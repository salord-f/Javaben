package javaben.structure.immutable;

import javaben.structure.Node;
import javaben.structure.Tuple;
import javaben.structure.mutable.MutableHeap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImmutableMinHeap {

	private static final int ORDER = 2;

	private Node root;

	private int size = 0; // 10 -> 1010 = 0 gauche, 1 droite, 0 gauche


	public ImmutableMinHeap push(int value) {
		if (root == null) {
			return new ImmutableMinHeap(new Node(value, 2), 1);
		}
		MutableHeap heap = new MutableHeap(root.copy(), size);
		heap.add(value);
		return new ImmutableMinHeap(heap.getRoot(), size + 1);
	}

	public boolean isEmpty() {
		return root == null;
	}

	public Tuple<ImmutableMinHeap, Integer> pop() {
		if (root == null) {
			throw new UnsupportedOperationException();
		}
		MutableHeap heap = new MutableHeap(root.copy(), size);
		int value = heap.pop();
		return new Tuple<>(new ImmutableMinHeap(heap.getRoot(), size - 1), value);
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
}
