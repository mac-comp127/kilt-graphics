package marsh.loops;

import java.util.*;

public class LoopStyles {

    public static void main(String[] args) {

        List<String> words = new ArrayList<>(List.of("these", "here", "are", "some", "words"));

        // For-Each Loop
        for(String w : words) {
            System.out.print(w + " ");
        }
        System.out.println();

        // C-Style For Loops
        for(int i = 0; i < words.size(); i++) {
            System.out.print(words.get(i));
        }
        System.out.println();

        // While Loops

        int j = 0;
//        while(j >= 0) {
//            System.out.println("Enter a number!");
//            Scanner scan = new Scanner(System.in);
//            j = scan.nextInt();
//
//        }

        ListIterator<String> itr = words.listIterator();
        while(itr.hasNext()) {
            itr.next();
            itr.remove();
            itr.add("hi");
        }
        System.out.print(words.toString());
    }
}
