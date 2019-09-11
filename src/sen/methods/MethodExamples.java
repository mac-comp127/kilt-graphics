package sen.methods;

import java.util.Scanner;

public class MethodExamples {
    public static void main(String [] args) {
        System.out.println("The square root of 5 is " + Math.sqrt(5));
        String name = "Shilad";
        System.out.println("The lowercase version of your name is " + name.toLowerCase());
        Scanner input = new Scanner(System.in);
        String line = input.next();
        System.out.println("I read: '" + line + "'");
        if (line.equals("hello")) {
            System.out.println("Hey!!");
        }
    }
}
