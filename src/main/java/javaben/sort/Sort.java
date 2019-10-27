package javaben.sort;

import javaben.Callable;
import javaben.Generator;
import javaben.Result;

import java.util.ArrayList;
import java.util.List;

public abstract class Sort implements Callable {
    public boolean testing = false;

    private List<Integer> list = new ArrayList<>();

    @Override
    public List<Integer> init(int size, long seed, Generator.Type type) {
        switch (type) {
            case UNSORTED:
                list = new Generator(seed).unsortedListGenerator(size);
                break;
            case SORTEDASC:
                list = new Generator(seed).sortedListGenerator(size, true);
                break;
            case SORTEDDESC:
                list = new Generator(seed).sortedListGenerator(size, false);
                break;
        }
        return list;
    }

    @Override
    public Result compute(String method, long seconds) {
        long current = System.nanoTime();
        int iterations = 0;
        while (System.nanoTime() - current < seconds * 1000000000) {
            iterations++;
            List<Integer> copiedList = new ArrayList<>(list);
            sort(copiedList);
        }
        return new Result(System.nanoTime() - current, iterations);
    }

    public abstract List<Integer> sort(List<Integer> source);
}
