package javaben.structure.mutable;

import javaben.structure.Node;

import java.util.ArrayList;
import java.util.List;

public class MutableArray {
    private List<Node> nodes;

    public MutableArray(int size) {
        nodes = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            nodes.add(new Node(null, 0));
        }
    }

    public void set(int index, int value) {
        nodes.get(index).setValue(value);
    }

    public Integer get(int index) {
        return nodes.get(index).getValue();
    }
}
