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

	public Node copy() {
		Node copy = new Node(value, order);
		if (order >= 0) System.arraycopy(nodes, 0, copy.nodes, 0, order);
		return copy;
	}
}
