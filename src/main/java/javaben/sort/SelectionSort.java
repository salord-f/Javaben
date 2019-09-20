package javaben.sort;

import java.util.ArrayList;

public class SelectionSort extends Sort {
    @Override
    public void compute(long n) {
        for (int i = 0; i < n; i++) {
            copiedList = new ArrayList<>(unsortedList);
            for (int j = 0; j < copiedList.size(); j++) {
                int max = -1;
                int max_index = -1;
                for (int k = j; k < copiedList.size(); k++) {
                    if (copiedList.get(k) > max) {
                        max = copiedList.get(k);
                        max_index = k;
                    }
                }
                swap(j, max_index);
            }
        }
    }
}
