/**
 * AppetizerMenuItems are for the small dish at the beginning of a meal
 *
 * @author Kevin Angstadt
 */

package activityStarterCode.menuMapActivity;

public class AppetizerMenuItem extends MenuItem {
    public AppetizerMenuItem(String name, String description, int calories, double price) {
        super(name, description, calories, price);
    }

    public String getType() { return "Appetizer"; }
}
