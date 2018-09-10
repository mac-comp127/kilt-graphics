package kluver.section05;

import java.util.Random;
import java.util.Scanner;

public class DiceRoller {
    public static void main(String[] args) {

        Random rng = new Random();

        Scanner scan = new Scanner(System.in);
        String doneYet = "nope";

        while(! doneYet.equalsIgnoreCase("yes")) {
            int accum = 0;
            for (int i = 0; i < 5; i++) {
                int die = rng.nextInt(6) + 1;
                accum = accum + die;
            }
            System.out.println(accum);

            System.out.println("done yet?");
            doneYet = scan.nextLine();
        }
    }
}
