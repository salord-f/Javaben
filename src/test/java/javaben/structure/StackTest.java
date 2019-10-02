package javaben.structure;

import javaben.structure.mutable.MutableStack;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StackTest {

	@Test
	public void stackTest() {
		MutableStack stack = new MutableStack();
		stack.push(1);
		stack.push(2);
		assertEquals(2, (long) stack.pop());
		assertEquals(1, (long) stack.pop());
	}
}
