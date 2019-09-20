package cantrell.listPractice;

import java.util.ArrayList;
import java.util.List;

/**
 * Several possible solutions to problems from activityStarterCode.listPractice.ListFormatting
 */
@SuppressWarnings({"StringConcatenationInLoop", "WeakerAccess"})
public class ListFormattingSolutions {

    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

    /**
     * Returns a new list with each item prefixed with the strings "1. ", "2. ", etc.
     *
     * HINT: Use a for-each loop, and create a variable outside the loop to keep
     *       track of which item number you’re on. (Think: why does the variable
     *       have to be declared and initialized _outside_ the loop? What
     *       happens if you move it inside?)
     */

    //
    // The tricky part: how do you know which item number you’re on?
    //
    // I like the first and fourth solutions the best. -PPC
    //

    // FIRST SOLUTION: Use a for-each loop, but with an int var to track the position
    //
    // This is a nice solution, and the one I thought you were most likely to end up with
    // during the activity.
    //
    public static List<String> numberEachItem_usingIntVar(List<String> items) {
        int itemNum = 1;
        List<String> result = new ArrayList<>();
        for (String item : items) {
            result.add(itemNum + ". " + item);
            itemNum++;
        }
        return result;
    }

    // SECOND SOLUTION: Use a three-part (“C-style”) for loop with list.get()
    //
    // This takes fewer lines of code, but not necessarily more readable — and list.get(n) can be
    // very slow for some kinds of lists. It’s usually better to use a for-each loop when you can.
    //
    public static List<String> numberEachItem_usingCStyleForLoop(List<String> items) {
        List<String> result = new ArrayList<>();
        for (int n = 0; n < items.size(); n++) {
            result.add((n + 1) + ". " + items.get(n));
        }
        return result;
    }

    // THIRD SOLUTION: Use indexOf() to get the item number
    //
    // This is clever, but it has a bug! It gives the wrong item number when the list contains the
    // same string in multiple positions, because indexOf() always returns the index of the _first_
    // match.
    //
    // (Also, calling indexOf() to search for the strings in the list over and over is very slow.)
    //
    public static List<String> numberEachItem_usingIndexOf_BROKEN(List<String> items) {
        List<String> result = new ArrayList<>();
        for (String item : items) {
            result.add((items.indexOf(item) + 1) + ". " + item);  // 🐞🐜 BUG! 🐜🐞 Breaks for duplicate items
        }
        return result;
    }

    // FOURTH SOLUTION: Use result.size() to get the item number
    //
    // This is perhaps a bit too clever by half — but it is quite tidy and works well. And calling
    // size() on a list is always fast, so no performance concerns here!
    //
    public static List<String> numberEachItem_usingResultSize(List<String> items) {
        List<String> result = new ArrayList<>();
        for (String item : items) {
            result.add((result.size() + 1) + ". " + item);
        }
        return result;
    }

    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

    /**
     * Formats the items in the given list separated by commas and spaces, e.g.
     * "one, two, three".
     *
     * HINT: You can declare a String variable outside the loop, much like you
     *       declared an into outside the loop in the previous example.
     *       Initialize it to an empty string, repeatedly add to it inside the
     *       loop, then return it when you're done.
     */

    //
    // The tricky part: how do you know when you’re on the first item, so you don’t put a comma at
    // the very beginning? (Or on the last item if you’re adding the commas after the items in the
    // loop?)
    //

    // FIRST SOLUTION: Use a bool to track whether you’re on the first one
    //
    // This is wordy, but it works — and it’s often the best approach when you need to make the
    // first iteration of a for-each loop into a special case.
    //
    public static String formatWithCommas_usingBooleanToDetectFirst(List<String> items) {
        String result = "";
        boolean isFirst = true;
        for (String item : items) {
            if (isFirst) {
                isFirst = false;
            } else {
                result += ", ";
            }
            result += item;
        }
        return result;
    }

    // SECOND SOLUTION: Use the result length to know whether you’re at the start
    //
    // This is clever — the bad kind of clever! It breaks if the first string is an empty string:
    // formatWithCommas(List.of("", "a", "b")) should give ", a, b".
    //
    public static String formatWithCommas_usingResultLengthToDetectFirst_BROKEN(List<String> items) {
        String result = "";
        for (String item : items) {
            if (!result.isEmpty()) {  // 🐞🐜 BUG! 🐜🐞 Breaks if the first item is an empty string
                result += ", ";
            }
            result += item;
        }
        return result;
    }

    // THIRD SOLUTION: Use Java’s built-in libraries!
    //
    // The Java standard library API (that is, the code that always comes with Java that you can use
    // in your own program) already has a method that does exactly what we need!
    //
    // The point of this exercise was to practice using loops, but this would be by far the best
    // solution in a real app.
    //
    // Moral #1: Learn and use the standard library! It is full of useful things.
    //
    // Moral #2: Sometimes you don’t need to use a loop to process a whole list.
    //
    public static String formatWithCommas_usingJavaAPI(List<String> items) {
        return String.join(", ", items);
    }

    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

    /**
     * Formats the given items separated by commas and the word “and” so that
     * they could be used in an English sentence. Examples:
     *
     *    "one"
     *    "one and two"
     *    "one, two and three"
     *
     *  HINT: There is no way to do this without using several conditionals.
     *        Use the test to help you. After you have it working, see if you
     *        can simplify your solution at all.
     */

    //
    // The tricky part: so many special cases! How do you keep the code tidy?
    //

    // FIRST SOLUTION: Use an int to track the current index, counting up from zero
    //
    // This works, but the logic is hard to follow.
    //
    public static String formatGrammatically_countingUp(List<String> items) {
        String result = "";
        int itemNum = 0;
        for (String item : items) {
            if (items.size() > 1) {
                if (itemNum == items.size() - 1) {
                    result += " and ";
                } else if (itemNum > 0) {
                    result += ", ";
                }
            }
            result += item;
            itemNum++;
        }
        return result;
    }

    // SECOND SOLUTION: Use an int to track how many items are left, counting down
    //
    // This is much tidier than the first option! Two insights made the code cleaner:
    //
    // 1. counting down how many are left, so that the special cases are always 1 and 0, and
    // 2. appending the comma / "and" at the end of the loop instead of the beginning, which makes
    //    a one-item list no longer a special case.
    //
    public static String formatGrammatically_usingIntVar_countingDown(List<String> items) {
        String result = "";
        int itemsRemaining = items.size();
        for (String item : items) {
            result += item;

            itemsRemaining--;
            if (itemsRemaining > 1) {
                result += ", ";
            } else if (itemsRemaining == 1) {
                result += " and ";
            }
        }
        return result;
    }

    // THIRD SOLUTION: Use the APIs!
    //
    // Hey look: we can do this one without loops too! It’s not so nice that 0 and 1 are special
    // cases again, though. I still kind of like this one the best, but with this problem all the
    // answers feel a little messy.
    //
    public static String formatGrammatically_usingSublistAndJoin(List<String> items) {
        if (items.size() == 0) {
            return "";
        }
        if (items.size() == 1) {
            return items.get(0);
        }
        return String.join(", ", items.subList(0, items.size() - 1))
            + " and " + items.get(items.size() - 1);
    }

    // ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

    // GOOD BONUS CHALLENGE: Modify each of the formatGrammatically() solutions above to solve the
    // Oxford comma problem in ListFormatting. (Use the tests!)
}
