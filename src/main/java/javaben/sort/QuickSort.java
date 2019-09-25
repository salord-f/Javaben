package javaben.sort;

import java.util.ArrayList;
import java.util.List;

public class QuickSort extends Sort {
    @Override
    public void compute(long n) {
        for (int i = 0; i < n; i++) {
            copiedList = new ArrayList<>(unsortedList);
            quickSort(copiedList, 0, copiedList.size() - 1);
        }
    }

    // https://www.baeldung.com/java-quicksort
    public void quickSort(List<Integer> list, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(list, begin, end);

            quickSort(list, begin, partitionIndex - 1);
            quickSort(list, partitionIndex + 1, end);
        }
    }

    private int partition(List<Integer> list, int begin, int end) {
        int pivot = list.get(end);
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (list.get(j) > pivot) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, end);
        return i + 1;
    }
}
