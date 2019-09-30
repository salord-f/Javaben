package javaben.sort;

import javaben.Callable;
import javaben.Generator;

import java.util.ArrayList;
import java.util.List;

public abstract class Sort implements Callable {
    private List<Integer> unsortedList = new ArrayList<>();
    public boolean testing = false;

    @Override
    public void init(int size, long seed) {
        unsortedList = new Generator(seed).unsortedListGenerator(size);
    }

    @Override
    public void compute(long n) {
        for (long i = 0; i < n; i++) {
            List<Integer> copiedList = new ArrayList<>(unsortedList);
            sort(copiedList);
        }
    }

    public abstract List<Integer> sort(List<Integer> source);
}
