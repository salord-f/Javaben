package javaben.sort;

import java.util.Collections;
import java.util.List;

public class HeapSort extends Sort {

    private int heap_limit;

    public HeapSort() {
    }

    HeapSort(int heap_limit) {
        this.heap_limit = heap_limit;
    }

    @Override
    public List<Integer> sort(List<Integer> source) {
        heapify(source);
        for (int i = 0; i < source.size(); i++) {
            Collections.swap(source, 0, heap_limit);
            heap_limit--;
            percolateDown(source, 0);
        }
        return source;
    }

    void heapify(List<Integer> list) {
        heap_limit = list.size() - 1;
        for (int i = (list.size() / 2) - 1; i >= 0; i--) {
            percolateDown(list, i);
        }
    }

    void percolateDown(List<Integer> list, int index) {
        int new_index = getLesserChild(list, index);
        while (new_index != -1) {
            Collections.swap(list, new_index, index);
            index = new_index;
            new_index = getLesserChild(list, index);
        }
    }

    int getLesserChild(List<Integer> list, int index) {
        int child_1 = -1;
        int child_2 = -1;
        int value = list.get(index);
        try {
            child_1 = list.get(2 * index + 1);
            if (2 * index + 1 > heap_limit) {
                child_1 = -1;
            }
            child_2 = list.get(2 * index + 2);
            if (2 * index + 2 > heap_limit) {
                child_2 = -1;
            }
        } catch (IndexOutOfBoundsException ignored) {

        }

        if (child_1 == -1) {
            return -1;
        } else if (value > child_1) {
            if (child_2 == -1) {
                return 2 * index + 1;
            } else {
                return (child_2 > child_1) ? 2 * index + 1 : 2 * index + 2;
            }
        } else if (child_2 == -1) {
            return -1;
        } else if (value > child_2) {
            return 2 * index + 2;
        } else {
            return -1;
        }

    }
}
