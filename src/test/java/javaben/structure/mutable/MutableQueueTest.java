package javaben.structure.mutable;

import javaben.structure.exception.EmptyQueueException;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class MutableQueueTest {
    @Test(expected = EmptyQueueException.class)
    public void mutableQueueEmptyTest() {
        MutableQueue queue = new MutableQueue();
        assertTrue(queue.isEmpty());
        queue.deQueue();
    }

    @Test
    public void mutableQueueDeQueueTest() {
        MutableQueue queue = new MutableQueue();
        queue.enQueue(1);
        assertEquals(1, queue.deQueue());
        queue.enQueue(10);
        queue.enQueue(2);
        assertEquals(10, queue.deQueue());
        assertEquals(2, queue.deQueue());
    }

    @Test(expected = EmptyQueueException.class)
    public void mutableQueueDeQueueEmptyTest() {
        MutableQueue queue = new MutableQueue();
        queue.enQueue(1);
        assertEquals(1, queue.deQueue());
        queue.deQueue();
    }
}
