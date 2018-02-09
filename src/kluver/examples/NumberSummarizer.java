package kluver.examples;

/**
 * A class to help when performing data analysis of various sources of
 * data. The idea of this object is that you could create one object for each
 * block of data in each dataset to get its minimum and maximum.
 *
 * In future versions I intend to add support for getting the average of the dataset.
 */
public class NumberSummarizer {
    // a static variable is used here to get the largest number seen
    // by _any_ NumberSummarizer (within a current execution of the program
    private static int largestNumberEver = Integer.MIN_VALUE;

    // instance variables for the largest and smallest number seen.
    private int smallestNum;
    private int largestNum;

    /**
     * Constructor -- initalize the variables to a reasonable max value.
     */
    public NumberSummarizer() {
        smallestNum = Integer.MAX_VALUE;
        largestNum = Integer.MIN_VALUE;
    }

    /**
     * record the given integer in the analysis.
     * This method should be called once for every number in the dataset.
     */
    public void addNumber(int number) {
        if(number > largestNum) {
            largestNum = number;
        }
        if (number < smallestNum) {
            smallestNum = number;
        }
        if(number > largestNumberEver) {
            largestNumberEver = number;
        }
    }

    /**
     * Overloaded method to make it easy to use this with doubles.
     *
     * Note, this is not _super_ useful, since we lose some precision
     * But its nice to have.
     *
     * Also note the chaining used to avoid code re-use.
     */
    public void addNumber(double number) {
        addNumber((int)Math.round(number));
    }

    /**
     * Reset the counter. This might be useful if you find you have to throw
     * away some data and want to reset this as well.
     *
     * Note the use of "this." style setters. This is very common in java
     * programing for constructors and setter methods.
     * @param smallestNum
     * @param largestNum
     */
    public void reset(int smallestNum, int largestNum) {
        this.smallestNum = smallestNum;
        this.largestNum = largestNum;
    }

    public static int getLargestNumberEver() {
        return largestNumberEver;
    }

    public int getSmallestNum() {
        return smallestNum;
    }

    public int getLargestNum() {
        return largestNum;
    }
}
