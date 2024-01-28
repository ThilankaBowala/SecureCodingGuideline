package AIML.entity;

import AIML.consts.AimlTag;

import java.util.HashSet;
import java.util.Set;

/**
 * Implements AIML Sets
 *
 * @author anton
 * @since 19/10/16
 *
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * @since 28/1/24
 */
public class AimlSet extends HashSet<String> implements AimlElement {
    private final String name;

    public AimlSet(String name, Set<String> data) {
        super(data);
        this.name = name;
    }

    @Override
    public String getType() {
        return AimlTag.set;
    }
}
