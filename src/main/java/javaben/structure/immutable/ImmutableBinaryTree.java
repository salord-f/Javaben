package javaben.structure.immutable;

import javaben.structure.EmptyBinaryTreeException;
import javaben.structure.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ImmutableBinaryTree {

    protected Node root;
    protected int size;

    public ImmutableBinaryTree() {
        this.root = null;
        this.size = 0;
    }

    public ImmutableBinaryTree(Node root, int size) {
        this.root = root;
        this.size = size;
    }

    public ImmutableBinaryTree add(Integer value) {
        if (size == 0) {
            return new ImmutableBinaryTree(new Node(value, 2), 1);
        } else {
            String path = Integer.toBinaryString(size + 1).substring(1);
            Stack<Node> parents = new Stack<>();
            Node curr = root;
            Node node = new Node(value, 2);
            for (int i = 0; i < path.length() - 1; i++) {
                parents.add(curr);
                curr = curr.getNodes()[path.charAt(i) - 48];
            }
            int i = 1;
            parents.add(curr);
            Node copy = new Node(parents.pop());
            copy.getNodes()[path.charAt(path.length() - i) - 48] = node;

            while (!parents.isEmpty()) {
                i++;
                Node prevCopy = copy;
                copy = new Node(parents.pop());
                copy.getNodes()[path.charAt(path.length() - i) - 48] = prevCopy;
            }

            return new ImmutableBinaryTree(copy, size + 1);
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public ImmutableBinaryTree removeLast() {
        if (size == 0) {
            throw new EmptyBinaryTreeException();
        } else if (size == 1) {
            return new ImmutableBinaryTree(null, 0);
        } else {
            String path = Integer.toBinaryString(size).substring(1);
            Stack<Node> parents = new Stack<>();
            Node curr = root;
            for (int i = 0; i < path.length() - 1; i++) {
                parents.add(curr);
                curr = curr.getNodes()[path.charAt(i) - 48];
            }
            int i = 1;
            parents.add(curr);
            Node copy = new Node(parents.pop());
            copy.getNodes()[path.charAt(path.length() - i) - 48] = null;

            while (!parents.isEmpty()) {
                i++;
                Node prevCopy = copy;
                copy = new Node(parents.pop());
                copy.getNodes()[path.charAt(path.length() - i) - 48] = prevCopy;
            }

            return new ImmutableBinaryTree(copy, size - 1);
        }
    }

    public Integer elementAt(int index) {
        String path = Integer.toBinaryString(index + 1).substring(1);
        Node curr = root;
        for (int i = 0; i < path.length(); i++) {
            curr = curr.getNodes()[path.charAt(i) - 48];
        }
        if (curr == null) throw new IndexOutOfBoundsException();
        else return curr.getValue();
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

    public int sizeCheck() {
        if (root == null) return 0;
        List<List<Integer>> nodes = new ArrayList<>();
        explore(root, nodes, 0);
        return (int) nodes.stream().mapToLong(List::size).sum() + 1;
    }

    protected void swapWithParent(Node child, Node parent) {
        int tmp = child.getValue();
        child.setValue(parent.getValue());
        parent.setValue(tmp);
    }
}
