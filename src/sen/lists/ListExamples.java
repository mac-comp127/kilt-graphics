package sen.lists;

import java.util.ArrayList;
import java.util.List;

public class ListExamples {
    public static void main(String [] args) {
        List<String> letters = List.of("a", "b", "c");
        System.out.println("You just created a list like: " + letters.toString());
        System.out.println("The first thing is : " + letters.get(0));
//        letters.add("d");
        List<String> letters2 = new ArrayList<>(letters);
        letters2.add("d");
        System.out.println("You just created a list like: " + letters2.toString());
        System.out.println("Original list: " + letters.toString());

        List<Integer> numbers =  List.of(1, 2, 3);
    }
}
