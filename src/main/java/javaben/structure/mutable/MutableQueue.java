package javaben.structure.mutable;

import javaben.structure.Structure;
import javaben.structure.exception.EmptyQueueException;

public class MutableQueue extends Structure {
	private MutableStack stack_in = new MutableStack();
	private MutableStack stack_out = new MutableStack();

	public void enQueue(int x) {
		stack_in.push(x);
	}

	public int deQueue() {
		if (isEmpty()) {
			throw new EmptyQueueException();
		} else {
			if (stack_out.isEmpty()) {
				while (!stack_in.isEmpty()) {
					stack_out.push(stack_in.pop());
				}
			}
		}
		return stack_out.pop();

	}

	public boolean isEmpty() {
		return stack_in.isEmpty() && stack_out.isEmpty();
	}

}
