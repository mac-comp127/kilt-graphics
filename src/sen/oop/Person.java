package sen.oop;

import java.awt.*;

public class Person {
    private static final Color DEFAULT_FAVORITE_COLOR = Color.BLUE;
    private final String name;
    private final Color favorite;

    public Person() {
        this("James");
    }

    public Person(String name) {
        this(name, DEFAULT_FAVORITE_COLOR);
    }

    public Person(String name, Color favorite) {
        this.name = name;
        this.favorite = favorite;
    }

    public String getName() {
        return this.name;
    }

    public Color getFavoriteColor() {
        return favorite;
    }

    @Override
    public String toString() {
        return "<" + name + " favorite color: " + favorite + ">";
    }
}
