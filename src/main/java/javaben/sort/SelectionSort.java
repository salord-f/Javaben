package javaben.sort;

import javaben.Callable;
import javaben.Generator;

import java.util.ArrayList;
import java.util.List;

public class SelectionSort extends Sort {
    @Override
    public void compute(long n) {
        for (int i = 0; i < n; i++) {
            List<Integer> copiedList = new ArrayList<>(unsortedList);
            List<Integer> sortedList = new ArrayList<>();
            while (copiedList.size() > 0) {
                int max = -1;
                int max_index = -1;
                for (int j = 0; j < copiedList.size(); j++) {
                    if (copiedList.get(j) > max) {
                        max = copiedList.get(j);
                        max_index = j;
                    }
                }
                sortedList.add(max);
                copiedList.remove(max_index);
            }
        }
    }
}
