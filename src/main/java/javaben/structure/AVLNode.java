package javaben.structure;

import lombok.Data;

@Data
public class AVLNode extends Node {
	private int height;

	public AVLNode(Integer value, int order) {
		super(value, order);
		this.height = 1;
	}

	public AVLNode(AVLNode node) {
		super(node);
		this.height = node.height;
	}
}
