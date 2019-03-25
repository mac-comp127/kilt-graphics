package listPerformance;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * A skeleton class to benchmark array lists.
 *
 * @author Shilad
 */
public class ArrayListBenchmark {
    private List<String> list = new ArrayList<>();

    /**
     * This is an example of how to use this class.
     *
     * This test will benchmark the following operation:
     * Remove each element from the stop of a list until it is empty
     */
    @Test
    public void benchRemoveFromEnd() {

        int[] listSizes = new int[] {500000, 100000, 200000, 400000, 800000, 1600000, 3200000};
        int numTrials = 20; // number of trials for EACH list size

        for (int size : listSizes) {
            Timer t = new Timer();
            t.start();

            // Run each trial
            for (int j = 0; j < numTrials; j++) {
                // Fill the list, do not time this!
                t.pause();
                fillList(size);
                t.resume();

                // Remove all elements of the list until it is empty
                while (list.size() > 0) {
                    list.remove(list.size() - 1);
                }
            }

            // End the timer and print out the timing PER OPERATION
            double elapsed = t.stop() / (numTrials * size);
            System.out.println("getByIndex for " + size + " is " + elapsed);
        }
    }


    /**
     *
     * This test will benchmark the following operation:
     * Get all elements of the list using an index for each one.
     */
    @Test
    public void benchLoopViaIndex() {

        // TODO: initialize variables for list sizes and number of trials
        // You will need to adjust the list sizes based on how long things
        // take, but they should always increase by a factor of two.

        // For each list size:
        //      Run each trial:
        //          Fill the list to the appropriate size. DO NOT TIME THIS PART.
        //          Write a do-n-times loop that sums up the sizes of the strings
        //          in the list. You should use the list's get() method.
        //      Print out the time PER OPERATION.
    }

    /**
     *
     * This test will benchmark the following operation:
     * Get all elements of the list by using a for-each loop syntax
     */
    @Test
    public void benchLoopViaIterator() {

        // TODO: initialize variables for list sizes and number of trials
        // You will need to adjust the list sizes based on how long things
        // take, but they should always increase by a factor of two.

        // For each list size:
        //      Run each trial:
        //          Fill the list to the appropriate size. DO NOT TIME THIS PART.
        //          Write a for-each loop that sums up the sizes of the strings
        //          in the list. You should NOT use the list's get() method.
        //      Print out the time PER OPERATION.
    }

    /**
     *
     * This test will benchmark the following operation:
     * Repeatedly remove each element from the front of the list until it is empty.
     */
    @Test
    public void benchRemoveFromFront() {

        // TODO: initialize variables for list sizes and number of trials
        // You will need to adjust the list sizes based on how long things
        // take, but they should always increase by a factor of two.

        // For each list size:
        //      Run each trial:
        //          Fill the list to the appropriate size. DO NOT TIME THIS PART.
        //          Remove the first element of the list until it is empty
        //      Print out the time PER OPERATION.
    }

    /**
     * Repeatedly get the middle element from a list.
     */
    @Test
    public void benchGetInMiddle() {

        // TODO: initialize variables for list sizes and number of trials
        // You will need to adjust the list sizes based on how long things
        // take, but they should always increase by a factor of two.
        // For this test you may want to increase the number of trials.

        // For each list size:
        //      Run each trial:
        //          Get the length of the middle element in the list. Use the
        //          list's get() method.
        //      Print out the time PER OPERATION.
    }

    private void fillList(int numElems) {
        list.clear();
        if (list instanceof ArrayList) {
            ((ArrayList)list).ensureCapacity(numElems);
        }
        for (int i = 0; i < numElems; i++) {
            list.add("num is " + i);
        }
    }
}
