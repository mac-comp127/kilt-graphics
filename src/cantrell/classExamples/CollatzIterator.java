package cantrell.classExamples;

public class CollatzIterator {

    // The original terrible implementation:

    public int reallyMessy_recordSettingSeed(int max) {
        int record = 0, recordSeed = 1, seed, iters, x;
        seed = 1;
        while(seed < max || seed == max) {
            iters = 0;
            x = seed;
            while((x == 1) == false) {
                int newx = x;
                if(x % 2 == 0) {    // x even
                    newx = x / 2;
                }
                if(x % 2 != 0) {  // x odd
                    newx = 3 * x + 1;
                }
                x = newx;
                iters = iters + 1;
                if(x == 1)
                    break;
            }
            if(iters > record) {
                record = iters;
                recordSeed = seed;
            }
            seed++;
        }
        return recordSeed;
    }

    // Our improved version:

    /**
     * Returns the number less than or equal to max that yields the longest
     * Collatz sequence (see https://en.wikipedia.org/wiki/Collatz_conjecture).
     */
    public static int recordSettingSeed(int max) {
        int recordSequenceLength = 0, recordSeed = 1;
        for(int seed = 1; seed <= max; seed++) {
            int iters = collatzSequenceLength(seed);
            if(iters > recordSequenceLength) {
                recordSequenceLength = iters;
                recordSeed = seed;
            }
        }
        return recordSeed;
    }

    public static int collatzSequenceLength(int x) {
        int iters = 0;
        while(x != 1) {
            x = nextCollatz(x);
            iters++;
        }
        return iters;
    }

    private static int nextCollatz(int x) {
        if(x % 2 == 0) {
            return x / 2;     // even
        } else {
            return 3 * x + 1; // odd
        }
    }

}
