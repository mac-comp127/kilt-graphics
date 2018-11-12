package cantrell.classExamples.listImplementations;


public class ListTests {
    public static void main(String[] args) {
        System.out.println("–––––– SimpleLinkedList ––––––");
        testList(new SimpleLinkedList<>());
        System.out.println();

        System.out.println("–––––– SimpleArrayList ––––––");
        testList(new SimpleArrayList<>());
        System.out.println();
    }

    private static void testList(SimpleList<String> list) {
        list.add("hedy");
        list.add("lamarr");  // https://en.wikipedia.org/wiki/Hedy_Lamarr
        list.add("is");
        list.add("awesome");

        // Now we want to print each item in the list. How?

        // This works, but it is O(n^2) for linked lists — yikes!

        System.out.println("––– Using get(n):");
        for(int n = 0; n < list.size(); n++) {
            System.out.println(list.get(n));
        }

        // This would require exposing Node, which is an implementation detail
        // of linked list — also yikes!
        //
        // for(Node<String> node = list.head; node != null; node = node.getNext()) {
        //    System.out.println(node.getElement());
        //}

        // The solution? Iterators!

        System.out.println("––– Using an iterator:");
        for(SimpleIterator<String> iterator = list.iterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }

        // When you use Java’s for-each loop, like this:
        //
        //     for(String item : names) { .... }
        //
        // ...Java translates it into code nearly identical to the loop above, except with
        // Java’s full-sized Iterator interface instead of our SimpleIterator.
    }
}

