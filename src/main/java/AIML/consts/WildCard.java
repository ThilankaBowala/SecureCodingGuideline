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

    private final String sumbol;

    WildCard(String sumbol) {
        this.sumbol = sumbol;
    }

    public String get() {
        return sumbol;
    }
}