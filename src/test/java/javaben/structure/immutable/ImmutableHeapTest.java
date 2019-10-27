package javaben.structure.immutable;

import javaben.structure.Node;
import javaben.structure.Tuple;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class ImmutableHeapTest {

    @Test
    public void minHeapTest() {
        ImmutableHeap heap = new ImmutableHeap();
        for (int i = 30; i > 0; i--) {
            heap = heap.add(i);
        }
        System.out.println(heap);
        checkNode(heap.root);
    }

    private void checkNode(Node node) {
        if (node == null) {
            return;
        }
        if (node.getNodes()[0] != null) {
            assertTrue(node.getValue() < node.getNodes()[0].getValue());
            checkNode(node.getNodes()[0]);
        }
        if (node.getNodes()[1] != null) {
            assertTrue(node.getValue() < node.getNodes()[1].getValue());
            checkNode(node.getNodes()[1]);
        }
    }

    @Test
    public void immutabilityTest() {
        ImmutableHeap heapA = new ImmutableHeap();
        ImmutableHeap heapB = heapA.add(10);
        ImmutableHeap heapC = heapB.add(5);
        ImmutableHeap heapD = heapC.add(6);
        ImmutableHeap heapE = heapD.add(2);
        ImmutableHeap heapF = heapE.add(7);
        assertNull(heapA.root);
        assertNotNull(heapB.root);

        assertEquals(10, (int) heapB.pop().right);
        assertEquals(10, (int) heapB.pop().right);
        assertEquals(5, (int) heapC.pop().right);
        assertEquals(5, (int) heapC.pop().right);
        assertEquals(5, (int) heapD.pop().right);
        assertEquals(5, (int) heapD.pop().right);
        assertEquals(2, (int) heapE.pop().right);
        assertEquals(2, (int) heapE.pop().right);
        assertEquals(2, (int) heapF.pop().right);
        assertEquals(2, (int) heapF.pop().right);

    }

    @Test
    public void popTest() {
        ImmutableHeap heap = new ImmutableHeap();

        heap = heap.add(10);
        heap = heap.add(1);
        heap = heap.add(3);
        heap = heap.add(2);
        heap = heap.add(5);

        Tuple<ImmutableHeap, Integer> tuple = heap.pop();
        assertEquals(1, (int) tuple.right);
        heap = tuple.left;

        tuple = heap.pop();
        assertEquals(2, (int) tuple.right);
        heap = tuple.left;

        tuple = heap.pop();
        assertEquals(3, (int) tuple.right);
        heap = tuple.left;

        tuple = heap.pop();
        assertEquals(5, (int) tuple.right);
        heap = tuple.left;

        tuple = heap.pop();
        assertEquals(10, (int) tuple.right);
    }
}
