package javaben;

public interface Callable {

    void init(int size, long seed, Generator.Type type);

    Result compute(long seconds);

}
