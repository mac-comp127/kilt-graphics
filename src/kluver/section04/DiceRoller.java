package kluver.section04;

import java.util.Random;
import java.util.Scanner;

public class DiceRoller {

    public static void main(String[] args) {
        Random rng = new Random();

        Scanner scan = new Scanner(System.in);
        String doneYet = "no";

        while(! doneYet.equalsIgnoreCase("yes")) {
            int accum = 0;
            for (int i = 0; i < 10; i++) {
                accum = accum + rng.nextInt(6) + 1;
            }
            System.out.println(accum);

            System.out.println("done yet?");
            doneYet = scan.nextLine();
        }

    }

}
