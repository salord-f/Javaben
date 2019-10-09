package javaben.structure.mutable;

import org.junit.Test;

import java.util.EmptyStackException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class MutableStackTest {

    @Test(expected = EmptyStackException.class)
    public void mutableStackEmptyTest() {
        MutableStack stack = new MutableStack();
        assertTrue(stack.isEmpty());
        stack.peek();
    }

    @Test
    public void mutableStackPeekTest() {
        MutableStack stackA = new MutableStack();
        stackA.push(1);
        assertEquals(1, (int) stackA.peek());
    }

    @Test
    public void mutableStackPopTest() {
        MutableStack stackA = new MutableStack();
        stackA.push(1);
        assertEquals(1, stackA.pop());
    }

    @Test(expected = EmptyStackException.class)
    public void mutableStackPopEmptyTest() {
        MutableStack stackA = new MutableStack();
        stackA.push(1);
        assertEquals(1, stackA.pop());
        stackA.pop();
    }
}
