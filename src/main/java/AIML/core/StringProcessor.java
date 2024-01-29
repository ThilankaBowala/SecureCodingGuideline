package AIML.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringProcessor {
    public int getFirstNonWhiteSpace(String input) {
        if(input.isEmpty())
            return -1;

        var firstCharacter = input.charAt(0);
        if(firstCharacter != ' ')
            return 0;

        Pattern p = Pattern.compile("([^\\s]+)?(\\s)+");
        final Matcher matcher = p.matcher(input);
        matcher.find();
        return matcher.end();
    }
}
