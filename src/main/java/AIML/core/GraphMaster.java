package AIML.core;

import AIML.entity.AimlCategory;
import AIML.entity.AimlMap;
import AIML.entity.AimlSet;
import AIML.entity.AimlSubstitution;

import java.util.List;
import java.util.Map;

/**
 * The AIML Pattern matching algorithm and data structure.
 * Brain of bot.
 *
 * @author anton
 * @author Marco
 *
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * @since 28/1/24
 * Predicates are passed to AIMLProcessor
 */
public class GraphMaster {
    private final Map<String, AimlSet> sets;
    private final Map<String, AimlMap> maps;
    private final Map<String, AimlSubstitution> substitutions;
    private final AIMLProcessor processor;

    public GraphMaster(List<AimlCategory> categories, Map<String, AimlSet> sets, Map<String, AimlMap> maps,
                       Map<String, AimlSubstitution> substitutions) {
        this.sets = sets;
        this.maps = maps;
        this.substitutions = substitutions;
        this.processor = new AIMLProcessor(categories);
    }

    public String getStat() {
        return "Brain contain "
                + processor.getTopicCount() + " topics, "
                + processor.getCategoriesCount() + " categories, "
                + sets.size() + " sets, "
                + maps.size() + " maps, "
                + substitutions.size() + " substitutions.";
    }

    /**
     * Split an input into an array of sentences based on sentence-splitting characters.
     *
     * @param editorText input text
     * @return array of sentences
     */
    public String[] linesSplit(String editorText) {
        editorText = editorText.replace("。", ".")
                .replace("？", "?")
                .replace("!", "!");
        var result = editorText.split("[\\r\\n|\\n\\r|\\r|\\n]");
        return result;
    }

    public String detectorRespond(List<String> stars, String pattern, String topic, String that, Map<String, String> predicates) {
        return processor.template(stars, pattern, topic, that, predicates);
    }

    public String match(String request, String topic, String that, List<String> stars) {
        return processor.match(request, topic, that, stars);
    }
}
