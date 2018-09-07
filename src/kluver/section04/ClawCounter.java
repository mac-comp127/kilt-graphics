package kluver.section04;

import java.util.Scanner;

/**
 * A class with a main function which will help you predict how many times you might get cut while cuddling clawed cats
 */
public class ClawCounter {

    /**
     * Main entry point to the program
     */
    public static void main(String args[]) {
        // Print an introduction
        System.out.println("Welcome to the claw counter!");
        // Art from http://www.asciiworld.com/-Cats-.html
        System.out.println("  A_A");
        System.out.println(" (-.-)");
        System.out.println("  |-|");
        System.out.println(" /   \\"); // The double slash (\\) represents the character '\'.
        System.out.println("|     |   __");
        System.out.println("|  || |  |  \\__");
        System.out.println(" \\_||_/_/");

        System.out.println(); // Note no input parametrs

        // Get the number of cats
        // Set up an object to help read input from the user
        Scanner fishdog = new Scanner(System.in);

        System.out.println("How many cats will you be playing with?");
        System.out.print(">> "); // Note - no "ln" on the print.
        int numCats = fishdog.nextInt();


        /* These would normally be at the top of the file, but I put them here for educational purposes */
        final int NUM_PAWS_PER_CAT = 4;
        final int NUM_CLAWS_PER_PAW = 4; // technically a cat's back paw has a fifth claw.

        int numPaws = numCats * NUM_PAWS_PER_CAT;
        int numClaws = numPaws * NUM_CLAWS_PER_PAW;

        // get cut rate
        System.out.println("What do you think the chance is of getting cut by each claw? (out of 100)");
        System.out.print(">> ");
        int cutPercent = fishdog.nextInt();


        double cutChance = cutPercent/100;
        double expectedCuts = numClaws*cutChance;
        System.out.println("There should be "+numCats+" cats with "+numPaws+" paws with "+numClaws+" claws.");
        System.out.println("Expect "+expectedCuts+" cuts");

        if (expectedCuts > 4) {
            System.out.println("This sounds like a bad plan");
        }
    }
}