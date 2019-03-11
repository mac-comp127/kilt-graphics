package marsh.arrays;

public class Cow implements Animal {

    private String name;
    private boolean adult = false;

    public Cow(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String vocalize() {
        return "Moo!!";
    }

    @Override
    public String[] dropOnKill() {
        String[] drops = new String[2];
        drops[0] = "leather";
        drops[1] = "beef";

        return drops;
    }

    @Override
    public void growUp() {
        adult = true;

    }

    public boolean isAdult() {
        return adult;
    }
}
