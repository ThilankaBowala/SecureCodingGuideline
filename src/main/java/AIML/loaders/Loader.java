package AIML.loaders;

import java.util.Map;

/**
 * Abstract interface for any types of loader specified by two types: type of source and type of results
 *
 * @param <S> source type of data
 * @param <R> result type of data
 * @author anton
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * Did code matching related changes on 28/1/24
 * @since 25/10/16
 */
public interface Loader<R, S> {
    /**
     * @param source of data
     * @return data from ${source}
     */
    R load(S source);

    /**
     * Load from collection of sources
     *
     * @param sources of data
     * @return map with sources names as keys and result data as values
     */
    Map<String, R> loadAll(S... sources);
}
