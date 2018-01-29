package kluver.examples;

import java.util.Scanner;

/**
 * An example showing how different types of loops can be used to look for user
 * input, and perform summations.
 */
public class SumRange {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Input the lower bound");
        System.out.print(">> ");
        int lower = input.nextInt();

        int upper;
        do {
            System.out.println("Input the upper bound, (>="+lower+")");
            System.out.print(">> ");
            upper = input.nextInt();
        } while (upper < lower);

        int sum = 0;
        for(int i = lower; i <= upper; i++) {
            sum = sum + i;
        }

        System.out.println("The sum from "+lower+" to "+upper+" is "+sum);
    }
}
