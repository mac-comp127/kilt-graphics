package activityStarterCode.performanceTesting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class RandomAccessPerformanceTestSuite implements PerformanceTestSuite {
    public static void main(String[] args) {
        PerformanceTester tester = new PerformanceTester(new RandomAccessPerformanceTestSuite());
        tester.testAcrossSizes(10, 200);
        tester.dumpResults();
    }

    public List<String> getHeadings() {
        return Arrays.asList(
            "ArrayList get(i)",
            "LinkedList get(i)"
        );
    }

    public List<Runnable> makeTasks(int size) {
        return Arrays.asList(
            new RandomAccessTask(size, new ArrayList<>()),
            new RandomAccessTask(size, new LinkedList<>())
        );
    }

    // –––––– Tasks to test ––––––

    private static class RandomAccessTask implements Runnable {
        private List<Object> list;

        RandomAccessTask(int size, List<Object> list) {
            this.list = list;

            String testObject = "splat";
            for(int n = 0; n < size; n++) {
                list.add(testObject);
            }
        }

        @SuppressWarnings("ResultOfMethodCallIgnored")
        @Override
        public void run() {
            int size = list.size();
            for(int n = 0; n < 1000; n++) {
                list.get(n * 4733 % size);
            }
        }
    }
}
