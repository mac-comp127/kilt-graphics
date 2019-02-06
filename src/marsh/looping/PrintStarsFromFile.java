package marsh.looping;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PrintStarsFromFile {


    public static void main (String[] args) throws FileNotFoundException {
        System.out.println("This program prints a simple triangle of stars (*)");

        // Read filename from command line
        Scanner scan;

        scan = new Scanner(System.in);

        System.out.print("Enter a filename: ");
        String filename = scan.nextLine();

        File file = new File(filename);

        scan = new Scanner(file);


        int height = 0;

        // Code to print stars
        while (scan.hasNext()) {
            height = scan.nextInt();
            System.out.println("Read " + height + " from number.txt");

            for (int row = 1; row <= height; row++) {
                for (int j = 0; j < row; j++) {
                    System.out.print("*");
                }
                System.out.println("");
            }
        }

    }
}
