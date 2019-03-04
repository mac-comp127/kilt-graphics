package marsh.arrays;

public class Cow implements Animal {

    private String name;

    public Cow(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String vocalize() {
        return "Moo!!";
    }
}
