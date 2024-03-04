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
import java.nio.file.Path;

public class PluginStartupActivity  implements StartupActivity {
    private static final Logger LOG = Logger.getInstance(PluginStartupActivity.class);

    @Override
    public void runActivity(@NotNull Project project) {
        try {
            setRuleStoreDirPath(project);
        } catch (ConfigurationException e) {
            LOG.error(e);
        }
    }

    public static void setRuleStoreDirPath(Project project) throws ConfigurationException {
        try{
            var projectRootVirtual = ModuleRootManager.getInstance(ModuleManager.getInstance(project).getModules()[0]).getContentRoots()[0];
            var projectRootDirPath = projectRootVirtual.getPath().replace("/", File.separator);
            var ruleStoreDirPath = Path.of(projectRootDirPath.concat(File.separator).concat(Constants.IDEA_DIR_NAME)
                    .concat(File.separator).concat(Constants.GUIDELINES_ROOT_DIR_NAME).concat(File.separator))
                    .toAbsolutePath().toString();

            Constants.setRuleStoreDirPath(ruleStoreDirPath);
            LOG.info("Secure coding guidelines: Plugin startup activity: Rule store access succeeded.");
        }catch (Exception e) {
            LOG.error("Secure coding guidelines: Plugin startup activity fails: " + e);
            throw new ConfigurationException("Secure coding guidelines: Could not access AIML rule store directory path at the plugin startup");
        }
    }
}
