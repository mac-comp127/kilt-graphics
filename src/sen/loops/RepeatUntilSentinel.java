package sen.loops;

import java.util.Scanner;

public class RepeatUntilSentinel {
    public static void main(String [] args) {
        System.out.println("This program adds up numbers.");
        Scanner scan = new Scanner(System.in);
        int sum = 0;

        while (true) {
            System.out.print("Type an integer to include in the sum or -1 to stop: ");
            int num = scan.nextInt();
            if (num == -1) {
                break;
            }
            sum += num;
        }
        System.out.println("sum is " + sum);
    }
}
