package activityStarterCode.listExperiments;

import activityStarterCode.listPerformance.ArrayListPerformance;
import activityStarterCode.listPerformance.LinkedListPerformance;


/**
 * In this class we perform experiments in which we gather times to perform
 * particular operations on the two implementations of Lists: ArrayList and LinkedList.
 *
 * By running these tests we verify experimentally our knowledge about the
 * running time of particular operations on these data structures.
 *
 * Created by shoop on 3/11/16.
 */
public class ListPerformanceExperiment {
    // size of smallest collection of Stings tested for O(1) operations
    private static final int INITIAL_SIZE_1 = 120000;
    // size of smallest collection of Stings tested for O(1) operations
    private static final int INITIAL_SIZE_N = 5000;

    private static final int TOTAL_MIDDLE_GETS = 50000;

    private ArrayListPerformance arrayPerformanceTest;
    private LinkedListPerformance linkedPerformanceTest;


    public ListPerformanceExperiment() {
        // PART 1
        // TODO: initialize the ArrayListPerformance object with no elements in it


        // PART 2
        // TODO: initialize the LinkedListPerfirmance object with no elements in it

        
        runTests();
    }


    /**
     *  Using increasing sizes of lists, perform experiments on ArrayList operations and
     *  LinkedList operations. Operations that should be O(1) time for each individual
     *  underlying operation are run on larger list sizes than operations that should be O(N).
     */
    private void runTests() {
        System.out.println("O(1) tests /////////////////////////////////////////////////////////////////////");

        // O(1) operations: verify that they are fast
        //
        // Will run successive tests using larger and larger sized Lists
        // The very large sizes are needed to get reasonable values for timings that are not too small.
        for (int test = 0; test < 7; test++) {

            int size = INITIAL_SIZE_1 * (int)Math.pow(2, test);  // increase size of list by powers of 2
            System.out.println("--------------------------------");
            System.out.println("TEST " + test + " : " + size + " elements");
            System.out.println("--------------------------------");
            perform1ArrayListTest(size);

            System.out.println("");

            /* Uncomment these when you are ready to benchmark Linked Lists
            perform1LinkedListTest(size);
            System.out.println("");
             */
        }

        System.out.println("");
        System.out.println("O(N) tests /////////////////////////////////////////////////////////////////////");

        // O(N) operations: verify that these take longer
        // Will run successive tests, but the list sizes will be smaller because there should be
        // more time taken for each operation.
        for (int test = 0; test < 5; test++) {
            int size = INITIAL_SIZE_N * (int)Math.pow(2, test);  // increase size of list by powers of 2
            System.out.println("--------------------------------");
            System.out.println("TEST " + test + " : " + size + " elements");
            System.out.println("--------------------------------");

            performNArrayListTest(size);

            System.out.println("");

            /* Uncomment these when you are ready to benchmark Linked Lists
            performNLinkedListTest(size);
            System.out.println("");
            */
        }

        // O(N) tests at same size as initial size for O(1) tests
        //      You may need to be patient for these
        int test = 5;
        int size = INITIAL_SIZE_1;
        System.out.println("--------------------------------");
        System.out.println("TEST " + test + " : " + size + " elements");
        System.out.println("--------------------------------");

        performNArrayListTest(size);
        System.out.println("");

        /* Uncomment these when you are ready to benchmark Linked Lists
        performNLinkedListTest(size);
        System.out.println("");
        */

        System.out.println("+++ All Tests Completed +++");

    }

    /**
     * Will execute the methods from arrayPerformanceTest whose underlying operation on
     * the ArrayList is O(1).
     *
     * @param listSize the size of list to be used for this particular set of tests
     */
    private void perform1ArrayListTest (int listSize) {
        double seconds;  // used to measure and record time for operations

        // TODO   Set up this next test/experiment on the ArrayListPerformance object


        // Part 1A. O(1) operations

        System.out.println("ArrayList ");
        // TODO Perform the ArrayList operation tests, where each operation should be O(1)



    }

    /**
     * Will execute the methods from arrayPerformanceTest whose underlying operation on
     * the ArrayList is O(N).
     *
     * @param listSize he size of list to be used for this particular set of tests
     */
    private void performNArrayListTest(int listSize) {
        double seconds;  // used to measure and record time for operations

        // Part 1B. O(N) operations

        // TODO   Set up this next test/experiment on the ArrayListPerformance object


        System.out.println("ArrayList");

        // TODO Perform the ArrayList operation tests, where each operation should be O(N)


    }

    /**
     * Will execute the methods from linkedPerformanceTest whose underlying operation on
     * the LinkedList is O(1).
     *
     * @param listSize he size of list to be used for this particular set of tests
     */
    private void perform1LinkedListTest(int listSize) {
        double seconds;  // used to measure and record time for operations

        // Part 2A. O(1) operations
        // TODO   Set up this next test/experiment on the LinkedListPerformance object


        System.out.println("LinkedList");

        // TODO Perform the LinkedList O(1) operation tests


    }

    /**
     * Will execute the methods from arrayPerformanceTest whose underlying operation on
     * the LinkedList is O(N).
     *
     * @param listSize he size of list to be used for this particular set of tests
     */
    private void performNLinkedListTest(int listSize) {
        double seconds;  // used to measure and record time for operations

        // Part 2B. O(N) operations
        // TODO   Set up this next test/experiment on the LinkedListPerformance object
        linkedPerformanceTest.newExperiment(listSize);

        System.out.println("LinkedList");

        // TODO Perform the LinkedList O(N) operation tests


    }

    public static void main(String[] args){
        ListPerformanceExperiment exp = new ListPerformanceExperiment();
    }
}
