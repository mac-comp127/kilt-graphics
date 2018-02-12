package kluver.examples.inheritance;

import java.util.Scanner;

public class MadLibBase {

    private Scanner scan;

    public MadLibBase() {
        scan = new Scanner((System.in));
    }

    protected String getWord(String prompt) {
        System.out.print(prompt+": ");
        return scan.nextLine();
    }

    protected String getVerb() {
        return getWord("a verb");
    }

    protected String getNoun() {
        return getWord("a noun");
    }

    protected String getName() {
        return getWord("a name");
    }

    protected String getAdjective() {
        return getWord("an adjective");
    }

    protected String getAdverb() {
        return getWord("an adverb");
    }
}
