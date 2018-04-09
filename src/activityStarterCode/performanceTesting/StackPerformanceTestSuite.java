package activityStarterCode.performanceTesting;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class StackPerformanceTestSuite implements PerformanceTestSuite {

    public static void main(String[] args) {
        PerformanceTester tester = new PerformanceTester(new StackPerformanceTestSuite());
        tester.testAcrossSizes(200, 6000);
        tester.dumpResults();
    }

    public List<String> getHeadings() {
        return Arrays.asList(
            "Grow & shrink ArrayDeque",
            "Grow & shrink LinkedList",
            "Grow & shrink ArrayList from head",
            "Grow & shrink ArrayList from tail"
        );
    }

    public List<Runnable> makeTasks(int size) {
        return Arrays.asList(
            new GrowAndShrinkStackTask(size, new ArrayDeque<>()),
            new GrowAndShrinkStackTask(size, new LinkedList<>()),
            new GrowAndShrinkListFromHeadTask(size, new ArrayList<>(size)),
            new GrowAndShrinkListFromTailTask(size, new ArrayList<>(size))
        );
    }

    // –––––– Tasks to test ––––––

    class GrowAndShrinkStackTask implements Runnable {
        private final int maxSize;
        private final Deque<Object> stack;

        GrowAndShrinkStackTask(int maxSize, Deque<Object> stack) {
            this.maxSize = maxSize;
            this.stack = stack;
        }

        @Override
        public void run() {
            Object testObject = "bonk";

            for(int n = 0; n < maxSize; n++ ) {
                stack.addFirst(testObject);
            }
            for(int n = 0; n < maxSize; n++ ) {
                stack.removeFirst();
            }
        }
    }

    private static class GrowAndShrinkListFromHeadTask implements Runnable {
        private final int maxSize;
        private final List<Object> stack;

        GrowAndShrinkListFromHeadTask(int maxSize, List<Object> stack) {
            this.maxSize = maxSize;
            this.stack = stack;
        }

        @Override
        public void run() {
            Object testObject = "bonk";

            for(int n = 0; n < maxSize; n++ ) {
                stack.add(0, testObject);
            }
            for(int n = 0; n < maxSize; n++ ) {
                stack.remove(0);
            }
        }
    }

    private static class GrowAndShrinkListFromTailTask implements Runnable {
        private final int maxSize;
        private final List<Object> stack;

        GrowAndShrinkListFromTailTask(int maxSize, List<Object> stack) {
            this.maxSize = maxSize;
            this.stack = stack;
        }

        @Override
        public void run() {
            Object testObject = "bonk";

            for(int n = 0; n < maxSize; n++ ) {
                stack.add(testObject);
            }
            for(int n = 0; n < maxSize; n++ ) {
                stack.remove(stack.size() - 1);
            }
        }
    }
}