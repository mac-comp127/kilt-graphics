package sen.lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListOfNames {
    public static void main(String [] args) {
        List<String> names = new ArrayList<>();
        names.add("Shilad");
        names.add("Brennan");
        names.add("Carter");
        names.add("Betsy");
        names.add("Nicole");
        names.add("Chris");
        names.add("Ted");
        names.add("Arthur");
        names.add("Henry");
        names.add("Nathan");
        names.add("Matthew");
        names.add("Shoua");
        names.add("Thy");
        names.add("Fed");
        names.add("Howard");
        names.add("Paul");

        Collections.shuffle(names);

        for (String name : names) {
            System.out.println("Name is " + name);
        }
    }
}
