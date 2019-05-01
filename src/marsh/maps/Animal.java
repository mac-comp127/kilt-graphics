package marsh.maps;

import java.util.Objects;

public abstract class Animal {
    private String name;
    private String color;

    public Animal(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public abstract String vocalize();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
       if (this == o) {
           return true;
       }
        if(o == null || getClass() != o.getClass()) {
            return false;
        }
        Animal animal = (Animal) o;
        return Objects.equals(name, animal.name) &&
                Objects.equals(color, animal.color);
    }

    @Override
    public int hashCode() {
        // A valid return, but not a good one
        // return 1;

        // A better hash function
        return Objects.hash(name, color);
    }
}
