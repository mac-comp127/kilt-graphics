package sen.animals3;

import java.util.Objects;

public class Cat implements Animal, Comparable<Animal> {
    private String color;
    private String name;

    public Cat(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String vocalize() {
        return "<meow>";
    }

    public String getName() {
        return this.name;
    }

    public String getSpecies() {
        return "Felis catus";
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

    @Override
    public int compareTo(Animal other) {
        int result = this.getName().compareTo(other.getName());
        if (result == 0) {
            result = this.getSpecies().compareTo(other.getSpecies());
        }
        return result;
    }
}
