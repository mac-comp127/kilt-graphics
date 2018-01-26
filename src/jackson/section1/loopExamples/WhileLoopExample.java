package jackson.section1.loopExamples;

import java.util.Scanner;

/**
 * Example of a repeat until sentinel loop
 * Created by bjackson on 9/9/2016.
 */
public class WhileLoopExample {

    public static final String SENTINEL = "STOP";

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        int sum = 0;

        String input = scan.nextLine();
        // Note the use of .equals() rather than ==. This is because we are comparing strings which are a class.
        while(!input.equals(SENTINEL)){
            sum += input.length();
            input = scan.nextLine();
        }
        System.out.println("You typed "+sum+" letters");


    }
}