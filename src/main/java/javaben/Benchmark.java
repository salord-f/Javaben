package javaben;

public class Benchmark {

    public static long bench(Callable method, long n, int size, long seed) {
        method.init(size, seed);
        long startTime = System.nanoTime();
        method.compute(n);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

}
