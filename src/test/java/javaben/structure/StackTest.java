package javaben.structure;

import javaben.structure.immutable.ImmutableStack;
import javaben.structure.mutable.MutableStack;
import org.junit.Test;

import java.util.EmptyStackException;

import static org.junit.Assert.assertEquals;

public class StackTest {

	@Test
	public void stackTest() {
		MutableStack stack = new MutableStack();
		stack.push(1);
		stack.push(2);
		assertEquals(2, (int) stack.pop());
		assertEquals(1, (int) stack.pop());
	}

	@Test(expected = EmptyStackException.class)
	public void immutableStackEmptyTest() {
		ImmutableStack stack = new ImmutableStack();
		stack.peek();
	}

	@Test
	public void immutableStackPeekTest() {
		ImmutableStack stackA = new ImmutableStack();
		ImmutableStack stackB = stackA.immutablePush(1);
		assertEquals(1, (int) stackB.peek());
	}

	@Test
	public void immutableStackPopTest() {
		ImmutableStack stackA = new ImmutableStack();
		ImmutableStack stackB = stackA.immutablePush(1);
		Tuple<ImmutableStack, Integer> tuple = stackB.immutablePop();
		assertEquals(1, (int) tuple.y);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void immutablePushExceptionTest() {
		ImmutableStack stack = new ImmutableStack();
		stack.push(1);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void immutablePopExceptionTest() {
		ImmutableStack stack = new ImmutableStack();
		stack.pop();
	}
}
