package javaben.structure.immutable;

import javaben.structure.Tuple;

import java.util.Stack;


public class ImmutableStack extends Stack<Integer> {

	@Override
	public synchronized Integer push(Integer item) {
		throw new UnsupportedOperationException("Default push is not supported");
	}

	@Override
	public synchronized Integer pop() {
		throw new UnsupportedOperationException("Default pop is not supported");

	}

	public synchronized ImmutableStack immutablePush(Integer item) {
		ImmutableStack stack = (ImmutableStack) this.clone();
		stack.addElement(item);
		return stack;
	}

	public synchronized Tuple<ImmutableStack, Integer> immutablePop() {
		Integer obj;
		int len = size();

		obj = peek();
		ImmutableStack stack = (ImmutableStack) this.clone();

		stack.removeElementAt(len - 1);

		return new Tuple<>(stack, obj);
	}
}
