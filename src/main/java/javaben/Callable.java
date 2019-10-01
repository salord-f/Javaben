package javaben;

public interface Callable {

    void init(int size, long seed, Generator.Type type);

    void compute(long n);

}
