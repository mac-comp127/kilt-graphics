package cantrell.classes.roster2;

import java.util.List;

public class Roster {
    public static void main(String[] args) {
        List<Name> names = List.of(
            new Name("Marjane", "Satrapi", false),
            new Name("Hayao", "Miyazaki", true),
            new Name("Chuck", "Jones", false));

        printRoster(names);

        greetEveryone(names);
    }

    private static void greetEveryone(List<Name> names) {
        for (Name name : names) {
            System.out.println("Hi, " + name.getGivenName() + "!");
        }
    }

    private static void printRoster(List<Name> names) {
        for (Name name : names) {
            System.out.println(name.getFullName());
        }
    }
}














