package jackson.section2.basicJava;

import java.util.Scanner;

/**
 * Calculates the average letter grade for several assignments
 * Created by bjackson on 9/6/2016.
 */
public class LetterGrade {

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter a grade: ");
        double grade1 = scan.nextDouble();
        System.out.print("\nEnter a grade: ");
        double grade2 = scan.nextDouble();
        System.out.print("\nEnter a grade: ");
        double grade3 = scan.nextDouble();

        double avg = (grade1+grade2+grade3)/3;
        System.out.println("Your average was: " + avg );
        if (avg >=90.0){
            System.out.println("You got an A!");
        }
        else if (avg <90.0 && avg >= 80.0){
            System.out.println("You got a B.");
        }
        else {
            System.out.println("You should try harder.");
        }

    }
}
