package AIML.consts;

/**
 * Wildcards
 *
 * @author batiaev
 * @since 19/06/15
 *
 * @Contributor Thilanka Bowala <thilankabowala@gmail.com>
 * @since 28/1/24
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