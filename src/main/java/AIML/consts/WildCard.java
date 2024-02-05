package AIML.consts;

/**
 * Wildcards
 *
 * @author batiaev
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * Did code matching related changes on 28/1/24
 * @since 19/06/15
 */
public enum WildCard {
    ZeroMore("^"),
    OneMore("*"),
    ZeroMorePriority("#"),
    OneMorePriority("_");

    private final String symbol;

    WildCard(String symbol) {
        this.symbol = symbol;
    }

    public String get() {
        return symbol;
    }
}