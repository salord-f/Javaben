package javaben.structure.immutable;

import javaben.structure.GeneralNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class ImmutableList<T> implements Iterable<T> {

    protected GeneralNode<T> root;
    protected int size;

    public ImmutableList() {
        this.root = null;
        this.size = 0;
    }

    public ImmutableList(GeneralNode<T> root, int size) {
        this.root = root;
        this.size = size;
    }

    public ImmutableList<T> add(T value) {
        if (size == 0) {
            return new ImmutableList<T>(new GeneralNode<T>(value, 2), 1);
        } else {
            String path = Integer.toBinaryString(size + 1).substring(1);
            Stack<GeneralNode<T>> parents = new Stack<>();
            GeneralNode<T> curr = root;
            GeneralNode<T> node = new GeneralNode<T>(value, 2);
            for (int i = 0; i < path.length() - 1; i++) {
                parents.add(curr);
                curr = curr.getNodes().get(path.charAt(i) - 48);
            }
            int i = 1;
            parents.add(curr);
            GeneralNode<T> copy = new GeneralNode<T>(parents.pop());
            copy.getNodes().set(path.charAt(path.length() - i) - 48, node);

            while (!parents.isEmpty()) {
                i++;
                GeneralNode<T> prevCopy = copy;
                copy = new GeneralNode<>(parents.pop());
                copy.getNodes().set(path.charAt(path.length() - i) - 48, prevCopy);
            }

            return new ImmutableList<T>(copy, size + 1);
        }
    }

    public boolean isEmpty() {
        return root == null;
    }

    public T get(int index) {
        String path = Integer.toBinaryString(index + 1).substring(1);
        GeneralNode<T> curr = root;
        for (int i = 0; i < path.length(); i++) {
            curr = curr.getNodes().get(path.charAt(i) - 48);
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

        List<List<T>> all_values = new ArrayList<>();
        explore(root, all_values, 0);

        for (List<T> level : all_values) {
            for (T value : level) {
                stringBuilder.append(value).append(" ");
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

    private void explore(GeneralNode<T> node, List<List<T>> nodes, int level) {
        try {
            for (GeneralNode<T> child : node.getNodes()) {
                if (child != null) {
                    nodes.get(level).add(child.getValue());
                    explore(child, nodes, level + 1);
                }
            }
        } catch (IndexOutOfBoundsException e) {
            nodes.add(new ArrayList<>());
            for (GeneralNode<T> child : node.getNodes()) {
                if (child != null) {
                    nodes.get(level).add(child.getValue());
                    explore(child, nodes, level + 1);
                }
            }
        }
    }

    public int sizeCheck() {
        if (root == null) return 0;
        List<List<T>> nodes = new ArrayList<>();
        explore(root, nodes, 0);
        return (int) nodes.stream().mapToLong(List::size).sum() + 1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currIndex = -1;

            @Override
            public boolean hasNext() {
                return currIndex < size - 1;
            }

            @Override
            public T next() {
                currIndex++;
                return get(currIndex);
            }
        };
    }
}
