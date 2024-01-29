package AIML.entity;

public class Violation {
    private String violationCode;
    private int editorLineNumber;
    private int firstNonWhiteSpaceIndex;

    public Violation(String _violationCode, int _editorLineNumber, int _firstNonWhiteSpaceIndex){
        this.violationCode = _violationCode;
        this.editorLineNumber = _editorLineNumber;
        this.firstNonWhiteSpaceIndex = _firstNonWhiteSpaceIndex;
    }

    public String getViolationCode(){
        return violationCode;
    }

    public int getEditorLineNumber(){
        return editorLineNumber;
    }

    public int getFirstNonWhiteSpaceIndex(){
        return firstNonWhiteSpaceIndex;
    }
}
