package javaben.structure.mutable;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MutableHeapTest {
    private MutableHeap heap;

    @Before
    public void init() {
        heap = new MutableHeap();
        heap.add(5);
        heap.add(8);
        heap.add(1);
        heap.add(3);
        heap.add(7);
        heap.add(6);
        heap.add(2);
        heap.add(4);
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
