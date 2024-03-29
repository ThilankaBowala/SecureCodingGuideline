package AIML.loaders;

import AIML.entity.AimlMap;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Map loader
 *
 * @author antonF
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * Did code matching related changes on 28/1/24
 * @since 19/10/16
 */
public class MapLoader<T extends AimlMap> implements FileLoader<T> {
    private static final Logger log = getLogger(MapLoader.class);

    @Override
    public T load(File file) {

        if (file == null) {
            log.error("File is null");
            return null;
        }
        if (!file.exists()) {
            log.error("File {} is not exist", file.getAbsolutePath());
            return null;
        }

        var data = new AimlMap(loadFile(file));

        log.info("Loaded {} records from {}", data.size(), file.getName());
        return (T) data;
    }

    @Override
    public Map<String, T> loadAll(File... files) {
        var data = new HashMap<String, T>();
        for (File file : files)
            data.put(file.getName(), load(file));
        log.info("Loaded {} files", data.size());
        return data;
    }

    protected Map<String, String> loadFile(File file) {
        var data = new HashMap<String, String>();
        try (Stream<String> stream = Files.lines(file.toPath())) {
            stream.forEach(s -> parseRow(data, s));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    protected void parseRow(final Map<String, String> data, final String row) {
        var splitStr = row.toUpperCase().trim().split(":");
        if (splitStr.length == 2)
            data.put(splitStr[0], splitStr[1]);
    }
}
