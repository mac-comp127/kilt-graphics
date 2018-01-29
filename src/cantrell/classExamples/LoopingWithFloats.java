package cantrell.classExamples;

public class LoopingWithFloats {
    public static void main(String[] args) {
        countUsingFloat();     // broken
        countUsingDouble();    // broken
        countUsingIntCents();  // works!

        // Moral: don't ever use float or double for money, or anything where you need
        //        an exact answer, in real-life code.
        //
        // (You _are_ allowed to use floats for HW1, because it is just for practice.
        // But be careful about rounding error.)
    }

    private static void countUsingFloat() {
        int count = 0;

        for(float n = 0; n < 1000; n += 0.01) {  // floating dollars → rounding error → wrong answer!
            count++;
            // System.out.println(n);  // Uncomment to see rounding errors accumulating
        }

        System.out.println("Using float, there are " + count + " cents in $1000.");
    }

    private static void countUsingDouble() {  // double means (about) twice the precision of float
        int count = 0;

        for(double n = 0; n < 1000; n += 0.01) {  // more precision → smaller error, but still wrong answer
            count++;
            // System.out.println(n);  // Uncomment to see rounding errors accumulating
        }

        System.out.println("Using double, there are " + count + " cents in $1000.");
    }

    private static void countUsingIntCents() {
        int count = 0;

        for(int n = 0; n < 100000; n += 1) {  // integer cents → no problem!
            count++;
        }

        System.out.println("Using int, there are " + count + " cents in $1000.");
    }
}
