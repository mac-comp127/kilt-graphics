/**
 * A class to run an interactive library program.
 *
 * @author Abby Marsh Fall 2019
 */
package activityStarterCode.libraryActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryMain {

    /**
     * Helper method to check an item out from the library
     * TODO: once you have implemented Media, change this to accept any Media type!
     *
     * @param title title of item to check out
     * @param library list of library items
     * @return true if item checked out successfully, false otherwise
     */
    public static boolean checkOut(String title, List<Book> library) {
        for(Book book : library) {
            if(book.getTitle().equalsIgnoreCase(title)) {
                return book.checkOut();
            }
        }

        return false;
    }

    /**
     * Helper method to check an item in to the library
     * TODO: once you have implemented Media, change this to accept any Media type!
     *
     * @param title title of item to check in
     * @param library list of library items
     * @return true if item checked in successfully, false otherwise
     */
    public static boolean checkIn(String title, List<Book> library) {
        for(Book book : library) {
            if(book.getTitle().equalsIgnoreCase(title)) {
                return book.checkIn();
            }
        }

        return false;
    }

    public static void main(String[] args) {

        // create/populate library
        // TODO: make this a list of Media objects, once your interface has been completed
        List<Book> library = new ArrayList<>();

        library.add(new Book("American Gods", "Neil Gaiman"));
        library.add(new Book("The Yiddish Policeman's Union", "Michael Chabon"));
        library.add(new Book("Pond", "Claire-Louise Bennett"));
        library.add(new Book("The Dream Songs", "John Berryman"));
        library.add(new Book("The Dud Avocado", "Elaine Dundy"));

        // TODO: add Video objects to your library, once the class has been created
        // If you create additional Media classes, add those items too!


        // begin user prompts
        System.out.println("Enter a command to perform an action. Type \'help\' to see the list of commands, and \'quit\' to exit.");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while(!input.equalsIgnoreCase("quit")) { // quit to exit

            if(input.equalsIgnoreCase("help")) { // print list of commands
                System.out.println("The following commands are accepted:");
                System.out.println("\'list\': list all library items and whether they are available");
                System.out.println("\'checkout [title]\': checkout the item called [title]");
                System.out.println("\'checkin [title]\': checkin the item called [title]");
                System.out.println("\'quit\': exit the program");

            } else if (input.matches("checkout .+")) { // checking out an item
                if (checkOut(input.substring(9), library)) {
                    System.out.println("Checked out " + input.substring(9));
                } else {
                    System.out.println("Could not check out that title.");
                }

            } else if (input.matches("checkin .+")) { // checking in an item
                if(checkIn(input.substring(8), library)) {
                    System.out.println("Checked in " + input.substring(8));
                } else {
                    System.out.println("Could not check in that title.");
                }


            } else if (input.equalsIgnoreCase("list")) { // list all library items
                System.out.println("\n-----\nLibrary Inventory\n-----\n");

                // TODO: edit this to work for all Media items
                for(Book book : library) {
                    System.out.println(book.toString());
                }

            } else { // invalid command
                System.out.println("Enter a valid command");
            }

            System.out.println("\nEnter a command to perform an action. Type \'help\' to see the list of commands");
            input = sc.nextLine();
        }


    }
}
