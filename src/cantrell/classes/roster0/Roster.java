package cantrell.classes.roster0;

import java.util.List;

public class Roster {
    public static void main(String[] args) {
        List<String> givenNames  = List.of("Marjane", "Hayao",    "Chuck");
        List<String> familyNames = List.of("Satrapi", "Miyazaki", "Jones");

        greetEveryone(givenNames);

        System.out.println();
        System.out.println("Roster:");
        printRoster(givenNames, familyNames);
    }

    private static void printRoster(List<String> givenNames, List<String> familyNames) {
        for (int n = 0; n < givenNames.size(); n++) {
            System.out.println(givenNames.get(n) + " " + familyNames.get(n));
        }
    }

    private static void greetEveryone(List<String> givenNames) {
        for (String name : givenNames) {
            System.out.println("Hi, " + name + "!");
        }
    }
}





