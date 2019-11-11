/**
 * Abstract class to represent menu items
 *
 * @author Kevin Angstadt
 */

package activityStarterCode.menuMapActivity;

public abstract class MenuItem {
    private String name;
    private String description;
    private int calories;
    private double price;

    public MenuItem(String name, String description, int calories, double price) {
        this.name = name;
        this.description = description;
        this.calories = calories;
        this.price = price;
    }

    public abstract String getType();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return name + ", " + price + "\n" + description + " (" + calories + ")";
    }
}
