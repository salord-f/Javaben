package javaben.sort;

import javaben.Callable;
import javaben.Generator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class Sort implements Callable {
    protected List<Integer> unsortedList = new ArrayList<>();
    protected List<Integer> copiedList;

    @Override
    public void init(int size, long seed) {
        unsortedList = new Generator(seed).unsortedListGenerator(size);
    }

    protected void swap(int index1, int index2) {
        Collections.swap(copiedList, index1, index2);
    }

    protected void insert(int former_index, int new_index) {
        if (former_index != new_index) {
            int tmp = copiedList.get(former_index);
            copiedList.remove(former_index);
            copiedList.add(new_index, tmp);
        }
    }
}
