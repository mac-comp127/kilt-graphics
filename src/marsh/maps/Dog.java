package marsh.maps;

public class Dog extends Animal {

    public Dog(String name, String color) {
        super(name, color);
    }

    public String vocalize() {
        return "RUFF!!";
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + getName() + '\'' +
                ", color='" + getColor() + '\'' +
                '}';
    }
}
