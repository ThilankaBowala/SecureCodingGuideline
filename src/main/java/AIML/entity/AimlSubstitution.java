package AIML.entity;

import java.util.Map;

/**
 * Implements AIML Map
 *
 * A map is a function from one string set to another.
 * Elements of the domain are called keys and elements of the range are called values.
 *
 * @author anton
 * @since 19/10/16
 *
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * @since 28/1/24
 */
public class AimlSubstitution extends AimlMap {

    public AimlSubstitution(Map<String, String> data) {
        super(data);
    }
}
