package cantrell.classes.roster1;

import java.util.List;

public class Roster {
    public static void main(String[] args) {
        List<Name> names = List.of(
            new Name("Marjane", "Satrapi"),
            new Name("Hayao", "Miyazaki"),
            new Name("Chuck", "Jones"));

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
            System.out.println(name.getGivenName() + " " + name.getFamilyName());
        }
    }
}
