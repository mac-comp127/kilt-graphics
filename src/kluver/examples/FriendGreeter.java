package kluver.examples;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class FriendGreeter {

    public static void main(String[] args) {
        Queue<String> message = new LinkedList<>();

        fillQueue(message);
        displayQueue(message);
    }

    private static void displayQueue(Queue<String> message) {
        while (! message.isEmpty()) {
            System.out.println(message.remove());

            // IGNORE ME!
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }
        }
    }

    /**
     * fills the message queue
     * AND!!!!! halts until the friendo hits enter.
     * @param message
     */
    private static void fillQueue(Queue<String> message) {
        Scanner scan = new Scanner(System.in);

        String theLine;
        do {
            theLine = scan.nextLine();
            message.add(theLine);
        } while (! "EOM".equalsIgnoreCase(theLine));

        for (int i = 0; i < 1000; i++) {
            System.out.println();
        }

        System.out.println("hit enter for a nice message, friendo!");
        scan.nextLine();
    }
}















