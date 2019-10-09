package cantrell.interfaces.without;

public class Cup {
    private Water waterContents;
    private Tea teaContents;
    private Coffee coffeeContents;

    public void fill(Water water) {
        this.waterContents = water;
        this.teaContents = null;
        this.coffeeContents = null;
    }

    public void fill(Tea tea) {
        this.teaContents = tea;
        this.waterContents = null;
        this.coffeeContents = null;
    }

    public void fill(Coffee coffee) {
        this.teaContents = null;
        this.waterContents = null;
        this.coffeeContents = coffee;
    }

    public boolean contentsHot() {
        if (waterContents != null) {
            return waterContents.getTemperature() > 50;
        }
        if (teaContents != null) {
            return teaContents.getTemperature() > 50;
        }
        if (coffeeContents != null) {
            return coffeeContents.getTemperature() > 50;
        }
        return false;
    }

    public static void main(String[] args) {
        Cup cup = new Cup();
        Water water = new Water();
        cup.fill(water);

        Tea tea = new Tea();
        cup.fill(tea);
    }
}
