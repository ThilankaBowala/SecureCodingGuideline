package AIML.bot;

import AIML.consts.AimlConst;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Chat context
 *
 * @author batiaev
 * @author Marco Piovesan
 * Added predicates on 29/08/16
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * Did code matching related changes on 28/1/24
 * @since 18/06/15
 */
public class CustomViolationsDetectorContext {
    private final UUID chatUid;
    private String request = "";
    private String topic = AimlConst.default_topic;
    private String that = AimlConst.default_that;
    private final Map<String, String> predicates = new HashMap<>();

    public CustomViolationsDetectorContext() {
        chatUid = UUID.randomUUID();
    }

    public void newState(String request, String respond) {
        this.request = request;
        this.that = respond;
    }

    public String topic() {
        if (predicates.containsKey("topic")) {
            setTopic(predicates.get("topic"));
        }
        return topic;
    }

    public String that() {
        return that;
    }

    public String request() {
        return request;
    }

    public String respond() {
        return that;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setRespond(String respond) {
        this.that = respond;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public Map<String, String> getPredicates() {
        return predicates;
    }

}
