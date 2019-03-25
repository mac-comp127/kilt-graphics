package activityStarterCode.listPerformance;

import java.util.List;

/**
 * This class is designed to be subclassed, using either an ArrayList or LinkedList
 * to hold String objects.
 *
 * The constructor and its methods set up and perform experiments using
 * List operations so that we can see how well these operations perform.
 * We will keep varying numbers of short String objects in the List
 * and use methods to retrieve or remove elements from it.
 *
 * Created by shoop on 3/16/16.
 */
public abstract class ListPerformance {
    protected List<String> list;  // set to an ArrayList or LinkedList in it subclass
    protected int testSize;       // number of String objects in the list for a particular test

    protected long startTime;   // the start of a particular experiment
    protected long endTime;     // the end of a particular experiment

    /**
     * Set up a new performance test with testSize elements to be added to the List.
     *
     * @param testSize the number of elements that will eventually be placed in the List
     */
    public ListPerformance(int testSize) {
        this.testSize = testSize;
    }

    /**
     * Set up an experiment with no elements in the list. Must use
     * in conjunction with newExperiment method to populate
     * the List with Strings.
     */
    public ListPerformance() {
        this.testSize = 0;
    }

    /**
     * add testSize String objects to the List
     */
    protected void fillList() {
        //TODO fill in the List with Strings
        //     each string can be of this form: "I am " + i,
        //     where i is an index to the next element to be added to the
        //     end of the list


    }

    /**
     * Start a new experiment by clearing the List and filling it with
     * testSize String objects.
     *
     * @param testSize  number of String objects to place in the List
     */
    public void newExperiment(int testSize) {
        list.clear();
        this.testSize = testSize;
        fillList();
    }

    /**
     * Perform an experiment where we retrieve every element using its index.
     * So that we will do a small bit of computation, add the number of characters
     * in each element retrieved to an accumulator that is keep track of the total
     * overall number of characters.
     *
     * @return time in seconds that it took to run this experiment.
     */
    public double tryGetEveryElement() {
        int numChars = 0;   // will contain total number of characters in all Strings

        start(); //for timing how long these operations take

        // TODO: complete code here to get every element in the list and
        //       update the numChars counter of the total number of characters in
        //       all String objects in the list combined.
        //       Since the list is populated and you will not be changing it,
        //       in normal situational use we would use the preferred 'for each'
        //       method with a colon.
        //       However, in this case we are timing the method that gets an element at
        //       a particular index and doing it for all of the elements so that we have
        //       a time that we can measure. So for experimental purposes we will
        //       use a traditional for loop, being careful not to call the size()
        //       method on the list repeatedly, but using our stored testSize instead.


        double endTime = end();
        return endTime;
    }

    /**
     * Get every element by iterating using a for-each type of for loop
     *
     * @return time in seconds to get every element
     */
    public double tryGetIterate() {
        int numChars = 0;  //accumulates length of all strings together
        start();

        // TODO: use for-each loop syntax on the list to get each element.
        //       update numChars by adding the length of the next String
        //       so that there is something being computed inside the loop.


        double endTime = end();
        return endTime;
    }

    /**
     * Remove all elements from list by repeatedly removing the zeroth one.
     * Note that the List will be empty after this test and will need to
     * be re-populated for an subsequent tests.
     *
     * @return time in seconds to perform all removals
     */
    public double tryRemoveFront() {
        int numChars = 0;
        start();

        // TODO: remove the zeroth element of the list repeatedly until the list is empty
        //       using the d0-n-times pattern


        double endTime = end();
        return endTime;
    }

    /**
     * Get the middle element of the List a total of numTimes
     *
     * @param numTimes  number of time to get the middle element
     *
     * @return total time in seconds to perform numTimes get operations
     */
    public double tryGetMiddleOfList(int numTimes) {
        int middleElement = testSize/2;

        //accumulator for repeatedly adding length of middle element
        int numChars = 0;

        start();

        // TODO: perform numTimes get of the middle element operations
        //       use do-n-times loop pattern


        double endTime = end();
        return endTime;
    }

    /**
     * reset the start time for an experiment
     */
    protected void start() {
        startTime = System.currentTimeMillis();     //start timing
    }

    /**
     * Determine the amount of time in seconds for an experiment
     *
     * @return time in seconds since start() method was called
     */
    protected double end() {
        endTime = System.currentTimeMillis();
        double elapsedMillis = endTime - startTime;
        double elapsedSecs = elapsedMillis/1000.0;
        return elapsedSecs;
    }

    //////////////////// Getters and Setters /////////////////////
    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public int getTestSize() {
        return testSize;
    }

    public void setTestSize(int testSize) {
        this.testSize = testSize;
    }
}
