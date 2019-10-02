package javaben.structure.mutable;

import java.util.ArrayList;
import java.util.List;

class BinaryTree {

    List<Integer> elements = new ArrayList<>();

    void add(int e) {
        elements.add(e);
    }

    boolean isEmpty() {
        return elements.isEmpty();
    }

    int elementAt(int index) {
        return elements.get(index);
    }

    int parentIndex(int index) {
        return (index - 1) / 2;
    }

    int leftChildIndex(int index) {
        return 2 * index + 1;
    }

    int rightChildIndex(int index) {
        return 2 * index + 2;
    }

}
