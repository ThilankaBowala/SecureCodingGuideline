package Tools;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.markup.EffectType;
import com.intellij.openapi.editor.markup.HighlighterTargetArea;
import com.intellij.openapi.editor.markup.TextAttributes;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Syntax highlighter functions
 *
 * @author S.L. Dasanayake
 * @author A. Mudalige
 * @author M.L.T. Perera
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * Refactoring, Added syntax highlighting for custom detector on 29/1/24
 * Original repo: https://bitbucket.org/lasithd2/seproject_framework_for_secure_coding/src/master/
 * @since 2018
 */

public class SyntaxHighlighter {
    public static Map<Integer, ArrayList<Integer>> annotateoffsets = new HashMap<>();
    public static List<String> tooltips = new ArrayList<String>();

    public void highlight(Editor editor, Document document, int line, int firstNonWhiteSpaceIndex,
                          int matchingStartOffset, int matchingEndOffset, String tooltip) {

        TextAttributes textAttributes = new TextAttributes();
        textAttributes.setBackgroundColor(Color.orange);
        textAttributes.setEffectColor(Color.red);
        textAttributes.setEffectType(EffectType.WAVE_UNDERSCORE);

        try {
            int lineStartOffset = document.getLineStartOffset(Math.max(0, line - 1)) + firstNonWhiteSpaceIndex + matchingStartOffset;
            int lineEndOffset = document.getLineStartOffset(Math.max(0, line - 1)) + matchingEndOffset + 1;

            display(editor, lineStartOffset, lineEndOffset, textAttributes, tooltip);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void highlight(Editor editor, Document document, ArrayList line, ArrayList column, ArrayList end, String tooltip) {
        TextAttributes x = new TextAttributes();
        x.setBackgroundColor(Color.orange);
        x.setEffectColor(Color.red);
        x.setEffectType(EffectType.WAVE_UNDERSCORE);

        try {
            for (int i = 0; i < line.size(); i++) {
                int lineStartOffset = document.getLineStartOffset(Math.max(0, (Integer) line.get(i) - 1)) + (Integer) column.get(i) - 1;
                int lineEndOffset = document.getLineStartOffset(Math.max(0, (Integer) line.get(i) - 1)) + (Integer) end.get(i);
                display(editor, lineStartOffset, lineEndOffset, x, tooltip);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void display(Editor editor, int lineStartOffset, int lineEndOffset, TextAttributes textAttributes,
                        String tooltip) {
        annotateoffsets.put(annotateoffsets.size() + 1, new ArrayList<Integer>() {{
            add(lineStartOffset);
            add(lineEndOffset);
        }});
        tooltips.add(tooltip);
        editor.getMarkupModel().addRangeHighlighter(
                lineStartOffset, lineEndOffset, 3333, textAttributes, HighlighterTargetArea.EXACT_RANGE
        );
    }

    public void clear() {
        annotateoffsets.clear();
        tooltips.clear();
    }
}
