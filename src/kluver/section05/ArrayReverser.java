package kluver.section05;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class ArrayReverser {
    public static void main(String[] args) {
        String[] input = {"Reggi", "Crombulent", "dietematiousq", "speghettiq", "watermelon"};
        System.out.println(Arrays.toString(input));
        reverse(input);
        System.out.println(Arrays.toString(input));
    }

    public static void reverse(String[] theArray) {
        Deque<String> theStack = new LinkedList<>();

        for (int i = 0; i < theArray.length; i++) {
            theStack.push(theArray[i]);
        }
        for (int i = 0; i < theArray.length; i++) {
            theArray[i] = theStack.pop();
        }
    }
}
