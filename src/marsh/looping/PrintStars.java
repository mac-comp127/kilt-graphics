package marsh.looping;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class PrintStars {

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("This program prints a simple triangle of stars (*)");

        Scanner scan;
        scan = new Scanner(System.in);

        int height;

        System.out.print("Enter a number of rows: ");
        height = scan.nextInt();

        for (int row = 1; row <= height; row++) {
            for (int j = 0; j < row; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }
}
