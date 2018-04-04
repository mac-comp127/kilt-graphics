package cantrell.classExamples;

import java.util.Scanner;

public class LandShark {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.println("Who’s there?");
            String answer = in.nextLine();

            //  answer.toLowerCase() == "landshark"

            //  answer.toLowerCase().equals("landshark")

            if(answer.toLowerCase().equals("landshark")) {
                System.out.println("Go away!!!!!");
            } else {
                System.out.println("C’mon in.");
            }
        }
    }
}
