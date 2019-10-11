package javaben.structure.mutable;

import javaben.structure.EmptyHeapException;
import javaben.structure.Node;

import java.util.Stack;

public class MutableHeap extends MutableBinaryTree {
    public void add(int value) {
        if (size == 0) {
            root = new Node(value, 2);
            size++;
        } else {
            String path = Integer.toBinaryString(size + 1).substring(1);
            Stack<Node> parents = new Stack<>();
            Node curr = root;
            Node node = new Node(value, 2);
            for (int i = 0; i < path.length() - 1; i++) {
                parents.add(curr);
                curr = curr.getNodes()[path.charAt(i) - 48];
            }
            curr.getNodes()[path.charAt(path.length() - 1) - 48] = node;
            parents.add(curr);
            size++;

            while (!parents.isEmpty()) {
                Node parent = parents.pop();
                if (node.getValue() < parent.getValue()) {
                    swapWithParent(node, parent);
                } else {
                    break;
                }
            }
        }
    }

    public int peek() {
        return root.getValue();
    }

    public int pop() {
        if (isEmpty()) {
            throw new EmptyHeapException();
        }

        int result = root.getValue();
        int lastValue = elementAt(size - 1);
        root.setValue(lastValue);
        Node curr = root;
        removeAt(size - 1);

        while (curr.getNodes()[0] != null) {
            Node minNode = curr.getNodes()[0];
            int min = minNode.getValue();
            try {
                if (curr.getNodes()[1].getValue() < min) {
                    minNode = curr.getNodes()[1];
                    min = minNode.getValue();
                }
            } catch (NullPointerException e) {
                // empty
            }
            if (min < curr.getValue()) {
                swapWithParent(minNode, curr);
                curr = minNode;
            } else {
                break;
            }
        }

        return result;
    }
}
