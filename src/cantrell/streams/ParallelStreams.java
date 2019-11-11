package cantrell.streams;

import java.util.stream.LongStream;

public class ParallelStreams {
    public static void main(String[] args) {
        new PerformanceProfiler(ParallelStreams::sumToZeroFixed)
            .profile(3, 5);
    }

    private static long sum;

    private static void sumToZero() {
        sum = 0;
        LongStream.range(0, 2000000000)
            .parallel()
            .forEach(x -> sum += (x % 2) * 2 - 1);
        System.out.println(sum);
    }

    private static void sumToZeroFixed() {
        long result = LongStream.range(0, 2000000000)
            .parallel()
            .map(x -> (x % 2) * 2 - 1)
            .sum();
        System.out.println(result);
    }

}








