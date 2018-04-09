package activityStarterCode.performanceTesting;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QueuePerformanceTestSuite implements PerformanceTestSuite {
    public static void main(String[] args) {
        PerformanceTester tester = new PerformanceTester(new QueuePerformanceTestSuite());
        tester.testAcrossSizes(50, 1000);
        tester.dumpResults();
    }

    public List<String> getHeadings() {
        return Arrays.asList(
            "Cycle ArrayDeque",
            "Cycle LinkedList using Deque API",
            "Cycle ArrayList",
            "Cycle LinkedList using List API"
        );
    }

    public List<Runnable> makeTasks(int size) {
        return Arrays.asList(
            new CycleQueueTask(size, new ArrayDeque<>(size)),
            new CycleQueueTask(size, new LinkedList<>()),
            new CycleListTask(size, new ArrayList<>(size)),
            new CycleListTask(size, new LinkedList<>())
        );
    }

    // –––––– Tasks to test ––––––

    private static class CycleQueueTask implements Runnable {
        private final Queue<Object> queue;

        CycleQueueTask(int queueSize, Queue<Object> queue) {
            this.queue = queue;

            Object testObject = "zoink";
            for(int n = 0; n < queueSize; n++) {
                queue.add(testObject);
            }
        }

        @Override
        public void run() {
            for(int n = 0; n < 100000; n++) {
                queue.add(queue.remove());
            }
        }
    }

    private static class CycleListTask implements Runnable {
        private final List<Object> list;

        CycleListTask(int queueSize, List<Object> list) {
            this.list = list;

            Object testObject = "zlonk";
            for(int n = 0; n < queueSize; n++) {
                list.add(testObject);
            }
        }

        @Override
        public void run() {
            for(int n = 0; n < 100000; n++) {
                list.add(list.remove(0));
            }
        }
    }
}
