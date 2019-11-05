package cantrell.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamInvestigationsSection03 {
    public static void main(String[] args) {
        List<String> items = List.of("one", "two", "three", "four", "five");

        // Iterative logic:

        List<Walrus> newItems = new ArrayList<>();
        for (String item : items) {
            String name = "Walrus " + item + "!";
            if (name.contains("e")) {
                newItems.add(
                    new Walrus(name));
            }
        }
        System.out.println(newItems);

        // Stream equvalent:

        System.out.println(
            items.stream()
                .map(item -> "Walrus " + item + "!")
                .filter(s -> s.contains("e"))
                .map(Walrus::new)
                .collect(toList()));

        // Breaking it out step by step to see order of execution:

        Stream<String> step0 = items.stream();
        System.out.println("––––––––––––– step0: " + step0);
        Stream<String> step1 = step0.map(item -> {
            System.out.println("On step0, processing " + item);
            return "Walrus " + item + "!";
        });

        System.out.println("––––––––––––– step1: " + step1);
        Stream<String> step2 = step1.filter(s -> {
            System.out.println("On step1, processing " + s);
            return s.contains("e");
        });

        System.out.println("––––––––––––– step2: " + step2);
        Stream<Walrus> step3 = step2.map(name -> {
            System.out.println("On step2, processing " + name);
            return new Walrus(name);
        });

        System.out.println("––––––––––––– step3: " + step3);

        List<Walrus> result = step3.collect(toList());

        System.out.println("––––––––––––– result: " + result);

        // Streams can be huge, or even infinite, as long as we don't ask for all of them:

        System.out.println(
            LongStream.range(0, 1000000000000000000L)
                .mapToObj(i -> "Thing " + i)
                .limit(10)
                .collect(Collectors.joining("\n")));
    }
}

class Walrus {
    private String name;

    public Walrus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Walrus{"
            + "name='" + name + '\''
            + '}';
    }
}








