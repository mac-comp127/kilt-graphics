package cantrell.classes.roster0;

import java.util.List;

public class Roster {
    public static void main(String[] args) {
        List<String> givenNames = List.of("Marjane", "Hayao", "Chuck");
        List<String> familyNames = List.of("Satrapi", "Miyazaki", "Jones");

        printRoster(givenNames, familyNames);

        greetEveryone(givenNames);
    }

    private static void greetEveryone(List<String> givenNames) {
        for (String name : givenNames) {
            System.out.println("Hi, " + name + "!");
        }
    }

    private static void printRoster(List<String> givenNames, List<String> familyNames) {
        for (int i = 0; i < familyNames.size(); i++) {
            System.out.println(givenNames.get(i) + " " + familyNames.get(i));
        }
    }
}
