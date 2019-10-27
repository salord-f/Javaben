package javaben.structure;

import javaben.Callable;
import javaben.Generator;
import javaben.Result;

import java.util.ArrayList;
import java.util.List;

public abstract class Structure implements Callable {
    private List<Integer> list = new ArrayList<>();

    @Override
    public List<Integer> init(int size, long seed, Generator.Type type) {
        switch (type) {
            case UNSORTED:
                list = new Generator(seed).unsortedListGenerator(size);
                break;
            case UNSORTEDSET:
                list = new Generator(seed).unsortedSetGenerator(size);
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

    public Result compute(String method, long seconds) {
        return null;
    }
}
