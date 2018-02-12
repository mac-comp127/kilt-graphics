package kluver.examples;

import java.util.Scanner;

public class NumberSummarizerMain {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        NumberSummarizer xSumm = new NumberSummarizer();
        NumberSummarizer ySumm = new NumberSummarizer();

        System.out.println("Please type points. Type a negative x or y to stop.");
        int lastX;
        int lastY;
        do {
            System.out.println("next point");
            System.out.print("x = ");
            lastX = scan.nextInt();
            System.out.print("y = ");
            lastY = scan.nextInt();
            xSumm.addNumber(lastX);
            ySumm.addNumber(lastY);
        } while (lastX>=0 && lastY>=0);

        System.out.println("X: "+xSumm.getSmallestNum()+" to "+xSumm.getLargestNum());
        System.out.println("Y: "+ySumm.getSmallestNum()+" to "+ySumm.getLargestNum());
        System.out.println("Largest number we saw overall: "+NumberSummarizer.getLargestNumberEver());


    }
}













