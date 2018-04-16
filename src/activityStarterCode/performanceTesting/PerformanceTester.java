package activityStarterCode.performanceTesting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Performs comparative performance testing on a collection of tasks.
 */
public class PerformanceTester {
    private static final double MIN_TASK_TEST_TIME = 0.1;  // seconds
    private static final int MIN_REPS = 10;
    private static final int MAX_REPS = 1000000;
    private static final int NUM_PASSES_FOR_MEDIAN = 5;

    private final PerformanceTestSuite suite;
    private List<PerformanceResult> results = new ArrayList<>();

    /**
     * Creates a tester with no accumulated results.
     *
     * @param suite A collection of related tasks to time at various sizes.
     */
    public PerformanceTester(PerformanceTestSuite suite) {
        this.suite = suite;
    }

    /**
     * Times the tasks in the suite at the size = step, step*2, step*3… up to maxSize.
     */
    public void testAcrossSizes(int step, int maxSize) {
        testAcrossSizes(step, maxSize, step);
    }

    /**
     * Times the tasks in the suite at the size = minSize, minSize + step, minSize + step*2… up to maxSize.
     */
    public void testAcrossSizes(int minSize, int maxSize, int step) {
        for(int size = minSize; size <= maxSize; size += step) {
            test(size);
        }
    }

    /**
     * Computes the median time taken for each task in the suite’s family of tasks at the given size,
     * adding the timings to this tester’s accumulated results.
     */
    private void test(int size) {
        List<Runnable> tasks = suite.makeTasks(size);
        List<String> headings = suite.getHeadings();
        PerformanceResult result = new PerformanceResult(size);

        if(tasks.size() != headings.size()) {
            throw new IllegalStateException(
                suite + " gave " + headings.size() + " headings, but " + tasks.size() + " tasks");
        }

        for(int i = 0; i < tasks.size(); i++) {
            Runnable task = tasks.get(i);
            System.out.println("size=" + size + " " + headings.get(i));
            result.getTimings().add(
                computeMedianTaskTime(task));
            System.out.println();
        }

        results.add(result);
    }

    /**
     * Repeats timeTask() with the given task NUM_PASSES_FOR_MEDIAN times, and computes the median time.
     */
    private double computeMedianTaskTime(Runnable task) {
        double times[] = new double[NUM_PASSES_FOR_MEDIAN];
        for(int n = 0; n < NUM_PASSES_FOR_MEDIAN; n++) {
            times[n] = timeTask(task);
        }
        Arrays.sort(times);
        return times[times.length / 2];
    }

    /**
     * Runs the given task repeatedly and times it. This method will initially call task.run() MIN_REPS times,
     * then keep running it in larger and larger groups of repetitions until either MIN_TASK_TEST_TIME has elapsed,
     * or we have run it MAX_REPS. This helps ensure that we get a good average to help eliminate measurement error
     * for very fast tasks, while not making the performance test take inordinate amounts of time for slow ones.
     *
     * @return The average time per call to task.run(), in microseconds.
     */
    private double timeTask(Runnable task) {
        long start = System.nanoTime(), lastClockMeasurement = start;

        long reps = 0;
        long timingChunk = MIN_REPS;
        long minTaskTestTimeNanos = Math.round(MIN_TASK_TEST_TIME * 1_000_000_000);

        while(lastClockMeasurement - start <= minTaskTestTimeNanos && reps < MAX_REPS) {
            for(long n = 0; n < timingChunk; n++ ) {
                task.run();
            }
            lastClockMeasurement = System.nanoTime();
            reps += timingChunk;
            timingChunk = timingChunk * 3 / 2;  // Think: why shouldn’t we do this?  timingChunk *= 3 / 2
        }
        double time = (double) (lastClockMeasurement - start) / reps / 1000;
        System.out.format("    %9.3f µs  (%d reps)\n", time, reps);
        return time;
    }

    /**
     * Prints accumulated timings to stdout in a tab-separated format suitable for pasting into a spreadsheet.
     */
    public void dumpResults() {
        System.out.println();
        System.out.println(suite.getClass().getName() + " Results");
        System.out.println();
        System.out.print("size\t");
        System.out.println(String.join("\t", suite.getHeadings()));
        for(PerformanceResult result : results) {
            System.out.print(result.getSize());
            for(double timing : result.getTimings()) {
                System.out.print("\t");
                System.out.print(timing);
            }
            System.out.println();
        }
    }
}

class PerformanceResult {
    private int size;
    private List<Double> timings;

    PerformanceResult(int size) {
        this.size = size;
        timings = new ArrayList<>();
    }

    int getSize() {
        return size;
    }

    List<Double> getTimings() {
        return timings;
    }
}
