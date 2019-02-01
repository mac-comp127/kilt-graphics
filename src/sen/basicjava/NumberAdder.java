package sen.basicjava;

import java.util.Scanner;

/*
 * An example program to collect two numbers from the user,
 * add them together and print out the input.
 */
public class NumberAdder {
    public static void main(String args[]) {
        System.out.println("This program adds two user-specified numbers.");

        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the first number to add: ");
        int x = scan.nextInt();

        System.out.print("Enter the second number to add: ");
        int y = scan.nextInt();

        int answer = x + y;
        System.out.println(x + " + " + y + " = " + answer);
    }
}
