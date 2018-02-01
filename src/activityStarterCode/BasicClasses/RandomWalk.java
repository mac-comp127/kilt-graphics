package activityStarterCode.BasicClasses;

import java.util.Random;

public class RandomWalk {
    private Random rng;

    public RandomWalk() {
        rng = new Random();
    }

    public int getValue() {
        return -1; //TODO - this is not correct.
    }

    public int advanceValue() {
        // TODO: update the value randomly, either +1 or -1
        return getValue();
    }
}

