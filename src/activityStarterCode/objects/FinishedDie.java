package activityStarterCode.objects;

import java.util.Random;

/**
 * A single die that can roll values between 1 and 6.
 * @author Shilad
 */
public class FinishedDie {
    private static final int DEFAULT_MAX_VALUE = 6;

    private final int maxValue;
    private int value = -1;
    private Random random = new Random();

    /**
     * Creates a new die that can generate values between 1 and 6.
     */
    public FinishedDie() {
        this(DEFAULT_MAX_VALUE);
    }

    /**
     * Creates a new die that can generate values between 1 and the specified max value.
     */
    public FinishedDie(int maxValue) {
        this.maxValue = maxValue;
    }

    /**
     * Roll the die and remember the new value
     */
    public void roll() {
        value = random.nextInt(maxValue) + 1;
    }

    /**
     * Returns the die's current value, or -1 if it has never been rolled.
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns the string representation of the dice ("one"... "six")
     */
    @Override
    public String toString() {
        if (value == 1) {
            return "one";
        } else if (value == 2) {
            return "two";
        } else if (value == 3) {
            return "three";
        } else if (value == 4) {
            return "four";
        } else if (value == 5) {
            return "five";
        } else if (value == 6) {
            return "six";
        } else {
            return "unknown";
        }
    }
}
