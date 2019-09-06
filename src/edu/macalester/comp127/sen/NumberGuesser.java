package edu.macalester.comp127.sen;

import java.util.Random;
import java.util.Scanner;

public class NumberGuesser {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System. in);
        Random rand = new Random();
        int answer = rand.nextInt(9) + 1;

        while (true) {
            System.out.println("Guess a number between 1 and 10:");
            int guess = scanner.nextInt();
            if (guess < answer) {
                System.out.println("Your guess is too low");
            } else if (guess > answer) {
                System.out.println("Your guess is too high");
            } else {
                System.out.println("You got it!");
                break;
            }
        }
    }
}
