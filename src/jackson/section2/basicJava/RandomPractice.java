package jackson.section2.basicJava;

import java.util.Random;
import java.util.Scanner;

public class RandomPractice {

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        Random rand = new Random();

        System.out.println("Enter a positive number: ");
        int max = input.nextInt();
        int randomVal = rand.nextInt(max)+1;

        System.out.println("Your random number is "+rand.nextInt(max));
    }
}
