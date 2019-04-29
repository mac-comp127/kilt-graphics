package marsh.maps;

public abstract class Animal {
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public abstract String vocalize();

    public String getName() {
        return name;
    }
}
