package javaben;

public interface Callable {

    void init(int size, long seed);

    void compute(long n);

}
