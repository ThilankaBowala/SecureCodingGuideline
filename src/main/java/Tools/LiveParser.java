package Tools;

import Configurations.Constants;
import CustomViolationsDetectors.CustomViolationsDetectorSystem;
import Startup.PluginStartupActivity;
import com.intellij.codeInsight.hint.TooltipController;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionManager;
import com.intellij.openapi.editor.actionSystem.TypedAction;
import com.intellij.openapi.editor.actionSystem.TypedActionHandler;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import org.jetbrains.annotations.NotNull;

/**
 * Live coding guideline violations detector
 *
 * @author S.L. Dasanayake
 * @author A. Mudalige
 * @author M.L.T. Perera
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * Did code refactoring on 28/1/24
 * Added custom violation detection related changes on 29/1/24
 * Original repo: https://bitbucket.org/lasithd2/seproject_framework_for_secure_coding/src/master/
 * @since 2018
 */

public class LiveParser extends AnAction {

    public static String editorText;
    private static LiveParser instance;

    public static LiveParser getInstance() {
        if (instance == null) {
            instance = new LiveParser();
        }
        return instance;
    }

    static {
        final EditorActionManager actionManager = EditorActionManager.getInstance();
        final TypedAction typedAction = actionManager.getTypedAction();
        TypedActionHandler handler = typedAction.getHandler();
        typedAction.setupHandler(new MyTypedHandler(handler));
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
    }

    @Override
    public void update(@NotNull AnActionEvent event) {
        // Using the event, evaluate the context,
        // and enable or disable the action.
    }

    public static class MyTypedHandler implements TypedActionHandler {
        private final TypedActionHandler handler;

        private static final Logger LOG = Logger.getInstance(MyTypedHandler.class);

        public MyTypedHandler(TypedActionHandler handler) {
            this.handler = handler;
        }

        @Override
        public void execute(@NotNull Editor editor, char c, @NotNull DataContext dataContext) {
            ToolWindowSystem toolWindowSystem = ToolWindowSystem.getInstance();
            handler.execute(editor, c, dataContext);
            Project project = editor.getProject();
            Document document = editor.getDocument();
            editorText = document.getText();

            if(!Constants.isReadyToWakeUp()){
                try {
                    PluginStartupActivity.setRuleStoreDirPathAtEdit(project);
                } catch (ConfigurationException e) {
                    LOG.warn("Warning: " + e);
                }finally {
                    if(!Constants.isReadyToWakeUp()){
                        LOG.warn("Warning: Secure coding guidelines: Did not find your secure coding rules. If you want to detect violations of your secure coding guideline, please create your secure coding guideline rules first.");
                        LOG.warn("Secure coding guidelines: If you need an example for this, please refer this project: 'https://github.com/ThilankaBowala/HelloWorld' and the custom secure coding rule store of it: 'https://github.com/ThilankaBowala/HelloWorld/tree/master/.idea/SecureCodingGuidelines'");
                        return;
                    }
                }
            }

            TooltipController tooltipController = new TooltipController();
            editor.addEditorMouseMotionListener(new MyEditorMouseMotionListener(tooltipController));

            toolWindowSystem.setupScrollArea();

            SyntaxHighlighter syntaxHighlighter = new SyntaxHighlighter();
            syntaxHighlighter.clear();

            ToolWindow toolWindow = ToolWindowManager.getInstance(project).getToolWindow("Secure Coding Plugin");
            toolWindowSystem.clearToolWindow(toolWindow);

            this.removeAllHighlighters(editor);

            CustomViolationsDetectorSystem customViolationsDetectorSystem = CustomViolationsDetectorSystem.getInstance();

            try {
                toolWindowSystem.init();
                customViolationsDetectorSystem.detectCustomViolations(editor, document, syntaxHighlighter, toolWindow);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void removeAllHighlighters(Editor editor) {
            editor.getMarkupModel().removeAllHighlighters();
        }
    }
}