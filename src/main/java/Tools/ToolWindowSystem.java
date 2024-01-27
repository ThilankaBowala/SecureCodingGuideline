package Tools;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import javax.swing.*;
import java.awt.*;

public class ToolWindowSystem {
    private static ToolWindowSystem instance;
    JLabel title = new JLabel();
    JPanel myToolWindowContent = new JPanel();
    Box box = Box.createVerticalBox();
    Box countermeasure_box = Box.createVerticalBox();
    JLabel counterMeasureBoxHeading;

    private ToolWindowSystem(){
    }

    public static ToolWindowSystem getInstance(){
        if(instance == null){
            instance = new ToolWindowSystem();
        }
        return instance;
    }

    public JLabel createJLabel(String rule, String tooltip) {
        JLabel jp = new JLabel();
        jp.setText("<html><br><font color='red'>" + rule + "</font><br></html>");
        jp.setToolTipText(tooltip);
        return jp;
    }

    public void init() {
        setupToolWindowStyling();
        setupCounterMeasureBoxHeading();
    }

    public void setupToolWindowStyling() {
        myToolWindowContent.setLayout(new GridLayout(1, 2));
        myToolWindowContent.setAutoscrolls(true);
        box.setBorder(BorderFactory.createLineBorder(JBColor.BLACK));
        countermeasure_box.setBorder(BorderFactory.createLineBorder(JBColor.BLACK));
        JLabel boxHeading = new JLabel("<html><h3><font color='black'>Violated Rules</font><h3></html>");
        boxHeading.setHorizontalAlignment(JLabel.CENTER);
        boxHeading.setBackground(JBColor.ORANGE);
        box.add(boxHeading);
    }

    public JLabel setupCounterMeasureBoxHeading() {
        counterMeasureBoxHeading = new JLabel("<html><h3><font color='black'>Rule Description</font><h3></html>");
        counterMeasureBoxHeading.setHorizontalAlignment(JLabel.CENTER);
        counterMeasureBoxHeading.setBackground(JBColor.ORANGE);
        countermeasure_box.add(counterMeasureBoxHeading);

        return counterMeasureBoxHeading;
    }

    public void setupScrollArea() {
        JScrollPane scrollArea = new JBScrollPane(countermeasure_box, JBScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JBScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollArea.add(myToolWindowContent);
    }

    public void addToToolWindow(ToolWindow toolWindow) {
        title.setLayout(new FlowLayout(FlowLayout.CENTER));
        myToolWindowContent.add(title);
        ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
        Content content = contentFactory.createContent(myToolWindowContent, "", true);
        toolWindow.getContentManager().addContent(content);
    }

    public void addBoxes() {
        myToolWindowContent.add(box);
        myToolWindowContent.add(countermeasure_box);
    }

    public void addToBox(JLabel label) {
        box.add(label);
    }

    public void addToCountermeasureBox(JLabel countermeasure, JLabel link) {
        countermeasure_box.removeAll();
        setupCounterMeasureBoxHeading();
        countermeasure_box.add(countermeasure);
        countermeasure_box.add(link);
    }

    public void clearToolWindow(ToolWindow toolWindow) {
        toolWindow.getContentManager().removeAllContents(true);
        box.removeAll();
        countermeasure_box.removeAll();
        title.removeAll();
        myToolWindowContent.remove(box);
        myToolWindowContent.remove(countermeasure_box);
        myToolWindowContent.remove(title);
    }
}
