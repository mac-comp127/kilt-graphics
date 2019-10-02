package cantrell.classes.roster1;

import java.util.List;

public class Roster {
    public static void main(String[] args) {
        List<Person> people = List.of(
            new Person("Marjane", "Satrapi"),
            new Person("Hayao", "Miyazaki"),
            new Person("Chuck", "Jones"));

        greetEveryone(people);

        System.out.println();
        System.out.println("Roster:");
        printRoster(people);
    }

    private static void printRoster(List<Person> people) {
        for (Person person : people) {
            System.out.println(person.getFamilyName() + " " + person.getGivenName());
        }
    }

    private static void greetEveryone(List<Person> people) {
        for (Person person : people) {
            System.out.println("Hi, " + person.getGivenName() + "!");
        }
    }
}





