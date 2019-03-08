package sen.animals2;

public class Dog implements Animal, Comparable<Animal> {
    private String color;
    private String name;

    public Dog(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String vocalize() {
        return "RUFF!!";
    }

    public String getName() {
        return this.name;
    }

    public String getSpecies() {
        return "Canis lupus";
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + getName() + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    @Override
    public int compareTo(Animal other) {
        int nameCompareResult = this.getName().compareTo(other.getName());
        return nameCompareResult;
    }
}
