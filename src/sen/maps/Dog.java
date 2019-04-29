package sen.maps;

public class Dog extends Animal {
    private String color;

    public Dog(String name, String color) {
        super(name);
        this.color = color;
    }

    public String vocalize() {
        return "RUFF!!";
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + getName() + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
