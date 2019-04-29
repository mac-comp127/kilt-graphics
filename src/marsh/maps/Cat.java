package marsh.maps;

import java.util.Objects;

public class Cat extends Animal {

    public Cat(String name, String color) {
        super(name, color);
    }

    public String vocalize() {
        return "<meow>";
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + getName() + '\'' +
                ", color='" + getColor() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return getColor().equals(cat.getColor())
                && getName().equals(cat.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor());
    }
}
