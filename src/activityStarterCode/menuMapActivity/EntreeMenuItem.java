/**
 * EntreeMenuItems are the main course for a dinner
 *
 * @author Kevin Angstadt
 */
package activityStarterCode.menuMapActivity;

public class EntreeMenuItem extends MenuItem {
    public EntreeMenuItem(String name, String description, int calories, double price) {
        super(name, description, calories, price);
    }

    public String getType() {
        return "Entree";
    }
}
