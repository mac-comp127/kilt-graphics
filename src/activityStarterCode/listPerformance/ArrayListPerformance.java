package activityStarterCode.listPerformance;

/**
 * This class is designed to set up and perform experiments using
 * ArrayList operations so that we can see how well these operations perform.
 * We will keep varying numbers of short String objects in the ArrayList
 * and use methods to retrieve or remove elements from
 *
 * Created by shoop on 3/16/16.
 */
public class ArrayListPerformance extends ListPerformance {
    /**
     * Set up a new performance test with testSize elements in an ArrayList.
     *
     * @param testSize the number of elements that will initially be placed in the ArrayList
     */
    public ArrayListPerformance(int testSize) {
        super(testSize);

        //TODO create the ArrayList of Strings here by setting the variable named list


        fillList();
    }

    /**
     * Set up an ArrayList with no elements in it. Must use
     * in conjunction with newExperiment method to populate
     * the ArrayList with the number of desired Strings.
     */
    public ArrayListPerformance() {
        super();
        //TODO create the ArrayList of Strings here by setting the variable named list

    }




}
