package javaben.structure.immutable;

import javaben.structure.Node;

import java.util.List;
import java.util.Stack;

public class ImmutableArray extends ImmutableBinaryTree {
    public ImmutableArray(int size) {
        super();
        for (int i = 0; i < size; i++) {
            this.root = add(null).root;
            this.size++;
        }
    }

    public ImmutableArray(Node root, int size) {
        super(root, size);
    }

    public ImmutableArray set(int index, int value) {
        String path = Integer.toBinaryString(index + 1).substring(1);
        Stack<Node> parents = new Stack<>();
        Node curr = root;
        for (int i = 0; i < path.length(); i++) {
            parents.add(curr);
            curr = curr.getNodes()[path.charAt(i) - 48];
        }

        int i = 0;
        parents.add(curr);
        Node copy = new Node(parents.pop());
        copy.setValue(value);

        while (!parents.isEmpty()) {
            i++;
            Node prevCopy = copy;
            copy = new Node(parents.pop());
            copy.getNodes()[path.charAt(path.length() - i) - 48] = prevCopy;
        }

        return new ImmutableArray(copy, size);
    }

    public Integer get(int index) {
        return elementAt(index);
    }

    @Override
    public void method(String method, List<Integer> source) {
        switch (method) {
            case "set-random":
                for (int i = 0; i < source.size(); i++) {
                    this.root = this.set(source.get(i), i).root;
                }
                break;
            case "set-sequential":
                for (int i = 0; i < source.size(); i++) {
                    this.root = this.set(i, source.get(i)).root;
                }
                break;
            case "get-random":
                for (int i = 0; i < source.size(); i++) {
                    this.get(source.get(i));
                }
                break;
            case "get-sequential":
                for (int i = 0; i < source.size(); i++) {
                    this.get(i);
                }
                break;
        }
    }

    @Override
    public void setup(String method, List<Integer> source) {
        switch (method) {
            case "get-random":
                for (int i = 0; i < source.size(); i++) {
                    this.root = this.set(source.get(i), i).root;
                }
                break;
            case "get-sequential":
                for (int i = 0; i < source.size(); i++) {
                    this.root = this.set(i, source.get(i)).root;
                }
                break;
        }
    }
}
