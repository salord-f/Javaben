package javaben.structure;

import lombok.Data;

@Data
public class Node {
    private int value;
    private int order;
    private Node[] nodes;

    public Node(int value, int order) {
        this.value = value;
        this.order = order;
        nodes = new Node[order];
    }
}
