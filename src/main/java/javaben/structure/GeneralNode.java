package javaben.structure;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GeneralNode<T> {
    private T value;
    private int order;
    private List<GeneralNode<T>> nodes;

    public GeneralNode(T value, int order) {
        this.value = value;
        this.order = order;
        nodes = new ArrayList<>();
        for (int i = 0; i < order; i++) {
            nodes.add(null);
        }
    }

    public GeneralNode(GeneralNode<T> node) {
        this.value = node.value;
        this.order = node.order;
        nodes = new ArrayList<>();
        nodes.addAll(node.getNodes());
    }
}
