package sen.expr;

import java.util.Scanner;

public class Squarer {
    public static double doSquare(double x) {
        return x * x;
    }

    public static void main(String [] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a decimal:");
        double x = in.nextDouble();
        System.out.println("The square of " + x + " is " + doSquare(x));
    }
}
