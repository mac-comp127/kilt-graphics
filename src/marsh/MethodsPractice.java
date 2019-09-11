package marsh;

import java.util.Scanner;

public class MethodsPractice {

    public static void main(String[] args) {

        // Using a static method (Math library)
        double x = Math.sqrt(25);

        System.out.println("SQRT 25: " + x);


        // Using non-static methods (Scanner and Strings)

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter a string: ");

        String str = scan.next();

        str = str.toLowerCase();

        System.out.println(str);


    }
}
