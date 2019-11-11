package cantrell.streams;

public class PerformanceProfiler {
    private final Runnable task;

    public PerformanceProfiler(Runnable task) {
        this.task = task;
    }

    void profile(int reps) {
        long start = System.currentTimeMillis();
        for (int n = 0; n < reps; n++) {
            task.run();
        }
        System.out.println("Average time: " + (System.currentTimeMillis() - start) / reps + " ms");
    }

    void profile(int innerReps, int outerReps) {
        for (int n = 0; n < outerReps; n++) {
            profile(innerReps);
        }
    }
}
