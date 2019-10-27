package javaben.sort;

import java.util.Collections;
import java.util.List;

public class QuickSort extends Sort {

    @Override
    public List<Integer> sort(List<Integer> source) {
        quickSort(source, 0, source.size() - 1);
        return source;
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
            if (list.get(j) <= pivot) {
                i++;
                Collections.swap(list, i, j);
            }
        }
        Collections.swap(list, i + 1, end);
        return i + 1;
    }
}
