package javaben.structure;

import lombok.Data;

@Data
public class Node {
    private Integer value;
    private int order;
    private Node[] nodes;

    public Node(Integer value, int order) {
        this.value = value;
        this.order = order;
        nodes = new Node[order];
    }

    public Node(Node node) {
        this.value = node.value;
        this.order = node.order;
        nodes = new Node[order];
        System.arraycopy(node.nodes, 0, this.nodes, 0, order);
    }
}
