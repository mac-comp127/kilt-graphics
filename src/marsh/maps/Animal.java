package marsh.maps;

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

    public String getColor() {
        return color;
    }
}
