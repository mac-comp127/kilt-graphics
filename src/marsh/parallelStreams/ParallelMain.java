package marsh.parallelStreams;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelMain {

    public static long sequentialSum(long n) {
        return LongStream.rangeClosed(1,n)
                .reduce(0L, Long::sum);
    }

    public static long parallelSum(long n) {
        return LongStream.rangeClosed(1,n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static long parallelSum2(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1,n)
                .parallel()
                .forEachOrdered(accumulator::add);
        return accumulator.total;
    }

    public static void sequentialPrint(long n) {
        LongStream.rangeClosed(1,n)
                .forEach(d -> System.out.print(d + " "));
    }

    public static void parallelPrint(long n) {
        LongStream.rangeClosed(1,n)
                .parallel()
                .forEachOrdered(d -> System.out.print(d + " "));
    }

    private static final long N = 10000000L;

    public static void main(String[] args) {
//        System.out.println(sequentialSum(N));
//
//        System.out.println(parallelSum(N));
//        System.out.println(parallelSum2(N));
//        System.out.println(parallelSum2(N));
//        System.out.println(parallelSum2(N));
//        System.out.println(parallelSum2(N));
//        System.out.println(parallelSum2(N));
//        System.out.println(parallelSum2(N));
//        System.out.println(parallelSum2(N));
//        System.out.println(parallelSum2(N));
//        System.out.println(parallelSum2(N));

        long start = System.nanoTime();
        sequentialPrint(20);
        long end = System.nanoTime();
        System.out.println("\nSequential time: " + (end - start));
        System.out.println();
        start = System.nanoTime();
        parallelPrint(20);
        end = System.nanoTime();
        System.out.println("\nParallel time: " + (end - start));
    }
}
