/**
 * DessertMenuItems represent food served after the main meal
 *
 * @author Kevin Angstadt
 */

package activityStarterCode.menuMapActivity;

public class DessertMenuItem extends MenuItem {
    public DessertMenuItem(String name, String description, int calories, double price) {
        super(name, description, calories, price);
    }

    public String getType() { return "Dessert"; }
}
