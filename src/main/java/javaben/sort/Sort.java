package javaben.sort;

import javaben.Callable;
import javaben.Generator;

import java.util.ArrayList;
import java.util.List;

public abstract class Sort implements Callable {
    protected List<Integer> unsortedList = new ArrayList<>();
    protected List<Integer> copiedList;

    @Override
    public void init() {
        unsortedList = new Generator(23).unsortedListGenerator( 1000);
    }

    protected void swap(int index1, int index2) {
        if (index1 != index2) {
            int tmp = copiedList.get(index2);
            copiedList.set(index2, copiedList.get(index1));
            copiedList.set(index1, tmp);
        }
    }

    protected void insert(int former_index, int new_index) {
        if (former_index != new_index) {
            int tmp = copiedList.get(former_index);
            copiedList.remove(former_index);
            copiedList.add(new_index, tmp);
        }
    }
}
