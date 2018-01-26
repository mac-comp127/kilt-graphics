package jackson.section1.loopExamples;

import java.util.Scanner;

/**
 * Created by bjackson on 9/6/2016.
 */
public class LetterGradeWhile {

    public static final int SENTINEL = -1;

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        /*
        System.out.print("Enter a grade: ");
        double grade1 = scan.nextDouble();
        System.out.print("\nEnter a grade: ");
        double grade2 = scan.nextDouble();
        System.out.print("\nEnter a grade: ");
        double grade3 = scan.nextDouble();
        */
        double total = 0;

        // Example using a do-n-times looping pattern avoid the code duplication that is commented out above.
        /*
        for(int i=0;i<NUMGRADES;i++){
            System.out.print("Enter grade "+i+": ");
            total += scan.nextDouble();
        }
        */

        int gradeCount = 0;
        double value = 0;
        while(true){
            System.out.println("Enter a grade or -1 to stop:");
            value = scan.nextDouble();
            if(value == SENTINEL){
                break;
            }
            total += value;
            gradeCount++;
        }
        double avg = (total)/gradeCount;



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
