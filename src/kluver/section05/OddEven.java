package kluver.section05;

import java.util.Scanner;

public class OddEven {
    public static int mod3(int number) {
        System.out.println("moo moo I'm a cow");
        return number % 3;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("gimmie>> ");
        int number = scan.nextInt();

        if ((number % 3) == 0) {
            System.out.println("so divisible!");
            System.out.println("your a winner");
        } else if (true || mod3(number) == 1) {
            System.out.println("so one more than divisble!");
        } else {
            System.out.println("so one less than divisble!");
            System.out.println("LOOSER");
        }
    }
}
