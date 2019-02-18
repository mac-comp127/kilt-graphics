package sen.animals;

import java.util.Objects;

public class Cat extends Animal {
    private String color;

    public Cat(String name, String color) {
        super(name);
        this.color = color;
    }

    public String vocalize() {
        return "<meow>";
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + getName() + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return color.equals(cat.color)
                && getName().equals(cat.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
