package marsh.polymorphism;

public class Dog extends Canine implements Animal {

    private String noise = "bark bark";

    public Dog(String color) {
        super(color);
    }

    public String getNoise() {
        return this.noise;
    }
}
