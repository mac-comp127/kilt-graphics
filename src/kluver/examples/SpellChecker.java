package kluver.examples;

import java.io.InputStream;
import java.util.Scanner;

public class SpellChecker {
    public static void main(String[] args) {
        Scanner words = openScanner("/SimpleNounList.txt");
        Scanner user = new Scanner(System.in);
        System.out.println("type a word.");

        String theWord = user.nextLine();
        theWord = theWord.replace("\n","");

        while (words.hasNextLine()) {
            String nextWord = words.nextLine();
            nextWord = nextWord.replace("\n","");
            if (nextWord.equals(theWord)) {
                System.out.println("yay!");
            }
        }

    }

    /**
     * Creates a scanner for a file located in the res folder.
     * Don't worry about how this works, it uses features of java we arn't
     * focusing on in this class.
     * @param resourceName the file name to open
     * @return a scanner that reads from that file, instead of the user.
     */
    private static Scanner openScanner(String resourceName) {
        InputStream is = SpellChecker.class.getResourceAsStream("/SimpleNounList.txt");
        return new Scanner(is);
    }
}
