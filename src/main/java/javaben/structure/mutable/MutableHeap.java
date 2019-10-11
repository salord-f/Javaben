package javaben.structure.mutable;

public class MutableHeap extends MutableBinaryTree {
    public void add(int e) {
        elements.add(e);
        int elementIndex = elements.size() - 1;
        while (!isRoot(elementIndex) && !isCorrectChild(elementIndex)) {
            int parentIndex = parentIndex(elementIndex);
            swap(elementIndex, parentIndex);
            elementIndex = parentIndex;
        }
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("You cannot pop from an empty heap");
        }

        int result = elementAt(0);

        int lastElementIndex = elements.size() - 1;
        swap(0, lastElementIndex);
        elements.remove(lastElementIndex);

        int elementIndex = 0;
        while (!isLeaf(elementIndex) && !isCorrectParent(elementIndex)) {
            int smallerChildIndex = smallerChildIndex(elementIndex);
            swap(elementIndex, smallerChildIndex);
            elementIndex = smallerChildIndex;
        }

        return result;
    }

    private boolean isRoot(int index) {
        return index == 0;
    }

    private boolean isCorrectChild(int index) {
        return isCorrect(parentIndex(index), index);
    }

    private boolean isCorrect(int parentIndex, int childIndex) {
        if (!isValidIndex(parentIndex) || !isValidIndex(childIndex)) {
            return true;
        }

        return elementAt(parentIndex) - elementAt(childIndex) < 0;
    }

    private boolean isValidIndex(int index) {
        return index < elements.size();
    }

    private void swap(int index1, int index2) {
        int element1 = elementAt(index1);
        int element2 = elementAt(index2);
        elements.set(index1, element2);
        elements.set(index2, element1);
    }

    private boolean isLeaf(int index) {
        return !isValidIndex(leftChildIndex(index));
    }

    private boolean isCorrectParent(int index) {
        return isCorrect(index, leftChildIndex(index)) && isCorrect(index, rightChildIndex(index));
    }

    private int smallerChildIndex(int index) {
        int leftChildIndex = leftChildIndex(index);
        int rightChildIndex = rightChildIndex(index);

        if (!isValidIndex(rightChildIndex)) {
            return leftChildIndex;
        }

        if (elementAt(leftChildIndex) - elementAt(rightChildIndex) < 0) {
            return leftChildIndex;
        }

        return rightChildIndex;
    }
}
