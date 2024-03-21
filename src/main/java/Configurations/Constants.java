package Configurations;

import java.io.File;
import java.nio.file.Path;

public final class Constants {
    private Constants() {
    }

    public static final String IDEA_DIR_NAME = ".idea";
    public static final String GUIDELINES_ROOT_DIR_NAME = "SecureCodingGuidelines";
    private static String ruleStoreDir = Path.of("./resources/sampleSecureCodingGuidelines".replace("/", File.separator))
            .toAbsolutePath().toString();

    private static boolean readyToWakeUp = false;

    public static String getRuleStoreDirPath() {
        return ruleStoreDir;
    }

    public static void setRuleStoreDirPath(String newRootPath) {
        ruleStoreDir = newRootPath;
    }

    public static boolean isReadyToWakeUp() {
        return readyToWakeUp;
    }

    public static void setReadyToWakeUp(boolean _readyToWakeUp) {
        readyToWakeUp = _readyToWakeUp;
    }
}
