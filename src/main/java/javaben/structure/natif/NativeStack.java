package javaben.structure.natif;

import javaben.structure.common.CommonStack;

import java.util.Stack;

public class NativeStack extends CommonStack {
	private Stack<Integer> stack = new Stack<>();

	public void push(int value) {
		stack.push(value);
	}

	public Integer pop() {
		return stack.pop();
	}
}
