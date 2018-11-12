package kluver.section05;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        List<String> theList = new ArrayList<>();

        // add some things
        theList.add("dog");
        theList.add("cat");
        theList.add("wolf");
        //printList1(theList);

        // add some more things
        theList.add(1, "rat");
        theList.add(1, "bunny");
        //printList2(theList);

        //remove some things
        theList.remove(0);
        theList.remove(1);
        theList.remove(2);
        printList3(theList);
    }

    private static void printList1(List<String> theList) {
        System.out.println(theList);
    }

    private static void printList2(List<String> theList) {
        for(int i = 0; i<theList.size(); i++) {
            System.out.println(theList.get(i));
        }
    }

    private static void printList3(List<String> theList) {
        for(String s : theList) {
            System.out.println(s);
        }
    }

    private static void printList4(List<String> theList) {
        Iterator<String> it = theList.iterator();
        while(it.hasNext()) {
            String next = it.next();
            System.out.println(next);
        }
    }
}
