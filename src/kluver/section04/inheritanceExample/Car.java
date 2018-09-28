package kluver.section04.inheritanceExample;

public class Car extends Vehicle {
    private String color;

    public Car(String license, String color) {
        super(license);
        this.color = color;
    }

    public Car(String license) {
        this(license, "Green"); // the default car is green.
    }

    @Override
    public String toString() {
        return "Car{color='" + color + "'license='" + getLicense() + "'}";
    }

    public static void main(String[] args) {
        String validLicense = "0134285433";
        String invalidLicense = "I'm bob the builder, you can't sue me!";
        Vehicle moped = new Vehicle(validLicense);
        Car convertible = new Car(invalidLicense, "RED");

        moped.goForADrive();
        convertible.goForADrive();
    }
}















