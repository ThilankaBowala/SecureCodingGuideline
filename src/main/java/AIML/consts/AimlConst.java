package AIML.consts;

import java.io.File;
import java.nio.file.Path;

/**
 * Aiml constants
 *
 * @author batiaev
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * Did code matching related changes on 28/1/24
 * @since 6/17/15
 */
public class AimlConst {

    public static final String AIML_FILE_SUFFIX = ".aiml";

    public static String sample_rule_store_root_path = Path.of("resources/sampleSecureCodingGuidelines".replace("/", File.separator))
            .toAbsolutePath().toString();
    public static final String default_bot_name = "SecureCodingGuidelineBot";
    public static final String error_bot_response = "Something is wrong with my brain.";
    public static final String default_bot_response = "";
    public static final String default_topic = "unknown";
    public static final String default_that = "unknown";
    public static final String null_input = "#NORESP";
    public static boolean debug = false;
}