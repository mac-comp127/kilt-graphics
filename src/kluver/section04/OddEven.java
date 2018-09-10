package kluver.section04;

import java.util.Scanner;

public class OddEven {

    public static boolean isAGoodBoy(int dog) {
        System.out.println("of course it is.");
        return true;
    }

    public static void main(String[] args) {
        Scanner scoobieDoo = new Scanner(System.in);

        System.out.println("numbers, what up?");
        System.out.print(">> ");
        int dog = scoobieDoo.nextInt();

        if (isAGoodBoy(dog) && (dog%3 == 0)) {
            System.out.println("that's a divisible  dog");
        } else if ((dog % 3) == 1) {
            System.out.println("That's one more than a divisble dog");
        } else {
            System.out.println("That's two more than a divisble dog");
        }
    }


}













