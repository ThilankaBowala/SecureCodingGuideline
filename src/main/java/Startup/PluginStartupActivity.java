package Startup;

import Configurations.Constants;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.module.ModuleManager;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.startup.StartupActivity;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PluginStartupActivity  implements StartupActivity {
    private static final Logger LOG = Logger.getInstance(PluginStartupActivity.class);

    @Override
    public void runActivity(@NotNull Project project) {
        try {
            setRuleStoreDirPathATStartup(project);
        } catch (ConfigurationException e) {
            LOG.warn("Warning: " + e);
        }
    }

    public static void setRuleStoreDirPathATStartup(Project project) throws ConfigurationException {
        try{
            var projectRootVirtual = ModuleRootManager.getInstance(ModuleManager.getInstance(project).getModules()[0]).getContentRoots()[0];
            var projectRootDirPath = projectRootVirtual.getPath().replace("/", File.separator);
            setRuleStoreDirPath(projectRootDirPath);

        }catch (Exception e) {
            LOG.warn("Warning: Secure coding guidelines: Plugin startup activity fails: " + e);
            LOG.warn("Warning: Please create the folder 'SecureCodingGuidelines/aiml' inside your '.idea' folder, if you do not have done already.");
            throw new ConfigurationException("Secure coding guidelines: Could not access AIML rule store directory path at the plugin startup");
        }
    }

    public static void setRuleStoreDirPathAtEdit(Project project) throws ConfigurationException {
        try{
            var projectRootDirPath = project.getBasePath().replace("/", File.separator);
            setRuleStoreDirPath(projectRootDirPath);

        }catch (Exception e) {
            LOG.warn("Warning: Secure coding guidelines: Plugin startup activity fails: " + e);
            LOG.warn("Warning: Please create the folder 'SecureCodingGuidelines/aiml' inside your '.idea' folder, if you do not have done already.");
            throw new ConfigurationException("Secure coding guidelines: Could not access AIML rule store directory path at the plugin startup.");
        }
    }

    private static void setRuleStoreDirPath(String projectRootDirPath) {
        var ruleStoreDirPath = Path.of(projectRootDirPath.concat(File.separator).concat(Constants.IDEA_DIR_NAME)
                        .concat(File.separator).concat(Constants.GUIDELINES_ROOT_DIR_NAME))
                .toAbsolutePath().toString();

        Constants.setRuleStoreDirPath(ruleStoreDirPath);

        var aimlFolderExists = validate(ruleStoreDirPath);
        if(aimlFolderExists){
            Constants.setReadyToWakeUp(true);
            LOG.info("Secure coding guidelines: Plugin startup activity: Rule store access succeeded.");
        }
    }

    private static boolean validate(String folder) {
        if (folder == null || folder.isEmpty())
            return false;
        var actualFolder = Paths.get(folder);
        if (Files.notExists(actualFolder)) {
            LOG.warn("Warning: Secure coding guidelines: Cannot find rule definitions folder: " + folder);
            return false;
        }
        return true;
    }
}
