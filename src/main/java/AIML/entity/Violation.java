package AIML.entity;

public class Violation {
    private String violationCode;
    private int editorLineNumber;

    public Violation(String _violationCode, int _editorLineNumber){
        this.violationCode = _violationCode;
        this.editorLineNumber = _editorLineNumber;
    }

    public String getViolationCode(){
        return violationCode;
    }

    public int getEditorLineNumber(){
        return editorLineNumber;
    }
}
