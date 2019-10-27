package javaben.sort;

import java.util.Collections;
import java.util.List;

public class HeapSort extends Sort {

    private int heapLimit;

    public HeapSort() {
    }

    HeapSort(int heapLimit) {
        this.heapLimit = heapLimit;
    }

    @Override
    public List<Integer> sort(List<Integer> source) {
        heapify(source);
        for (int i = 0; i < source.size(); i++) {
            Collections.swap(source, 0, heapLimit);
            heapLimit--;
            percolateDown(source, 0);
        }
        return source;
    }

    void heapify(List<Integer> list) {
        heapLimit = list.size() - 1;
        for (int i = (list.size() / 2) - 1; i >= 0; i--) {
            percolateDown(list, i);
        }
    }

    void percolateDown(List<Integer> list, int index) {
        int new_index = getGreaterChild(list, index);
        while (new_index != -1) {
            Collections.swap(list, new_index, index);
            index = new_index;
            new_index = getGreaterChild(list, index);
        }
    }

    int getGreaterChild(List<Integer> list, int index) {
        int child_1 = -1;
        int child_2 = -1;
        int value = list.get(index);
        try {
            child_1 = list.get(2 * index + 1);
            if (2 * index + 1 > heapLimit) {
                child_1 = -1;
            }
            child_2 = list.get(2 * index + 2);
            if (2 * index + 2 > heapLimit) {
                child_2 = -1;
            }
        } catch (IndexOutOfBoundsException ignored) {

        }

        if (child_1 == -1) {
            return -1;
        } else if (value < child_1) {
            if (child_2 == -1) {
                return 2 * index + 1;
            } else {
                return (child_2 < child_1) ? 2 * index + 1 : 2 * index + 2;
            }
        } else if (child_2 == -1) {
            return -1;
        } else if (value < child_2) {
            return 2 * index + 2;
        } else {
            return -1;
        }

    }
}
