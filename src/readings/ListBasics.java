package readings;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("ALL")
public class ListBasics {
    public static void main(String[] args) {
        // –––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
        section("Creating lists");

        // Here is a list of four items:
        List.of("one", "fish", "two", "fish");

        // What is a list? In Java, it:
        // - contains many values of the same type,
        // - has a consistent order, and
        // - can contain duplicates.

        // You can print a list:

        System.out.println(List.of("hither", "thither"));

        // To declare a list variable, you have to say what type of elements are
        // in the list using <...> notation:

        List<String> magicWords = List.of("hocus", "pocus", "abracadabra");
        System.out.println("magicWords = " + magicWords);

        // If the elements in the list are a primitive type, you have to use the
        // corresponding wrapper type. That essentially means spelling out the
        // name of the primitive type as a whole word and capitalizing it,
        // e.g. “int” → “Integer”:

        List<Integer> magicNumbers = List.of(2, 10, 18, 36, 54, 86);
        System.out.println("magicNumbers = " + magicNumbers);

        // –––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
        section("Looping over lists");

        // To do something with each element of a list, use a “for each” loop:

        for(String word : magicWords) {
            System.out.println("I say " + word + "!!");
        }

        // Things to note here:
        //
        // - The variable `word` gets a new value for each element of the loop.
        //
        // - You have to declare `word` just like you declare any other variable.
        //
        // - The variable doesn't exist outside of the for loop; you can't use it
        //   after the closing brace. The term for this is “scope,” as in,
        //   “the scope of `word` is the for loop.”

        // Note, however, that variables declared _outside_ the loop can be
        // visible inside it. Here the scope of sum extends outside the loop:

        int sum = 0;
        for(int number : magicNumbers) {
            sum += number;
        }
        System.out.println("sum = " + sum);

        // You can nest a loop inside another loop:

        List<String> people = List.of("sally", "fred");
        for(String person : people) {
            for(String word : magicWords) {
                System.out.println(person + " says " + word + "!");
            }
        }

        // But can you nest a list inside another list? A list of lists? Yes!

        System.out.println(
            List.of(                   // Two elements in this list, each of which is a list:
                List.of(1, 3, 5),      // • first element = list of 3 ints
                List.of(0, 2, 4, 6))); // • second element = list of 4 ints

        // Hmm, what if we want to assign a list of lists to a variable? We need
        // to say what the type is to declare the variable ... so what type is it?
        //
        // It's a list of lists of integers:

        List<List<Integer>> nestedList = List.of(List.of(1, 3, 5), List.of(0, 2, 4, 6));
        System.out.println("nestedList = " + nestedList);

        // –––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
        section("Getting parts of lists");

        // You can extract a single item from a list:

        String singleMagicWord = magicWords.get(1);
        System.out.println("singleMagicWord = " + singleMagicWord);

        // In this context, we call the number 1 the “index.” An index is a
        // number that identifies a position in a list. (Note that the plural of
        // “index” is “indices.”)
        //
        // List indices count from zero! This means that get(1) gets the _second_
        // element of the list. (What is that?)
        //
        // So if get(1) gives us the second element, how do we get the first?

        String firstMagicWord = magicWords.get(0);
        System.out.println("firstMagicWord = " + firstMagicWord);

        // What about the last element?

        String lastMagicWord = magicWords.get(2);
        System.out.println("lastMagicWord = " + lastMagicWord);

        // Yes, but what about getting the last element if we don't already know
        // exactly how many elements are in the list?
        //
        // Well, we can ask a list how big it is:

        System.out.println("size of magicWords = " + magicWords.size());

        // ...and that can give us the index of the last element:

        String lastMagicWordAgain = magicWords.get(magicWords.size() - 1);
        System.out.println("lastMagicWordAgain = " + lastMagicWordAgain);

        // Think about it: Why size() - 1 instead of just size()?
        //
        // OK then, what happens if we do this? Try running this main method and
        // see what happens:

        String willNotWork = magicWords.get(magicWords.size());

        // The error we get here is called an “exception.” It means “the code
        // stopped here because something unexpected happened.”
        //
        // Look carefully at the message Java prints when the exception happens.
        // Note that it gives you lots of helpful (if cryptic) information about
        // both _what_ happened and _where_ it happened.

        // Now comment out that broken line of code to let the program continue.

        // What if you want to find a particular word in the list? You can ask
        // for its index:

        int indexOfPocus = magicWords.indexOf("pocus");

        System.out.println("indexOfPocus = " + indexOfPocus);
        System.out.println("word at that position = " + magicWords.get(indexOfPocus));

        // –––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––––
        section("Mutability and immutability");

        // So far we’ve only read the information that was already in lists.
        // Java also gives us the ability to _change_ what's in a list! Here’s
        // how you add a new element to the end of the list:

        magicWords.add("expelliarmus");

        // Oops! What happened?! That code compiles. Why doesn't it run?
        //
        // (Comment out that line.)
        //
        // The answer is that in programming languages, there are two kinds of
        // values: “mutable” and “immutable.” (People also sometimes call these
        // “modifiable” and “unmodifiable,” particularly in Java.) Mutable
        // things can change, but immutable values are always the same. To do
        // computation with immutable values, you have to replace the old value
        // with a new one.
        //
        // You have already encountered immutable values in Java. Integers are
        // immutable; you can't make the number 2 grow bigger! Strings in Java
        // are immutable; to change a string, you have to make a new String.
        // This is what the String addition operator does:

        String thing0 = "Mac";
        String thing1 = thing0;
        System.out.println("thing0 = " + thing0);  // Now they're both "Mac"
        System.out.println("thing1 = " + thing1);

        thing1 += "alester";
        System.out.println("thing1 = " + thing1);  // Now it's the new value "Macalester", but
        System.out.println("thing0 = " + thing0);  // this one is still "Mac"

        // Are lists mutable or immutable in Java? The answer is: both! Some
        // lists are mutable, and some are not.
        //
        // When you make a list with List.of(…), it is immutable. But the List
        // add() method tries to modify the list, not create a new one. This is
        // why calling magicWords.add(…) failed above.
        //
        // If you want to make a new mutable list, the simplest and most common
        // way is this:

        List<String> mutableList = new ArrayList<>();
        System.out.println("mutableList = " + mutableList);

        // For now, you can think of “new ArrayList<>()” as meaning “a new empty
        // mutable list.” We will get into what each part of that syntax means
        // as the semester goes along.

        // Unlike the immutable list above, we can add elements to this one:

        mutableList.add("school bus");
        mutableList.add("rhinoceros");
        System.out.println("mutableList = " + mutableList);

        mutableList.add("kitchen sink");
        mutableList.add(2, "turbo unicorn");  // 2 here means “at index 2”
        System.out.println("mutableList = " + mutableList);

        // We can change elements:

        mutableList.set(1, "angry rhinoceros");
        System.out.println("mutableList = " + mutableList);

        // We can remove elements:

        mutableList.remove(3);            // Remove by position
        mutableList.remove("school bus"); // Remove by value
        System.out.println("mutableList = " + mutableList);

        // Remember when we got the exception above trying to add new magic
        // words? To do that, we'll need to create a mutable copy of the list.
        // Here is one way:

        List<String> magicWordsCopy = new ArrayList<>();
        for(String word : magicWords) {
            magicWordsCopy.add(word);
        }
        magicWordsCopy.add("expelliarmus");
        System.out.println("magicWordsCopy = " + magicWordsCopy);

        // But wait! This is unnecessarily complex. What we just did is so
        // common, Java gives us a helpful addAll() method to do it:

        List<String> betterMagicWordsCopy = new ArrayList<>();
        betterMagicWordsCopy.addAll(magicWords);  // nice!
        betterMagicWordsCopy.add("expelliarmus");
        System.out.println("betterMagicWordsCopy = " + betterMagicWordsCopy);

        // And in fact, creating a new list and then adding all the elements of
        // another list is so common there is yet _another_ shortcut for it!
        // You can put the list you want to copy right between the parentheses
        // of new ArrayList<>(…):

        List<String> bestMagicWordsCopy = new ArrayList<>(magicWords);  // nicer!
        bestMagicWordsCopy.add("expelliarmus");
        System.out.println("bestMagicWordsCopy = " + bestMagicWordsCopy);

        // There is a lot more to learn about lists in Java, but this is plenty
        // to get us started with some interesting activities and labs.
    }

    private static void section(String title) {
        System.out.println();
        System.out.println("–––––– " + title + " ––––––");
    }
}
