package AIML.entity;

import AIML.consts.AimlTag;

import java.util.HashSet;
import java.util.Set;

/**
 * Implements AIML Sets
 *
 * @author anton
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * Did code matching related changes on 28/1/24
 * @since 19/10/16
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
