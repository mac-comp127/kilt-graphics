package activityStarterCode.objects;

/**
 * Main driver program for dice example.
 * @author Shilad
 */
public class FinishedDiceRoller {
    public static void main(String [] args) {
        FinishedDie d1 = new FinishedDie();
        // Roll a die 10 times, print out the value
        for (int i = 0; i < 10; i++) {
            d1.roll();
            System.out.println("Roll " + (i + 1) + " generated a " + d1);
        }

        FinishedDie d2 = new FinishedDie();
        int matches = 0;
        for (int i = 0; i < 1000000; i++) {
            d1.roll();
            d2.roll();
            if (d1.getValue() == d2.getValue()) {
                matches++;
            }
        }

        System.out.println("In 1,000,000 rolls, two dice agreed " + matches + " times");
    }
}
