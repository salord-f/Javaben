package javaben;

import javaben.basic.Addition;
import javaben.basic.Multiplication;
import javaben.sort.BenchmarkSort;

import java.util.ArrayList;
import java.util.List;

public class Warmup {

    public static void warmup(long seconds, long seed) {
        List<Callable> list = new ArrayList<>();
        list.add(new Addition());
        list.add(new Multiplication());

        // Warmup
        for (Callable item : list) {
            Result result = BenchmarkSort.benchOne(item, null, seconds, 1, seed, Generator.Type.UNSORTED);
            System.out.format("%-20s%-40s%s%n", item.getClass().getSimpleName() + " :", "total (iterations) : " + result.iterations, "total (time) : " + result.time / result.iterations);
        }
    }
}
