package javaben.structure.mutable;

import javaben.structure.Node;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class MutableBinaryTree {

    protected Node root;
    protected int size = 0;

    public void add(int value) {
        if (size == 0) {
            root = new Node(value, 2);
            size++;
        } else {
            String path = Integer.toBinaryString(size + 1).substring(1);
            Node curr = root;
            Node node = new Node(value, 2);
            for (int i = 0; i < path.length() - 1; i++) {
                curr = curr.getNodes()[path.charAt(i) - 48];
            }
            curr.getNodes()[path.charAt(path.length() - 1) - 48] = node;
            size++;
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void removeAt(int index) {
        if (index == 0) {
            root = null;
            size--;
        } else {
            String path = Integer.toBinaryString(index + 1).substring(1);
            Node curr = root;
            for (int i = 0; i < path.length() - 1; i++) {
                curr = curr.getNodes()[path.charAt(i) - 48];
            }
            curr.getNodes()[path.charAt(path.length() - 1) - 48] = null;
            size--;
        }
    }

    public int elementAt(int index) {
        String path = Integer.toBinaryString(index + 1).substring(1);
        Node curr = root;
        for (int i = 0; i < path.length(); i++) {
            curr = curr.getNodes()[path.charAt(i) - 48];
        }
        return curr.getValue();
    }

    @Override
    public String toString() {
        if (root == null) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(root.getValue()).append("\n");

        List<List<Integer>> all_values = new ArrayList<>();
        explore(root, all_values, 0);

        for (List<Integer> level : all_values) {
            for (int value : level) {
                stringBuilder.append(value).append(" ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    private void explore(Node node, List<List<Integer>> nodes, int level) {
        try {
            for (Node child : node.getNodes()) {
                if (child != null) {
                    nodes.get(level).add(child.getValue());
                    explore(child, nodes, level + 1);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            nodes.add(new ArrayList<>());
            for (Node child : node.getNodes()) {
                if (child != null) {
                    nodes.get(level).add(child.getValue());
                    explore(child, nodes, level + 1);
                }
            }
        }
    }

    protected void swapWithParent(Node child, Node parent) {
        int tmp = child.getValue();
        child.setValue(parent.getValue());
        parent.setValue(tmp);
    }
}
