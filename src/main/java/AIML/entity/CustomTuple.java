package AIML.entity;

/**
 * Tuple data structure
 *
 * @author Thilanka Bowala <thilankabowala@gmail.com>
 * @since 29/1/24
 */

public class CustomTuple<X, Y> {
    public final X x;
    public final Y y;

    public CustomTuple(X x, Y y) {
        this.x = x;
        this.y = y;
    }
}
