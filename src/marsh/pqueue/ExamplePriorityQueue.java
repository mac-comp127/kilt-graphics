package marsh.pqueue;

import java.util.PriorityQueue;

public class ExamplePriorityQueue {
    public static void main(String args[]) {
        //Create PQ, no Comparator
        //PriorityQueue<String> pq = new PriorityQueue<>();
        //Create PQ, using Comparator
        PriorityQueue<String> pq = new PriorityQueue<>(new StringLengthComparator());

        //Fill PQ
        pq.add("red");
        pq.add("green");
        pq.add("purple");
        pq.add("yellow");
        pq.add("blue");

        System.out.println("Size is " + pq.size());

        // Print elements in PQ as they are removed
        int i = 1;
        while (pq.size() > 0) {
            System.out.println("Element " + i + " is: " + pq.remove());
            i++;
        }
    }
}
