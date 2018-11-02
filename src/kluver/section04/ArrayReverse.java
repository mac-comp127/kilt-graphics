package kluver.section04;

import java.util.*;

public class ArrayReverse {

    public static void reverse(String[] theArray) {
        Deque<String> theStack = new ArrayDeque<>();

        for(int i = 0; i<theArray.length; i++) {
            theStack.push(theArray[i]);
        }
        for(int i = 0; i<theArray.length; i++) {
            theArray[i] = theStack.pop();
        }
    }

    public static void main(String[] args) {
        String[] theData = {"Rome", "Speghettiq", "cobra", "ccorgi", "Target (TM)"};

        System.out.println(Arrays.toString(theData));
        reverse(theData);
        System.out.println(Arrays.toString(theData));

    }
}
