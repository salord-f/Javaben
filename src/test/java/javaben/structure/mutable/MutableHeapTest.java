package javaben.structure.mutable;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MutableHeapTest {
    private MutableHeap heap;

    @Before
    public void init() {
        heap = new MutableHeap();
        for (int i = 1; i < 10; i++) {
            heap.add(10-i);
        }
    }

    @Test
    public void peekTest() {
        assertEquals(1, heap.peek());
    }

    @Test
    public void popTest() {
        for (int i = 1; i < 9; i++) {
            assertEquals(i, heap.pop());
        }
    }
}
