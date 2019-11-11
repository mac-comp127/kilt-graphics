/**
 * A class to run an interactive restaurant menu
 *
 * @author Kevin Angstadt
 */

package activityStarterCode.menuMapActivity;

import java.text.DecimalFormat;
import java.util.*;
import java.util.List;

public class MenuMain {

    public static void main(String[] args) {

        Map<String, MenuItem> menu = new HashMap<String, MenuItem>();
        List<MenuItem> customerOrder = new ArrayList<MenuItem>();

        MenuItem a1 = new AppetizerMenuItem("Shrimp Cocktail", "Yummy beyond belief!", 276, 9.12);
        MenuItem a2 = new AppetizerMenuItem("Spring Rolls", "Only the freshest vegetables, deepfried", 300, 5.19);
        MenuItem e1 = new EntreeMenuItem("Black Bean Burger", "Hand-made patty, vegetarian", 750, 15.75);
        MenuItem e2 = new EntreeMenuItem("Fish of the Day", "Caught fresh every day", 576, 25.17);
        MenuItem d1 = new DessertMenuItem("Apple Pie", "Served with ice cream", 540, 9.88);
        MenuItem d2 = new DessertMenuItem("Brownie", "A huge slab of chockolate", 700, 7.88);

        // TODO 1 insert the menu Items above into the menu


        // TODO 4 generate the byType Map
        Map<String, List<MenuItem>> byType = new HashMap<>();

        // begin user prompts
        System.out.println("Enter a command to perform an action. Type \'help\' to see the list of commands, and \'quit\' to exit.");
        System.out.println("There are " + Integer.toString(menu.size()) + " items on the menu.");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while(!input.equalsIgnoreCase("quit")) { // quit to exit

            if(input.equalsIgnoreCase("help")) { // print list of commands
                System.out.println("The following commands are accepted:");
                System.out.println("\'menu\': list all food items on the menu");
                System.out.println("\'appetizers\': list all appetizers on the menu");
                System.out.println("\'entrees\': list all entrees on the menu");
                System.out.println("\'desserts\': list all desserts on the menu");
                System.out.println("\'order [name]\': add the food item [name] to your order");
                System.out.println("\'receipt\': print a list of the items on the order");
                System.out.println("\'total\': print the total of the customer's order (bonus)");
                System.out.println("\'remaining\': print all menu items that have not been ordered (bonus)");
                System.out.println("\'quit\': exit the program");

            } else if(input.equalsIgnoreCase("menu")) {

                // TODO 2 update printing to match the required format
                System.out.println(menu);

            } else if(input.matches("order .+")) {
                String request = input.substring(6);

                /*
                 * TODO 3 add the requested menu item to the customerOrder list
                 * If the item is not on the menu, print an error message, otherwise
                 * print a message indicating the the item was added.
                 */

                System.out.println("Could not add " + request + " to the order");

            } else if(input.equalsIgnoreCase("receipt")) {

                if(customerOrder.isEmpty()) {
                    System.out.println("There are no items in the order");
                } else {
                    DecimalFormat df = new DecimalFormat("0.00");
                    System.out.println("Order receipt:");
                    for(MenuItem itm : customerOrder) {
                        System.out.println("  " + itm.getName() + ", $" + df.format(itm.getPrice()));
                    }
                }

            } else if(input.equalsIgnoreCase("total")) {

                /*
                 * TODO 5 add support for printing the total of the customer's order
                 * Can you calculate this with one line of code?
                 */

                System.out.println("Not implemented");

            } else if(input.equalsIgnoreCase("remaining")) {

                /*
                 * TODO 6 print a list of menu items that are not yet on the order
                 */

                System.out.println("The following items are not in the order:");

                System.out.println("Not implemented");

            } else if(input.equalsIgnoreCase("appetizers")){

                if(byType.isEmpty()) {
                    System.out.println("No appetizers found");
                } else {
                    for (MenuItem itm : byType.get("Appetizer")) {
                        System.out.println(itm);
                    }
                }

            } else if(input.equalsIgnoreCase("entrees")){

                if(byType.isEmpty()) {
                    System.out.println("No entrees found");
                } else {
                    for (MenuItem itm : byType.get("Entree")) {
                        System.out.println(itm);
                    }
                }

            } else if(input.equalsIgnoreCase("desserts")){

                if(byType.isEmpty()) {
                    System.out.println("No desserts found");
                } else {
                    for (MenuItem itm : byType.get("Dessert")) {
                        System.out.println(itm);
                    }
                }

            } else { // invalid command
                System.out.println("Enter a valid command");
            }

            System.out.println("\nEnter a command to perform an action. Type \'help\' to see the list of commands");
            input = sc.nextLine();
        }
    }
}
