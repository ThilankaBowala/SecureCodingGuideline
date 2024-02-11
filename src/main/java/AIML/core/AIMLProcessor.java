package AIML.core;

import AIML.consts.AimlConst;
import AIML.consts.AimlTag;
import AIML.consts.WildCard;
import AIML.entity.AimlCategory;
import AIML.utils.AppUtils;
import org.slf4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.*;
import java.util.regex.Pattern;

import static AIML.consts.AimlTag.*;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * The core AIML parser and interpreter.
 * Implements the AIML 2.0 specification as described in
 * AIML 2.0 Working Draft document
 * https://docs.google.com/document/d/1wNT25hJRyupcG51aO89UcQEiG-HkXRXusukADpFnDs4/pub
 * https://playground.pandorabots.com/en/tutorial/
 * http://blog.pandorabots.com/aiaas-aiml-2-0-support/
 * http://www.alicebot.org/documentation/aiml101.html
 * http://itc.ua/articles/kvest_tyuringa_7667/
 * http://www.eblong.com/zarf/markov/chan.c
 *
 * @author anton
 * @author Marco
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * Did code matching related changes on 28/1/24, added support for get, set, condition, li on 11/02/24
 * @since 19/10/16
 */
public class AIMLProcessor {
    private static final Logger log = getLogger(AIMLProcessor.class);

    private final List<AimlCategory> categories;
    private final Map<String, Map<String, AimlCategory>> topics;
    private Map<String, String> predicates;

    public AIMLProcessor(List<AimlCategory> categories) {
        this.predicates = new HashMap<>();
        this.topics = convert(categories);
        this.categories = categories;
    }

    private Map<String, Map<String, AimlCategory>> convert(List<AimlCategory> categories) {
        var topics = new HashMap<String, Map<String, AimlCategory>>();
        for (AimlCategory aimlCategory : categories) {
            Map<String, AimlCategory> topicCategories;
            if (topics.containsKey(aimlCategory.getTopic())) {
                topicCategories = topics.get(aimlCategory.getTopic());
            } else {
                topicCategories = new HashMap<>();
                topics.put(aimlCategory.getTopic(), topicCategories);
            }
            topicCategories.put(aimlCategory.getPattern(), aimlCategory);
        }
        return topics;
    }

    public String match(final String request, String topic, String that, List<String> stars) {
        var patterns = patterns(topic);
        if (!AimlConst.default_topic.equals(topic))
            patterns.addAll(patterns(AimlConst.default_topic));

        var result = WildCard.OneMore.get();
        for (String pattern : patterns) {
            if (WildCard.OneMore.get().equals(pattern)
                    || WildCard.OneMorePriority.get().equals(pattern)
                    || WildCard.ZeroMore.get().equals(pattern)
                    || WildCard.ZeroMorePriority.get().equals(pattern))
                result = pattern;
            else if (isMatching(request, pattern, stars))
                return pattern;
        }
        return result;
    }

    public String template(List<String> stars, String pattern, String topic, String that, Map<String, String> predicates) {
        this.predicates = predicates;
        var category = category(topic, pattern);
        if (category == null)
            category = category(AimlConst.default_topic, WildCard.OneMore.get());
        return category == null ? AimlConst.default_bot_response : getTemplateValue(category.getTemplate(), stars);
    }

    public int getTopicCount() {
        return topics.size();
    }

    public int getCategoriesCount() {
        return categories.size();
    }

    private boolean isMatching(String input, String pattern, List<String> stars) {
        input = input.trim();
        var regex = pattern.trim();
        regex = regex.replace(WildCard.OneMorePriority.get(), "(.+)");
        regex = regex.replace(WildCard.OneMore.get(), "(.+)");
        regex = regex.replace(WildCard.ZeroMorePriority.get(), "(.*)");
        regex = regex.replace(WildCard.ZeroMore.get(), "(.*)");

        var p = Pattern.compile(regex);
        var m = p.matcher(input);
        if (m.matches()) {
            for (int i = 0; i <= m.groupCount(); i++) {
                if (i > 0) //skip first group because that is contains full input
                    stars.add(m.group(i).toLowerCase());
            }
            return true;
        }
        return false;
    }

    private String getTemplateValue(Node node, List<String> stars) {
        var result = new StringBuilder();
        var childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); ++i) {
            result.append(recurseParse(childNodes.item(i), stars));
        }
        return (result.length() == 0) ? AimlConst.default_bot_response : result.toString();
    }

    private String recurseParse(Node node, List<String> stars) {
        node.normalize();
        var nodeName = node.getNodeName();
        switch (nodeName) {
            case text:
                return textParse(node);
            case template:
                return getTemplateValue(node, stars);
            case random:
                return randomParse(node);
            case srai:
                return sraiParse(node, stars);
            case set:
                return setParse(node, stars);
            case get:
                return getParse(node);
            case bot:
                return botInfoParse();
            case star:
                return starParse(node, stars);
            case think:
                getTemplateValue(node, stars);
                return "";
            case condition:
                return conditionParse(node, stars);
            case li:
                return liParse(node, stars);
        }
        return "";
    }

    private String starParse(Node node, List<String> stars) {
        if (stars.isEmpty()) return "";
        if (node.hasAttributes()) {
            Element element = (Element) node;
            try {
                int index = Integer.parseInt(element.getAttribute("index")) - 1;
                if (stars.size() > index)
                    return stars.get(index);
            } catch (Exception e) {
                log.error("Invalid index format {}: {}", element.getAttribute("index"), e.getLocalizedMessage());
            }
        } else if (node.hasChildNodes()) {
            var childNodes = node.getChildNodes();
            if (childNodes.getLength() == 1) {
                var item = childNodes.item(0);
                if (index.equals(item.getNodeName())) {
                    String sIndex = item.getNodeValue();
                    try {
                        int index = Integer.parseInt(sIndex) - 1;
                        if (stars.size() > index)
                            return stars.get(index);
                    } catch (Exception e) {
                        log.error("Invalid index format {}: {}", sIndex, e.getLocalizedMessage());
                    }
                }
            }
        }
        return stars.get(0);
    }

    private String botInfoParse() {
        return "This is a secure coding guideline bot written in AIML.";
    }

    private String textParse(Node node) {
        return node.getNodeValue().replaceAll("(\r\n|\n\r|\r|\n|\t)", "").replaceAll("  ", " ");
    }

    private String setParse(Node node, List<String> stars) {
        var attributes = node.getAttributes();
        if (attributes.getLength() > 0) {
            var node1 = attributes.getNamedItem("name");
            if (node1 == null) return "";
            var key = node1.getNodeValue();
            var value = getTemplateValue(node, stars);
            return predicates.put(key, value);
        }
        return "";
    }

    private String sraiParse(Node node, List<String> stars) {
        var category = category(AimlConst.default_topic, AppUtils.node2String(node));
        return category != null ? getTemplateValue(category.getTemplate(), stars) : AimlConst.error_bot_response;
    }

    private String randomParse(Node node) {
        var values = new ArrayList<String>();
        var childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); ++i) {
            if (childNodes.item(i).getNodeName().equals(AimlTag.li))
                values.add(AppUtils.node2String(childNodes.item(i)));
        }

        return AppUtils.getRandom(values);
    }

    private String getParse(Node node) {
        var attributes = node.getAttributes();
        if (attributes.getLength() > 0) {
            var node1 = attributes.getNamedItem("name");
            if (node1 == null) return "";
            var key = node1.getNodeValue();
            var value = predicates.get(key);
            return value;
        }
        return "";
    }

    private String conditionParse(Node node, List<String> stars) {
        var attributes = node.getAttributes();

        if (attributes.getLength() > 0) {
            var conditionNameNode = attributes.getNamedItem("name");
            var conditionValueNode = attributes.getNamedItem("value");

            if (conditionNameNode == null) return "";
            else if(conditionValueNode == null){
                //hasn't a value: only strings for li
                var conditionTemplateValue = getTemplateValue(node, stars);
                return conditionTemplateValue;
            }else{
                //has a value: a secondary variable, no li tags
                var conditionNameKey = conditionNameNode.getNodeValue();
                var conditionStateValue = predicates.get(conditionNameKey);

                var conditionSecondaryKey = conditionValueNode.getNodeValue();
                var conditionSecondaryValue = predicates.get(conditionSecondaryKey);

                if(conditionStateValue.equals(conditionSecondaryValue)) {
                    var conditionTemplateValue = getTemplateValue(node, stars);
                    return conditionTemplateValue;
                }
            }

        }
        return "";
    }

    private String liParse(Node node, List<String> stars) {
        var attributes = node.getAttributes();
        var liTemplateValue = getTemplateValue(node, stars);

        if (attributes.getLength() > 0) {
            var liValueNode = attributes.getNamedItem("value");
            if (liValueNode == null) return "";

            var parentConditionNode = node.getParentNode();
            var conditionAttributes = parentConditionNode.getAttributes();

            if (conditionAttributes.getLength() > 0) {
                var conditionNameNode = conditionAttributes.getNamedItem("name");
                var conditionNameKey = conditionNameNode.getNodeValue();
                var conditionStateValue = predicates.get(conditionNameKey);

                var liValue = getTemplateValue(liValueNode, stars);

                if(liValue.equals(conditionStateValue)) {
                    return liTemplateValue;
                }
            }

            return "";
        }else{
            return liTemplateValue;
        }
    }

    private Set<String> patterns(String topic) {
        if (topics.containsKey(topic)) {
            return topics.get(topic).keySet();
        } else {
            topics.put(topic, new HashMap<>());
            return Collections.emptySet();
        }
    }

    private AimlCategory category(String topic, String pattern) {
        return topics.get(topic).get(pattern);
    }
}