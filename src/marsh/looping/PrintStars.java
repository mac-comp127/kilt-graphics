package marsh.looping;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PrintStars {

    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("This program prints a simple triangle of stars (*)");

        Scanner scan;
        //scan = new Scanner(System.in);

        scan = new Scanner(new File("124-shared/src/marsh/looping/number.txt"));

        int height;

        //System.out.print("Enter a number of rows: ");
        height = scan.nextInt();

        System.out.println("Read " + height + " from file number.txt");

        for (int row = 1; row <= height; row++) {
            for (int j = 0; j < row; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }
}
