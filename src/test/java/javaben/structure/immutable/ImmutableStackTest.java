package javaben.structure.immutable;

import javaben.structure.Tuple;
import org.junit.Test;

import java.util.EmptyStackException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ImmutableStackTest {

	@Test(expected = EmptyStackException.class)
	public void immutableStackEmptyTest() {
		ImmutableStack stack = new ImmutableStack();
		assertTrue(stack.isEmpty());
		stack.peek();
	}

	@Test
	public void immutableStackPeekTest() {
		ImmutableStack stackA = new ImmutableStack();
		ImmutableStack stackB = stackA.push(1);
		assertTrue(stackA.isEmpty());
		assertEquals(1, (int) stackB.peek());
	}

	@Test
	public void immutableStackPopTest() {
		ImmutableStack stackA = new ImmutableStack();
		ImmutableStack stackB = stackA.push(1);
		assertTrue(stackA.isEmpty());
		Tuple<ImmutableStack, Integer> tuple = stackB.pop();
		assertFalse(stackB.isEmpty());
		assertEquals(1, (int) tuple.right);
	}

	@Test(expected = EmptyStackException.class)
	public void immutableStackPopEmptyTest() {
		ImmutableStack stackA = new ImmutableStack();
		ImmutableStack stackB = stackA.push(1);
		assertTrue(stackA.isEmpty());
		Tuple<ImmutableStack, Integer> tuple = stackB.pop();
		assertFalse(stackB.isEmpty());
		tuple.left.pop();
	}
}
