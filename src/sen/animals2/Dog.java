package sen.animals2;

public class Dog implements Animal {
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

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + getName() + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
