package javaben;

public class Benchmark {

    public static Result bench(Callable method, long seconds, int size, long seed, Generator.Type type) {
        method.init(size, seed, type);
        return method.compute(seconds);
    }

}
