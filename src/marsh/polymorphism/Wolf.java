package marsh.polymorphism;

public class Wolf extends Canine implements Animal {


    private String noise = "arooooo";
    private int numKills = 0;

    public Wolf(String color, int numKills) {
        super(color);
        this.numKills = numKills;

    }


    @Override
    public String getNoise() {
        return this.noise;
    }
}
