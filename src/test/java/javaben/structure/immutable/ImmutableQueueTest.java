package javaben.structure.immutable;

import javaben.structure.EmptyQueueException;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class ImmutableQueueTest {
    @Test(expected = EmptyQueueException.class)
    public void immutableQueueEmptyTest() {
        ImmutableQueue queue = new ImmutableQueue();
        assertTrue(queue.isEmpty());
        queue.deQueue();
    }

    @Test
    public void immutableQueueEnQueueTest() {
        ImmutableQueue queue = new ImmutableQueue();
        queue.enQueue(1);
        assertTrue(queue.isEmpty());
    }

    @Test
    public void immutableQueueDeQueueTest() {
        ImmutableQueue queue = new ImmutableQueue();
        queue = queue.enQueue(1);
        assertEquals(1, (int) queue.deQueue().right);
        queue = queue.enQueue(10);
        assertEquals(1, (int) queue.deQueue().right);
        assertEquals(1, (int) queue.deQueue().right);
        ImmutableQueue queue1 = queue.deQueue().left;
        assertEquals(10, (int) queue1.deQueue().right);
        assertEquals(10, (int) queue1.deQueue().right);
    }

    @Test(expected = EmptyQueueException.class)
    public void immutableQueueDeQueueEmptyTest() {
        ImmutableQueue queue = new ImmutableQueue();
        queue = queue.enQueue(1);
        assertEquals(1, (int) queue.deQueue().right);
        assertEquals(1, (int) queue.deQueue().right);
        queue = queue.deQueue().left;
        queue.deQueue();
    }
}
