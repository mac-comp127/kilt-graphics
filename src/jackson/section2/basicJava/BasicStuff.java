package jackson.section2.basicJava;

import java.util.Scanner;

/**
 * This class represents the first part of basic activity 1
 */
public class BasicStuff {

    // Main is the method that java will call to start running the program.
    public static void main(String[] args){
        int age; // Declares a primitive variable
        Scanner scan = new Scanner(System.in); // Declares and initializes an object (i.e. an instance of a class)

        System.out.println("Enter your age:");
        age = scan.nextInt();
        System.out.println("Your age is "+age);
    }
}
