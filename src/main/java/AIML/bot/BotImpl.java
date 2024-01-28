package AIML.bot;

import AIML.consts.AimlConst;
import AIML.core.GraphMaster;
import AIML.entity.AimlCategory;
import AIML.entity.AimlMap;
import AIML.entity.AimlSet;
import AIML.entity.AimlSubstitution;
import AIML.loaders.AimlLoader;
import AIML.loaders.MapLoader;
import AIML.loaders.SetLoader;
import AIML.loaders.SubstitutionLoader;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Class representing the AIML bot, for a code matching program
 *
 * @author Thilanka Bowala <thilankabowala@gmail.com>
 * @since 28/1/24
 */
public class BotImpl {

    private final GraphMaster brain;
    private static BotImpl bot;

    private final String rootDir;
    private ChatContext chatContext;
    private String name = AimlConst.default_bot_name;

    public static BotImpl getInstance() {
        if (bot == null) {
            bot = new BotImpl("D:\\01_MSc\\0_research_final\\Code\\AIML\\ExampleGuidelines\\"); //todo
        }
        return bot;
    }

    public Optional<String> checkMatches() {
        bot.wakeUp();
        String message = "for (float i = "; // read opened doc here
        return bot.process(message);
    }

    private BotImpl(String rootDir) {
        this.rootDir = rootDir;
        this.chatContext = new ChatContext();
        var aimlSets = loadSets();
        var aimlMaps = loadMaps();
        var aimlCategories = loadAiml();
        brain = new GraphMaster(preprocess(aimlCategories, aimlSets), aimlSets, aimlMaps, loadSubstitutions());
    }

    private Optional<String> process(String textLine) {
        var message = textLine == null || textLine.isEmpty() ? AimlConst.null_input : textLine.trim();
        String response = bot.multiSentenceRespond(message, chatContext);
        chatContext.newState(message, response);
        return Optional.of(response);
    }

    private List<AimlCategory> preprocess(List<AimlCategory> categories, Map<String, AimlSet> aimlSets) {
        var processed = new ArrayList<AimlCategory>();
        for (AimlCategory aimlCategory : categories) {
            var pattern = aimlCategory.getPattern();
            var regexp = Pattern.compile("<set>(.+?)</set>");
            var matcher = regexp.matcher(pattern);
            if (matcher.find()) {
                var setName = matcher.group(1);
                var setValues = aimlSets.get(setName + ".txt");
                if (setValues != null) {
                    for (String s : setValues) {
                        var first = matcher.replaceFirst(s);
                        var cloned = aimlCategory.clone();
                        cloned.setPattern(first);
                        processed.add(cloned);
                    }
                }
            } else {
                processed.add(aimlCategory);
            }
        }
        return processed;
    }

    public boolean wakeUp() {
        return validate(getRootDir()) && validate(getAimlFolder());
    }

    public String multiSentenceRespond(String request, ChatContext state) {
        //todo: generate error codes here
        var sentences = brain.sentenceSplit(request);
        var response = new StringBuilder();
        for (String sentence : sentences)
            response.append(" ").append(respond(sentence, state));
        return (response.length() == 0)
                ? AimlConst.error_bot_response
                : response.toString().trim();
    }

    public String respond(final String request, ChatContext state) {
        var stars = new ArrayList<String>();
        var pattern = brain.match(request, state.topic(), state.that(), stars);
        return brain.respond(stars, pattern, state.topic(), state.that(), state.getPredicates());
    }

    private List<AimlCategory> loadAiml() {
        var loader = new AimlLoader();
        var folder = getAimlFolder();
        var categories = loader.loadFiles(folder);
        return categories;
    }

    private Map<String, AimlSet> loadSets() {
        var sets = new File(getSetsFolder());
        if (!sets.exists()) {
//            log.warn("Sets not found!");
            return Collections.emptyMap();
        }
        var files = sets.listFiles();
        if (files == null || files.length == 0)
            return Collections.emptyMap();

        var loader = new SetLoader();

        var data = loader.loadAll(files);
        int count = data.keySet().stream().mapToInt(s -> data.get(s).size()).sum();

//        log.info("Loaded {} set records from {} files.", count, files.length);
        return data;
    }

    private Map<String, AimlMap> loadMaps() {
        var maps = new File(getMapsFolder());
        if (!maps.exists()) {
//            log.warn("Maps not found!");
            return Collections.emptyMap();
        }
        var files = maps.listFiles();
        if (files == null || files.length == 0) return Collections.emptyMap();

        var loader = new MapLoader<>();

        var data = loader.loadAll(files);
        int count = data.keySet()
                .stream()
                .mapToInt(s -> data.get(s).size())
                .sum();

//        log.info("Loaded " + count + " map records from " + files.length + " files.");
        return data;
    }

    private Map<String, AimlSubstitution> loadSubstitutions() {
        var maps = new File(getSubstitutionsFolder());
        if (!maps.exists()) {
//            log.warn("Maps not found!");
            return Collections.emptyMap();
        }
        var files = maps.listFiles();
        if (files == null || files.length == 0)
            return Collections.emptyMap();

        var loader = new SubstitutionLoader();

        var data = loader.loadAll(files);
        int count = data.keySet().stream().mapToInt(s -> data.get(s).size()).sum();

//        log.info("Loaded " + count + " substitutions from " + files.length + " files.");
        return data;
    }

    private boolean validate(String folder) {
        if (folder == null || folder.isEmpty())
            return false;
        var botsFolder = Paths.get(folder);
        if (Files.notExists(botsFolder)) {
//            log.warn("BotImpl folder " + folder + " not found!");
            return false;
        }
        return true;
    }

    private String getRootDir() {
        return rootDir;
    }

    private String getAimlFolder() {
        return getRootDir() + "aiml";
    }

    private String getSubstitutionsFolder() {
        return getRootDir() + "substitutions";
    }

    private String getSetsFolder() {
        return getRootDir() + "sets";
    }

    private String getMapsFolder() {
        return getRootDir() + "maps";
    }

    private String getSkillsFolder() {
        return getRootDir() + "skills";
    }
}
