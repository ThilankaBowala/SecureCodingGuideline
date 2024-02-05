package Tools;

import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.*;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

/**
 * Tool window related functionality
 *
 * @author S.L. Dasanayake
 * @author A. Mudalige
 * @author M.L.T. Perera
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * Did code refactoring on 28/1/24
 * Original repo: https://bitbucket.org/lasithd2/seproject_framework_for_secure_coding/src/master/
 * @since 2018
 */

public class MyToolWindowFactory implements ToolWindowFactory {
    @Override
    public void createToolWindowContent(@NotNull Project project, ToolWindow toolWindow) {
        ConsoleView consoleView = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole();
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(consoleView.getComponent(), "Results", false);
        toolWindow.getContentManager().addContent(content);
    }
}