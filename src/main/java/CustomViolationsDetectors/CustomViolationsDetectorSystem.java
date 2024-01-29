package CustomViolationsDetectors;

import AIML.bot.BotImpl;
import Countermeasures.Countermeasures_data;
import Tools.SyntaxHighlighter;
import Tools.ToolWindowSystem;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.wm.ToolWindow;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * Class contains custom violation detection related methods
 *
 * @author Thilanka Bowala <thilankabowala@gmail.com>
 * @since 28/1/24
 */

public class CustomViolationsDetectorSystem {
    private static CustomViolationsDetectorSystem instance;
    private final BotImpl aimlBotInstance = BotImpl.getInstance();
    private static final ToolWindowSystem toolWindowSystem = ToolWindowSystem.getInstance();

    private CustomViolationsDetectorSystem() {
    }

    public static CustomViolationsDetectorSystem getInstance() {
        if (instance == null) {
            instance = new CustomViolationsDetectorSystem();
        }
        return instance;
    }

    public void detectCustomViolations(@NotNull Editor editor, Document document, SyntaxHighlighter syntaxHighlighter, ToolWindow toolWindow) {
        Countermeasures_data Countermeasure_data = new Countermeasures_data();
        String editorText = document.getText();

        List<String> violations = aimlBotInstance.detectViolations(editorText);

        if(!violations.isEmpty()){
            for (int i=0; i<violations.size(); i++)
            {
                String violation = violations.get(i);
                String tooltip = violation;
//            syntaxHighlighter.highlight(editor, document, lce.get("method_rule1_line"), lce.get("method_rule1_column"), lce.get("method_rule1_end"), tooltip);
                String CounterMeasure = Countermeasure_data.CountermeasureData.get(violation);
                JLabel link = new JLabel("Click here for more details");
                link.setHorizontalAlignment(JLabel.CENTER);
                setRuleDescription(violation, tooltip, link, CounterMeasure, "https://wiki.sei.cmu.edu/confluence/display/java/NUM09-J.+Do+not+use+floating-point+variables+as+loop+counters");
            }
        }

        toolWindowSystem.addBoxes();
        toolWindowSystem.addToToolWindow(toolWindow);
    }

    public void setRuleDescription(String ruleDescription, String tooltip, JLabel link, String ruleDetails, String url) {
        JLabel label = toolWindowSystem.createJLabel(ruleDescription, tooltip);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //super.mouseClicked(e);
                if (e.getButton() == MouseEvent.BUTTON1) {
                    setLinkToRule(link, url);
                    JLabel countermeasure = createCountermeasure(ruleDetails);
                    toolWindowSystem.addToCountermeasureBox(countermeasure, link);
                }
            }
        });
        toolWindowSystem.addToBox(label);
    }

    public JLabel createCountermeasure(String counterMeasureData) {
        JLabel jp = new JLabel();
        jp.setText(counterMeasureData);
        jp.setHorizontalAlignment(JLabel.CENTER);
        return jp;
    }

    public void setLinkToRule(JLabel link, String url) {
        link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        link.setToolTipText("Click on below link for more details...");
        link.setForeground(Color.BLUE);
        Font font = link.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        link.setFont(font.deriveFont(attributes));
        link.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() > 0) {
                    if (Desktop.isDesktopSupported()) {
                        Desktop desktop = Desktop.getDesktop();
                        try {
                            URI uri = new URI(url);
                            desktop.browse(uri);
                        } catch (IOException | URISyntaxException ex) {
                            System.out.println(ex.getMessage());
                        }
                    } else {
                        System.out.println("Desktop not supported.");
                    }

                    System.out.print(e.getClickCount());
                }
            }
        });
    }
}
