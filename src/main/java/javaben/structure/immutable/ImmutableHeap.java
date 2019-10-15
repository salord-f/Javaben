package javaben.structure.immutable;

import javaben.structure.EmptyHeapException;
import javaben.structure.Node;
import javaben.structure.Tuple;

import java.util.Stack;

public class ImmutableHeap extends ImmutableBinaryTree {

	public ImmutableHeap() {
		super();
	}

	public ImmutableHeap(Node root, int size) {
		super(root, size);
	}

	public ImmutableHeap add(Integer value) {
		if (size == 0) {
			return new ImmutableHeap(new Node(value, 2), 1);
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

			boolean climbing = false;

			if (node.getValue() < copy.getValue()) {
				swapWithParent(node, copy);
				climbing = true;
			}

			while (!parents.isEmpty()) {
				i++;
				Node prevCopy = copy;
				copy = new Node(parents.pop());
				copy.getNodes()[path.charAt(path.length() - i) - 48] = prevCopy;
				if (climbing) {
					if (prevCopy.getValue() < copy.getValue()) {
						swapWithParent(prevCopy, copy);
					} else {
						climbing = false;
					}
				}
			}

			return new ImmutableHeap(copy, size + 1);
		}
	}

	public int peek() {
		return root.getValue();
	}

	public Tuple<ImmutableHeap, Integer> pop() {
		if (isEmpty()) {
			throw new EmptyHeapException();
		} else if (size == 1) {
			return new Tuple<>(new ImmutableHeap(null, 0), root.getValue());
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
			int lastValue = copy.getNodes()[path.charAt(path.length() - i) - 48].getValue();
			copy.getNodes()[path.charAt(path.length() - i) - 48] = null;

			while (!parents.isEmpty()) {
				i++;
				Node prevCopy = copy;
				copy = new Node(parents.pop());
				copy.getNodes()[path.charAt(path.length() - i) - 48] = prevCopy;
			}

			copy.setValue(lastValue);
			curr = copy;
			while (curr.getNodes()[0] != null) {
				Node minNode = curr.getNodes()[0];
				int min = minNode.getValue();
				int argMin = 0;
				try {
					if (curr.getNodes()[1].getValue() < min) {
						minNode = curr.getNodes()[1];
						min = minNode.getValue();
						argMin = 1;
					}
				} catch (NullPointerException e) {
					// empty
				}
				if (min < curr.getValue()) {
					minNode = new Node(minNode);
					curr.getNodes()[argMin] = minNode;
					swapWithParent(minNode, curr);
					curr = minNode;
				} else {
					break;
				}
			}

			return new Tuple<>(new ImmutableHeap(copy, size - 1), root.getValue());
		}



	}
}
