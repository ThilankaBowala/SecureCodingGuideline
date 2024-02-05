package AIML.loaders;

import java.io.File;
import java.util.Map;

/**
 * File loader
 *
 * @param <T> type of result data
 * @author anton
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * Did code matching related changes on 28/1/24
 * @since 19/10/16
 */
public interface FileLoader<T> extends Loader<T, File> {
    T load(File file);

    Map<String, T> loadAll(File... files);
}
