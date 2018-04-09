package activityStarterCode.performanceTesting;

import java.util.List;

public interface PerformanceTestSuite {
    /**
     * Returns descriptions of the suite of tasks in this suite.
     */
    List<String> getHeadings();

    /**
     * Returns the suite of tasks to time for the given size.
     */
    List<Runnable> makeTasks(int size);
}
