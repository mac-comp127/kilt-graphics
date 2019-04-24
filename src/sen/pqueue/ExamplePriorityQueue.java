package sen.pqueue;

import java.util.PriorityQueue;

public class ExamplePriorityQueue {
    public static void main(String args[]) {
        PriorityQueue<String> pq = new PriorityQueue<>(new StringLengthComparator());
        pq.add("red");
        pq.add("green");
        pq.add("purple");
        pq.add("yellow");
        pq.add("blue");
        System.out.println("size is " + pq.size());
        System.out.println("first element is " + pq.peek());
        while (pq.size() > 0) {
            System.out.println("Next element is: " + pq.remove());
        }
    }
}
