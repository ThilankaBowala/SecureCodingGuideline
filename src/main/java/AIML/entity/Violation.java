package AIML.entity;

/**
 * Violation data structure
 *
 * @author Thilanka Bowala <thilankabowala@gmail.com>
 * @since 29/1/24
 */

public class Violation {
    private String violationCode;
    private int editorLineNumber;
    private int firstNonWhiteSpaceIndex;
    private int matchingIndexStart;
    private int matchingIndexEnd;

    public Violation(String _violationCode, int _editorLineNumber, int _firstNonWhiteSpaceIndex,
                     int _matchingIndexStart, int _matchingIndexEnd) {
        this.violationCode = _violationCode;
        this.editorLineNumber = _editorLineNumber;
        this.firstNonWhiteSpaceIndex = _firstNonWhiteSpaceIndex;
        this.matchingIndexStart = _matchingIndexStart;
        this.matchingIndexEnd = _matchingIndexEnd;
    }

    public String getViolationCode() {
        return violationCode;
    }

    public int getEditorLineNumber() {
        return editorLineNumber;
    }

    public int getFirstNonWhiteSpaceIndex() {
        return firstNonWhiteSpaceIndex;
    }

    public int getMatchingIndexStart() {
        return matchingIndexStart;
    }

    public int getMatchingIndexEnd() {
        return matchingIndexEnd;
    }
}
