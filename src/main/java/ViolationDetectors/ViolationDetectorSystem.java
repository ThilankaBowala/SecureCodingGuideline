package ViolationDetectors;

import CodeFragments.ClassLevelCodeFragment;
import CodeFragments.MethodLevelCodeFragment;
import CodeFragments.PackageLevelCodeFragment;
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
import java.util.Map;

import static ViolationDetectors.ViolationDetector.lce;

public class ViolationDetectorSystem {
    private static ViolationDetectorSystem instance;
    private static final ToolWindowSystem toolWindowSystem = ToolWindowSystem.getInstance();

    private ViolationDetectorSystem(){
    }

    public static ViolationDetectorSystem getInstance(){
        if(instance == null){
            instance = new ViolationDetectorSystem();
        }
        return instance;
    }

    public void detectBasicViolations(@NotNull Editor editor, Document document, SyntaxHighlighter syntaxhighlighter, ToolWindow toolWindow) {
        DetectorFactory DetectorFactory = new DetectorFactory();
        Countermeasures_data Countermeasure_data = new Countermeasures_data();

        ViolationDetector methodLevelDetector = DetectorFactory.getViolatorType("MethodLevelViolationDetector");
        ViolationDetector classLevelDetector = DetectorFactory.getViolatorType("ClassLevelViolationDetector");
        ViolationDetector packageLevelDetector = DetectorFactory.getViolatorType("PackageLevelViolationDetector");

        MethodLevelCodeFragment methodLevel = new MethodLevelCodeFragment();
        ClassLevelCodeFragment classLevel = new ClassLevelCodeFragment();
        PackageLevelCodeFragment packageLevel = new PackageLevelCodeFragment();

        String rule1 = methodLevelDetector.rule1Detection();
        if (!rule1.equals("")) {
            String tooltip = "Do not use floating-point variables as loop counters";
            syntaxhighlighter.highlight(editor, document, lce.get("method_rule1_line"), lce.get("method_rule1_column"), lce.get("method_rule1_end"), tooltip);
            methodLevel.forCounter.clear();
            String CounterMeasure = Countermeasure_data.CountermeasureData.get("NUM09J");
            JLabel link = new JLabel("Click here for more details");
            link.setHorizontalAlignment(JLabel.CENTER);
            setRuleDescription(rule1, tooltip, link, CounterMeasure, "https://wiki.sei.cmu.edu/confluence/display/java/NUM09-J.+Do+not+use+floating-point+variables+as+loop+counters");
        }
        methodLevel.forCounter.clear();

        String rule2 = methodLevelDetector.rule2Detection();
        if (!rule2.equals("")) {
            String tooltip = "Do not catch NullPointerException or any of its ancestors";
            syntaxhighlighter.highlight(editor, document, lce.get("method_rule2_line"), lce.get("method_rule2_column"), lce.get("method_rule2_end"), tooltip);
            methodLevel.catchClause.clear();
            methodLevel.forCounter.clear();
            String CounterMeasure = Countermeasure_data.CountermeasureData.get("ERR08J");
            JLabel link = new JLabel("Click here for more details");
            link.setHorizontalAlignment(JLabel.CENTER);
            setRuleDescription(rule2, tooltip, link, CounterMeasure, "https://wiki.sei.cmu.edu/confluence/display/java/ERR08-J.+Do+not+catch+NullPointerException+or+any+of+its+ancestors");
        }
        methodLevel.catchClause.clear();
        methodLevel.forCounter.clear();

        String rule3 = classLevelDetector.rule1Detection();
        if (!rule3.equals("")) {
            String tooltip = "Classes that define an equals() method must also define a hashCode() method";
            syntaxhighlighter.highlight(editor, document, lce.get("class_rule1_line"), lce.get("class_rule1_column"), lce.get("class_rule1_end"), tooltip);
            classLevel.methoddeclarations.clear();
            String CounterMeasure = Countermeasure_data.CountermeasureData.get("MET09J");
            JLabel link = new JLabel("Click here for more details");
            link.setHorizontalAlignment(JLabel.CENTER);
            setRuleDescription(rule3, tooltip, link, CounterMeasure, "https://wiki.sei.cmu.edu/confluence/display/java/MET09-J.+Classes+that+define+an+equals%28%29+method+must+also+define+a+hashCode%28%29+method");
        }
        classLevel.methoddeclarations.clear();

        String rule4 = classLevelDetector.rule2Detection();
        if (!rule4.equals("")) {
            String tooltip = "Do not return references to private mutable class members";
            syntaxhighlighter.highlight(editor, document, lce.get("class_rule2_line"), lce.get("class_rule2_column"), lce.get("class_rule2_end"), tooltip);
            classLevel.ClassVarNonPrimitiveList.clear();
            methodLevel.returnstatementlist.clear();
            String CounterMeasure = Countermeasure_data.CountermeasureData.get("OBJ05J");
            JLabel link = new JLabel(("Click here for more details"));
            link.setHorizontalAlignment(JLabel.CENTER);
            setRuleDescription(rule4, tooltip, link, CounterMeasure, "https://wiki.sei.cmu.edu/confluence/display/java/OBJ05-J.+Do+not+return+references+to+private+mutable+class+members");
        }
        classLevel.ClassVarNonPrimitiveList.clear();
        methodLevel.returnstatementlist.clear();

        String rule5 = packageLevelDetector.rule1Detection();
        if (!rule5.equals("")) {
            String tooltip = "Do not invoke Thread.run()";
            syntaxhighlighter.highlight(editor, document, lce.get("package_rule1_line"), lce.get("package_rule1_column"), lce.get("package_rule1_end"), tooltip);
            packageLevel.ImplementedInterfaces.clear();
            methodLevel.methodCalls.clear();
            String CounterMeasure = Countermeasure_data.CountermeasureData.get("THI00J");
            JLabel link = new JLabel("Click here for more details");
            link.setHorizontalAlignment(JLabel.CENTER);
            setRuleDescription(rule5, tooltip, link, CounterMeasure, "https://wiki.sei.cmu.edu/confluence/pages/viewpage.action?pageId=88487912");
        }
        packageLevel.ImplementedInterfaces.clear();
        methodLevel.methodCalls.clear();

        String rule6 = packageLevelDetector.rule2Detection();
        if (!rule6.equals("")) {
            String tooltip = "Do not deviate from the proper signatures of serialization methods";
            syntaxhighlighter.highlight(editor, document, lce.get("package_rule2_line"), lce.get("package_rule2_column"), lce.get("package_rule2_end"), tooltip);
            packageLevel.ImplementedInterfaces.clear();
            classLevel.methodSignatures.clear();
            String CounterMeasure = Countermeasure_data.CountermeasureData.get("SER01J");
            JLabel link = new JLabel("Click here for more details");
            link.setHorizontalAlignment(JLabel.CENTER);
            setRuleDescription(rule6, tooltip, link, CounterMeasure, "https://wiki.sei.cmu.edu/confluence/display/java/SER01-J.+Do+not+deviate+from+the+proper+signatures+of+serialization+methods");
        }
        packageLevel.ImplementedInterfaces.clear();
        classLevel.methodSignatures.clear();

        String rule7 = classLevelDetector.rule4Detection();
        if (!rule7.equals("")) {
            String tooltip = "Prevent class initialization cycles";
            syntaxhighlighter.highlight(editor, document, lce.get("class_rule4_line"), lce.get("class_rule4_column"), lce.get("class_rule4_end"), tooltip);
            classLevel.clssvardeclarations.clear();
            classLevel.constructorAssignStmt.clear();
            String CounterMeasure = Countermeasure_data.CountermeasureData.get("DCL00J");
            JLabel link = new JLabel("Click here for more details");
            link.setHorizontalAlignment(JLabel.CENTER);
            setRuleDescription(rule7, tooltip, link, CounterMeasure, "https://wiki.sei.cmu.edu/confluence/display/java/DCL00-J.+Prevent+class+initialization+cycles");
        }
        classLevel.clssvardeclarations.clear();
        classLevel.constructorAssignStmt.clear();

        String rule8 = classLevelDetector.rule3Detection();
        if (!rule8.equals("")) {
            String tooltip = "Limit accessibility of fields";
            syntaxhighlighter.highlight(editor, document, lce.get("class_rule3_line"), lce.get("class_rule3_column"), lce.get("class_rule3_end"), tooltip);
            classLevel.clssvardeclarations.clear();
            methodLevel.returnstatementlist.clear();
            methodLevel.AssignExprlist.clear();
            methodLevel.UnaryExpressionslist.clear();
            String CounterMeasure = Countermeasure_data.CountermeasureData.get("OBJ01J");
            JLabel link = new JLabel("Click here for more details");
            link.setHorizontalAlignment(JLabel.CENTER);
            setRuleDescription(rule8, tooltip, link, CounterMeasure, "https://wiki.sei.cmu.edu/confluence/display/java/OBJ01-J.+Limit+accessibility+of+fields");
        }
        classLevel.clssvardeclarations.clear();
        methodLevel.returnstatementlist.clear();
        methodLevel.AssignExprlist.clear();
        methodLevel.UnaryExpressionslist.clear();

        String rule9 = methodLevelDetector.rule3Detection();
        if (!rule9.equals("")) {
            String tooltip = "Do not throw RuntimeException, Exception, or Throwable";
            syntaxhighlighter.highlight(editor, document, lce.get("method_rule3_line"), lce.get("method_rule3_column"), lce.get("method_rule3_end"), tooltip);
            methodLevel.throwStatement.clear();
            String CounterMeasure = Countermeasure_data.CountermeasureData.get("ERR07J");
            JLabel link = new JLabel("Click here for more details");
            link.setHorizontalAlignment(JLabel.CENTER);
            setRuleDescription(rule9, tooltip, link, CounterMeasure, "https://wiki.sei.cmu.edu/confluence/display/java/ERR07-J.+Do+not+throw+RuntimeException%2C+Exception%2C+or+Throwable");
        }
        methodLevel.throwStatement.clear();

        String rule10 = methodLevelDetector.rule4Detection();
        if (!rule10.equals("")) {
            String tooltip = "Do not complete abruptly from a finally block";
            syntaxhighlighter.highlight(editor, document, lce.get("method_rule4_line"), lce.get("method_rule4_column"), lce.get("method_rule4_end"), tooltip);
            methodLevel.finallystmtBlock.clear();
            String CounterMeasure = Countermeasure_data.CountermeasureData.get("ERR04J");
            JLabel link = new JLabel("Click here for more details");
            link.setHorizontalAlignment(JLabel.CENTER);
            setRuleDescription(rule10, tooltip, link, CounterMeasure, "https://wiki.sei.cmu.edu/confluence/display/java/ERR04-J.+Do+not+complete+abruptly+from+a+finally+block");
        }
        methodLevel.finallystmtBlock.clear();

        String rule11 = packageLevelDetector.rule3Detection();
        if (!rule11.equals("")) {
            String tooltip = "Do not construct BigDecimal objects from floating-point literals";
            syntaxhighlighter.highlight(editor, document, lce.get("package_rule3_line"), lce.get("package_rule3_column"), lce.get("package_rule3_end"), tooltip);
            methodLevel.ObjectCReationExpress.clear();
            String CounterMeasure = Countermeasure_data.CountermeasureData.get("NUM10J");
            JLabel link = new JLabel("Click here for more details");
            link.setHorizontalAlignment(JLabel.CENTER);
            setRuleDescription(rule11, tooltip, link, CounterMeasure, "https://wiki.sei.cmu.edu/confluence/display/java/NUM10-J.+Do+not+construct+BigDecimal+objects+from+floating-point+literals");
        }
        methodLevel.ObjectCReationExpress.clear();

        String rule12 = packageLevelDetector.rule4Detection();
        if (!rule12.equals("")) {
            String tooltip = "Call the superclass's getPermissions() method when writing a custom class loader";
            syntaxhighlighter.highlight(editor, document, lce.get("package_rule4_line"), lce.get("package_rule4_column"), lce.get("package_rule4_end"), tooltip);
            classLevel.methoddeclarations.clear();
            methodLevel.ObjectCReationExpress.clear();
            String CounterMeasure = Countermeasure_data.CountermeasureData.get("SEC07J");
            JLabel link = new JLabel("Click here for more details");
            link.setHorizontalAlignment(JLabel.CENTER);
            setRuleDescription(rule12, tooltip, link, CounterMeasure, "https://wiki.sei.cmu.edu/confluence/display/java/SEC07-J.+Call+the+superclass%27s+getPermissions%28%29+method+when+writing+a+custom+class+loader");
        }
        classLevel.methoddeclarations.clear();
        methodLevel.ObjectCReationExpress.clear();

        String rule13 = methodLevelDetector.rule5Detection();
        if (!rule13.equals("")) {
            String tooltip = "Do not use the Object.equals() method to compare two arrays";
            syntaxhighlighter.highlight(editor, document, lce.get("method_rule5_line"), lce.get("method_rule5_column"), lce.get("method_rule5_end"), tooltip);
            methodLevel.equalsmethodArguments.clear();
            methodLevel.arraysList.clear();
            String CounterMeasure = Countermeasure_data.CountermeasureData.get("EXP02J");
            JLabel link = new JLabel("Click here for more details");
            link.setHorizontalAlignment(JLabel.CENTER);
            setRuleDescription(rule13, tooltip, link, CounterMeasure, "https://wiki.sei.cmu.edu/confluence/display/java/EXP02-J.+Do+not+use+the+Object.equals%28%29+method+to+compare+two+arrays");
        }
        methodLevel.equalsmethodArguments.clear();
        methodLevel.arraysList.clear();

        String rule14 = packageLevelDetector.rule5Detection();
        if (!rule14.equals("")) {
            String tooltip = "Detect and handle file-related errors";
            syntaxhighlighter.highlight(editor, document, lce.get("package_rule5_line"), lce.get("package_rule5_column"), lce.get("package_rule5_end"), tooltip);
            methodLevel.ObjectCReationExpress.clear();
            methodLevel.IfStatements.clear();
            String CounterMeasure = Countermeasure_data.CountermeasureData.get("FIO02J");
            JLabel link = new JLabel("Click here for more details");
            link.setHorizontalAlignment(JLabel.CENTER);
            setRuleDescription(rule14, tooltip, link, CounterMeasure, "https://wiki.sei.cmu.edu/confluence/display/java/FIO02-J.+Detect+and+handle+file-related+errors");
        }
        methodLevel.ObjectCReationExpress.clear();
        methodLevel.IfStatements.clear();

        String rule15 = classLevelDetector.rule5Detection();
        if (!rule15.equals("")) {
            String tooltip = "Do not use public static nonfinal fields";
            syntaxhighlighter.highlight(editor, document, lce.get("class_rule5_line"), lce.get("class_rule5_column"), lce.get("class_rule5_end"), tooltip);
            classLevel.clssvardeclarations.clear();
            String CounterMeasure = Countermeasure_data.CountermeasureData.get("OBJ10J");

            JLabel link = new JLabel("Click here for more details");
            link.setHorizontalAlignment(JLabel.CENTER);
            setRuleDescription(rule15, tooltip, link, CounterMeasure, "https://wiki.sei.cmu.edu/confluence/display/java/OBJ10-J.+Do+not+use+public+static+nonfinal+fields");
        }
        classLevel.clssvardeclarations.clear();

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
