package javaben.sort;

import java.util.ArrayList;

public class NativeSort extends Sort {
    @Override
    public void compute(long n) {
        for (int i = 0; i < n; i++) {
            copiedList = new ArrayList<>(unsortedList);

        }
    }
}
