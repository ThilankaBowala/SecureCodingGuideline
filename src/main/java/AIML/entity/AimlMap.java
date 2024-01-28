package AIML.entity;

import java.util.HashMap;
import java.util.Map;

import AIML.consts.AimlTag;

/**
 * Implements AIML Map
 * A map is a function from one string set to another.
 * Elements of the domain are called keys and elements of the range are called values.
 *
 * @author anton
 * @since 19/10/16
 *
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * @since 28/1/24
 */
public class AimlMap extends HashMap<String, String> implements AimlElement {

    public AimlMap(Map<String, String> data) {
        super(data);
    }

    @Override
    public String getType() {
        return AimlTag.map;
    }
}
