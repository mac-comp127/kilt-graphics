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

        int x;

        //System.out.print("Enter a number of rows: ");
        x = scan.nextInt();

        System.out.println("Read " + x + " from file number.txt");

        for (int i = 1; i <= x; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            System.out.println("");
        }
    }
}
