package cantrell.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.*;

import static java.util.stream.Collectors.toList;

public class StreamInvestigationsSection04 {
    public static void main(String[] args) {
        List<String> items = List.of("one", "two", "three", "four", "five");

        // Iterative logic:

        List<String> newItems = new ArrayList<>();
        for (String item : items) {
            String s = item + "!!!";
            if (s.contains("e")) {
                newItems.add(
                    s.toUpperCase());
            }
        }
        System.out.println(newItems);

        // Stream equvalent:

        System.out.println(
            items.stream()
                .map(s -> s + "!!!")
                .filter(s -> !s.contains("e"))
                .map(String::toUpperCase)
                .collect(toList()));


        // Breaking it out step by step to see order of execution:

        Stream<String> step0 = items.stream();
        System.out.println("––––––––– step0: " + step0);

        Stream<String> step1 = step0.map(s -> {
            System.out.println("step0: " + s);
            return s + "!!!";
        });
        System.out.println("––––––––– step1: " + step1);

        Stream<String> step2 = step1.filter(s -> {
            System.out.println("step1: " + s);
            return !s.contains("e");
        });
        System.out.println("––––––––– step2: " + step2);

        Stream<String> step3 = step2.map(s -> {
            System.out.println("step2: " + s);
            return s.toUpperCase();
        });
        System.out.println("––––––––– step3: " + step3);

        List<String> result = step3.limit(1).collect(toList());
        System.out.println("––––––––– result: " + result);

        // Streams can be huge, or even infinite, as long as we don't ask for all of them:

        System.out.println(
            LongStream.range(0, 1000000000000000000L)
                .mapToObj(i -> "Thing " + i)
                .limit(10)
                .collect(Collectors.joining("\n")));
    }
}
