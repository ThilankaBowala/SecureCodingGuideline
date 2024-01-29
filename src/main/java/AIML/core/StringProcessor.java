package AIML.core;

import AIML.entity.CustomTuple;

import java.util.List;
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

    public CustomTuple<Integer,Integer> getMatchingRange(String input, List<String> stars) {
        if(input.isEmpty())
            return new CustomTuple<>(-1, -1);

        if(stars.isEmpty())
            return new CustomTuple<>(0, input.length() - 1);

        var processedInput = input.trim();
        var firstStar = stars.get(0);
        var lastStar = stars.get(stars.size() - 1);

        Integer startIndex;
        Integer endIndex;

        if(processedInput.startsWith(firstStar))
            startIndex = firstStar.length();
        else
            startIndex = 0;

        if(processedInput.endsWith(lastStar))
            endIndex = input.length() - 1 - lastStar.length();
        else
            endIndex = input.length() - 1;

        return new CustomTuple<>(startIndex, endIndex);
    }
}
