package cantrell.classExamples;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListTypes {
    public static void main(String[] args) {

        String[] wilma = new String[10];
        System.out.println(wilma.length);

        List<String> fred = new ArrayList<>();
        fred.add("one");
        fred.add("fish");
        fred.add("two");
        fred.add("fish");
        System.out.println(fred.size());

        List<String[]> zargle = new ArrayList<>();
        zargle.add(new String[10]);
        zargle.add(new String[20]);
        zargle.add(new String[3]);
        zargle.add(new String[1452345]);
        zargle.get(1)[4] = "hello";

        List<ArrayList<String>> blargle = new ArrayList<>();
        // pretend there is more code here
        blargle.get(1).set(4, "hello");

        List<
            List<
                List<
                    List<
                        List<
                            List<
                                List<
                                    List<
                                        String>>>>>>>> unreasonable = new ArrayList<>();

    }
}


class Utensil { }

class Spoon extends Utensil { }

class Fork extends Utensil { }










