package kluver.section05;

import kluver.section04.Dog;

import java.util.*;

public class Scratch {

    public static void main(String[] args) {
        Deque<String> theStack = new ArrayDeque<>();

        theStack.push("zombie");
        theStack.push("bat");
        theStack.push("ghost");
        theStack.push("cat");
        theStack.push("spooky");
        theStack.push("bills");
        theStack.push("vampires");
        theStack.push("creepy");
        theStack.push("haunted doll");


        while (!theStack.isEmpty()) {
            System.out.println(theStack.pop());
        }

    }
}
