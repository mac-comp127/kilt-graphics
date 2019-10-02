package cantrell.classes.roster2;

import java.util.List;

public class Roster {
    public static void main(String[] args) {
        List<Person> people = List.of(
            new Person("Marjane", "Satrapi", false),
            new Person("Hayao", "Miyazaki", true),
            new Person("Chuck", "Jones", false));

        greetEveryone(people);

        System.out.println();
        System.out.println("Roster:");
        printRoster(people);
    }

    private static void printRoster(List<Person> people) {
        for (Person person : people) {
            System.out.println(person.getFullName());
        }
    }

    private static void greetEveryone(List<Person> people) {
        for (Person person : people) {
            System.out.println("Hi, " + person.getGivenName() + "!");
        }
    }
}





