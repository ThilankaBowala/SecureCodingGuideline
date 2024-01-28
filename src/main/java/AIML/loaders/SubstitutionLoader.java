package AIML.loaders;

import AIML.entity.AimlMap;
import AIML.entity.AimlSubstitution;

import java.io.File;
import java.util.Map;

/**
 * Substitution loader
 *
 * @author anton
 * @since 19/10/16
 *
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * @since 28/1/24
 */
public class SubstitutionLoader extends MapLoader<AimlSubstitution> {
    @Override
    public AimlSubstitution load(File file) {
        AimlMap map = super.load(file);
        return new AimlSubstitution(map);
    }

    @Override
    public Map<String, AimlSubstitution> loadAll(File... files) {
        return super.loadAll(files);
    }

    @Override
    protected Map<String, String> loadFile(File file) {
        return super.loadFile(file);
    }

    @Override
    protected void parseRow(final Map<String, String> data, final String row) {
        var splitStr = row.toUpperCase().trim().split(",");
        if (splitStr.length < 2) return;
        var first = splitStr[0];
        var second = splitStr[1];
        if (first.length() >= 2 && second.length() >= 2)
            data.put(removeBraces(first), removeBraces(second));
    }

    private String removeBraces(String value) {
        return value.substring(1, value.length() - 2).trim();
    }
}
