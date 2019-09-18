package javaben;

public class Benchmark {

    public static long bench(Callable method, long n) {
        long startTime = System.nanoTime();
        method.compute(n);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

}
