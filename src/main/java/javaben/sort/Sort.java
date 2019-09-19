package javaben.sort;

import javaben.Callable;
import javaben.Generator;

import java.util.ArrayList;
import java.util.List;

public abstract class Sort implements Callable {
    protected List<Integer> unsortedList = new ArrayList<>();

    @Override
    public void init() {
        unsortedList = new Generator().unsortedListGenerator(1000, 1000);
    }
}
