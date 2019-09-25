package javaben.sort;

import javaben.Callable;
import javaben.Generator;

import java.util.ArrayList;
import java.util.List;

public abstract class Sort implements Callable {
    protected List<Integer> unsortedList = new ArrayList<>();
    protected List<Integer> copiedList;
    public boolean testing = false;

    @Override
    public void init(int size, long seed) {
        unsortedList = new Generator(seed).unsortedListGenerator(size);
    }

    @Override
    public void compute(long n) {
        for (int i = 0; i < n; i++) {
            copiedList = new ArrayList<>(unsortedList);
            sort(copiedList);
        }
    }

    public abstract List<Integer> sort(List<Integer> source);
}
