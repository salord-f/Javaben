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
    }

    @Test
    public void popTest() {
        assertEquals(1, heap.pop());
        assertEquals(2, heap.pop());
        assertEquals(3, heap.pop());
        assertEquals(5, heap.pop());
        assertEquals(6, heap.pop());
        assertEquals(7, heap.pop());
        assertEquals(8, heap.pop());
    }
}
