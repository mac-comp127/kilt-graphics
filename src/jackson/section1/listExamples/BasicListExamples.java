package jackson.section1.listExamples;

import java.util.ArrayList;
import java.util.List;

public class BasicListExamples {

    public static void main(String[] args){
        List<String> words = new ArrayList<>();
        words.add("one");
        words.add("fish");
        words.add("two");
        words.add("fish");
        words.add("red fish");
        words.add("blue fish");

        System.out.println(words.get(2));
        System.out.println(words.size());

    }
}
