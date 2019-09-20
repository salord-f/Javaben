package javaben.sort;

import java.util.ArrayList;

public class InsertionSort extends Sort {
    @Override
    public void compute(long n) {
        for (int i = 0; i < n; i++) {
            copiedList = new ArrayList<>(unsortedList);
            for (int j = 1; j < copiedList.size(); j++) {
                int k = j - 1;
                while (k >= 0 && copiedList.get(k) < copiedList.get(j)) {
                    k--;
                }
                insert(j, k + 1);
            }
        }
    }
}
