package javaben.structure.immutable;

import javaben.structure.Node;
import javaben.structure.Tuple;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.EmptyStackException;

@AllArgsConstructor
@NoArgsConstructor
public class ImmutableStack {

	private static final int ORDER = 1;

	private Node head;

	public ImmutableStack push(int value) {
		Node node = new Node(value, ORDER);
		node.getNodes()[0] = head;
		return new ImmutableStack(node);
	}

	public Tuple<ImmutableStack, Integer> pop() {
		try {
			return new Tuple<>(new ImmutableStack(head.getNodes()[0]), head.getValue());
		} catch (EmptyStackException e) {
			System.out.println("Empty stack");
		}
		return null;
	}

	public Integer peek() {
		try {
			return head.getValue();
		} catch (NullPointerException e) {
			throw new EmptyStackException();
		}
	}

	public boolean isEmpty() {
		return head == null;
	}

}
