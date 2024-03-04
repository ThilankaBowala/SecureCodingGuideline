package AIML.bot;

import AIML.consts.AimlConst;
import AIML.core.GraphMaster;
import AIML.core.StringProcessor;
import AIML.entity.*;
import AIML.loaders.AimlLoader;
import AIML.loaders.MapLoader;
import AIML.loaders.SetLoader;
import AIML.loaders.SubstitutionLoader;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
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

    private final GraphMaster detector;
    private static BotImpl bot;

    private final String rootDir;
    private CustomViolationsDetectorContext customViolationsDetectorContext;

    private AIML.core.StringProcessor stringProcessor;

    public static BotImpl getInstance(String rootDir) {
        if (bot == null) {
            bot = new BotImpl(rootDir);
            bot.wakeUp();
        }
        return bot;
    }

    private BotImpl(String rootDir) {
        this.rootDir = rootDir;
        this.customViolationsDetectorContext = new CustomViolationsDetectorContext();
        this.stringProcessor = new StringProcessor();
        var aimlSets = loadSets();
        var aimlMaps = loadMaps();
        var aimlCategories = loadAiml();
        detector = new GraphMaster(preprocess(aimlCategories, aimlSets), aimlSets, aimlMaps, loadSubstitutions());
    }

    public List<Violation> detectViolations(String editorText) {
        editorText = editorText == null || editorText.isEmpty() ? AimlConst.null_input : editorText;
        List<Violation> violations = bot.matchedViolations(editorText, customViolationsDetectorContext);
//        customViolationsDetectorContext.newState(editorTextTrimmed, violations);
        return violations;
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

    public List<Violation> matchedViolations(String editorText, CustomViolationsDetectorContext state) {
        var editorLines = detector.linesSplit(editorText);
        var detectedViolations = new ArrayList<Violation>();

        for (int lineNumber = 0; lineNumber < editorLines.length; lineNumber++) {
            String editorLine = editorLines[lineNumber];
            var match = detectCodeMatches(editorLine, state);
            var errorCode = match.x;

            if (!errorCode.isEmpty()) {
                var stars = match.y;

                var editorLineNumber = lineNumber + 1;
                var firstNonWhiteSpaceIndex = stringProcessor.getFirstNonWhiteSpace(editorLine);
                var matchingRange = stringProcessor.getMatchingRange(editorLine, stars);

                var violation = new Violation(errorCode, editorLineNumber, firstNonWhiteSpaceIndex, matchingRange.x, matchingRange.y);
                detectedViolations.add(violation);
            }
        }

        return detectedViolations;
    }

    public CustomTuple<String, ArrayList<String>> detectCodeMatches(final String editorLine, CustomViolationsDetectorContext state) {
        var stars = new ArrayList<String>();
        var pattern = detector.match(editorLine, state.topic(), state.that(), stars);
        var detectorResponse = detector.detectorRespond(stars, pattern, state.topic(), state.that(), state.getPredicates());
        return new CustomTuple(detectorResponse, stars);
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
        return Path.of(getRootDir().concat(File.separator).concat("aiml"))
                .toAbsolutePath().toString();
    }

    private String getSubstitutionsFolder() {
        return Path.of(getRootDir().concat(File.separator).concat("substitutions"))
                .toAbsolutePath().toString();
    }

    private String getSetsFolder() {
        return Path.of(getRootDir().concat(File.separator).concat("sets"))
                .toAbsolutePath().toString();
    }

    private String getMapsFolder() {
        return Path.of(getRootDir().concat(File.separator).concat("maps"))
                .toAbsolutePath().toString();
    }

    private String getSkillsFolder() {
        return Path.of(getRootDir().concat(File.separator).concat("skills"))
                .toAbsolutePath().toString();
    }
}
