package kluver.examples;

import java.awt.geom.Point2D;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayStuff {

    public static void main(String[] args) {
        String[] words = new String[200];
        fillInArray(words); // passes an array to a method
        // Question - can fillInArray effect the variables or data
        // in main, and if so why?

        int longestWordIndex = 0;
        for(int i = words.length-1; i>=0; i--) {
            String longWord = words[longestWordIndex];
            String word = words[i];
            if(word.length() > longWord.length()) {
                longestWordIndex = i;
            }
        }
        System.out.println("the last longest word is: "+words[longestWordIndex]);
    }

    private static void fillInArray(String[] words) {
        // this is some java-magic to open the file "SimpleNounList" from the
        // /res folder in a way that should work on everyone's computer.
        // The InputStream class is the class for System.in as well as all other
        // streams of data into the program (files, internet connections, etc.)
        InputStream nouns = ArrayStuff.class.getResourceAsStream("/SimpleNounList.txt");
        // Just like you can make a scanner from system.in you can make a scanner
        // from any other InputStream.
        Scanner nounScan = new Scanner(nouns);

        // the file has 200 words, so this should be fine.
        // In general this isn't the best way to do this, but its good enough for now.
        for(int i = 0; i<words.length; i++) {
            words[i] = nounScan.next(); // reads the next word.
        }
    }
}
