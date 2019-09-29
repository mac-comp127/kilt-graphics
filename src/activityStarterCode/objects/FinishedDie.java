package activityStarterCode.objects;

import java.util.Random;

/**
 * A single die that can roll values between 1 and 6.
 * @author Shilad
 */
public class FinishedDie {
    private int value = -1;
    private Random random = new Random();

    public FinishedDie() {}

    /**
     * Re-roll the die
     */
    public void roll() {
        value = random.nextInt(6) + 1;
    }

    /**
     * The current die value, or -1 if it has never been rolled.
     */
    public int getValue() {
        return value;
    }

    /**
     * Returns true iff the two dice have equal values.
     */
    public boolean equals(Object o) {
        if (!(o instanceof FinishedDie)) {
            return false;
        }
        FinishedDie d = (FinishedDie)o;
        return this.value == d.value;
    }

    /**
     * Returns the string representation of the dice ("one"... "six")
     */
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
