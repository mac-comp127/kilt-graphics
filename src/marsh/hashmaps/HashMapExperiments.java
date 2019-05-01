package marsh.hashmaps;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class HashMapExperiments {
    public static void main(String[] args) {

        // –––––– Side lesson on autoboxing / auto-unboxing ––––––

        // int is a primitive type. It can't be null.
        //
        // Integer is a class type. Integer objects contain an int, and nothing else.
        // Integer is thus a "box" that holds an int. But it is a class type, so
        // it CAN be null.

        Integer x1 = 7; // Assigning primitive int to Integer works
        int x2 = x1;    // This works too: Java automatically transforms rhs to x1.intValue()

        // Integer x1 = null;  // This works: Integer can be null.
        // int x2 = x1;        // Kaboom! NPE! Java runs this as null.intValue()

        // –––––– Main lesson: Using Map, using custom equals() / hashCode() ––––––

        Person sally = new Person("Sally", "Jones");
        Person fred = new Person("Fred", "Smith");

        // Person can be a key in a map!
        Map<Person,Integer> ages = new HashMap<>();
        ages.put(sally, 50);
        ages.put(fred, 12);

        // Getting one person's age
        System.out.println("Sally age: " + ages.get(sally));
        // Remember that this did NOT work until we implemented Person.hashCode()!

        // Printing all the ages (see implementation below):
        printAllAges(ages);

        // –––––– Changing a key object ––––––

        System.out.println("⚠️☣️☢️ Changing Sally’s name ☢☣⚠");
        sally.setFirstName("Ultrasally");
        ages.put(sally, 6000);

        System.out.println("Her name has changed, but she's still the same object ... right?");
        System.out.println("Ultrasally’s age: " + ages.get(sally));

        System.out.println("Huh? What about the other person in the map?");
        System.out.println("Fred’s age: " + ages.get(fred));

        System.out.println("Wat?! Is Ultrasally now gone from the map? Or is she still in there as Sally?");
        printAllAges(ages);

        System.out.println("Wait ... the slower and faster ways printed something different.");
        System.out.println("Why? Is Ultrasally in there or not?? IS EVERYTHING A LIE?!?");

        interactiveAgeQuery(ages);
    }

    @SuppressWarnings("InfiniteLoopStatement")
    private static void interactiveAgeQuery(Map<Person, Integer> ages) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String firstName = scanner.nextLine().trim();
            String lastName = scanner.nextLine().trim();

            // Is it OK to make a _new_ Person object and use it as a key?
            // Yes, because we implemented equals() and hashCode() to look up people by name!
            Person person = new Person(firstName, lastName);

            System.out.println("Age for " + person + " is " + ages.get(person));
        }
    }

    private static void printAllAges(Map<Person, Integer> ages) {
        // This works:

        System.out.println("All ages (slightly slower way)");
        for(Person person : ages.keySet()) {
            System.out.println("    " + person + "’s age is " + ages.get(person));
        }

        // ...but we are calling get() for every person, which makes Java go back
        // inside the map a second time to get each value after it was just there
        // getting the keys for us!

        // This is a bit better:

        System.out.println("All ages (slightly faster way):");
        for(Map.Entry<Person,Integer> entry : ages.entrySet()) {
            System.out.println("    " + entry.getKey() + "’s age is " + entry.getValue());
        }

        // Using entrySet() lets Java walk through the hash table and get both
        // the value and key at the same time, and give them both to us at once.
    }
}





