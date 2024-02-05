package AIML.entity;

import java.util.Map;

/**
 * Implements AIML Map
 * <p>
 * A map is a function from one string set to another.
 * Elements of the domain are called keys and elements of the range are called values.
 *
 * @author anton
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * Did code matching related changes on 28/1/24
 * @since 19/10/16
 */
public class AimlSubstitution extends AimlMap {

    public AimlSubstitution(Map<String, String> data) {
        super(data);
    }
}
