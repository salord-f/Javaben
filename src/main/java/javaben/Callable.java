package javaben;

import java.util.List;

public interface Callable {

    List<Integer> init(int size, long seed, Generator.Type type);

    void setup(String method, List<Integer> source);

    Result compute(String method, long seconds);

}
