package marsh.arrays;

public class Horse implements Animal {

    private String name;

    public Horse(String name) {
        this.name = name;
    }

    public String vocalize() {
        return "Neigh!";
    }

    public String getName() {
        return name;
    }
}
