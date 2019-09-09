package marsh;

import java.util.Scanner;

public class Conditionals {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter a number: ");

        int x = scan.nextInt();

        int y = squareNum(x);

        System.out.println(y);

        System.out.println(greaterThanTen(y));

    }

    public static int squareNum(int square) {
        return square * square;
    }

    public static boolean greaterThanTen(int z) {
        if (z <= 10) {
            return false;
        } else {
            return true;
        }
    }
}
