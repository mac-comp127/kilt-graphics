package kluver.section05;

import java.util.Scanner;

public class Scratch {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String word;
        do {
            System.out.println("What's your favorite animal?");
            word = scanner.nextLine();

        } while (!word.equalsIgnoreCase("duck"));
    }
}
