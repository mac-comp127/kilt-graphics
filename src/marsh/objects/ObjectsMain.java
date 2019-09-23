package marsh.objects;

import java.util.Scanner;

public class ObjectsMain {

    public static void main(String[] args) {

        Toaster terry = new Toaster();

        Scanner sc = new Scanner(System.in);

        System.out.println("What to toast?");
        String toast = sc.nextLine();
        System.out.println("How many?");
        int num = sc.nextInt();
        System.out.println("How hot?");
        int heat = sc.nextInt();

        boolean toasted = terry.toastThings(toast, num, heat);

        if(toasted) {
            System.out.print("Yay! Toasted!");
        } else {
            System.out.print("Not toasted!");
        }
    }
}
