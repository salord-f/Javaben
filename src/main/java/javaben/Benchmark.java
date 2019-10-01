package javaben;

public class Benchmark {

    public static long bench(Callable method, long n, int size, long seed, Generator.Type type) {
        method.init(size, seed, type);
        long startTime = System.nanoTime();
        method.compute(n);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

}
