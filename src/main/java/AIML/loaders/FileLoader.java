package AIML.loaders;

import java.io.File;
import java.util.Map;

/**
 * File loader
 *
 * @param <T> type of result data
 * @author anton
 * @since 19/10/16
 *
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * @since 28/1/24
 */
public interface FileLoader<T> extends Loader<T, File> {
    T load(File file);

    Map<String, T> loadAll(File... files);
}
