package javaben.structure.immutable;

import javaben.structure.Node;
import javaben.structure.Tuple;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.*;

public class ImmutableMinHeapTest {

	@Test
	public void minHeapTest() {
		ImmutableMinHeap heap = new ImmutableMinHeap();
		for (int i = 30; i > 0; i--) {
			heap = heap.push(i);
		}
		System.out.println(heap);
		checkNode(heap.getRoot());
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
	public void immutableTest() {
		ImmutableMinHeap heapA = new ImmutableMinHeap();
		ImmutableMinHeap heapB = heapA.push(10);
		assertNull(heapA.getRoot());
		assertNotNull(heapB.getRoot());
	}

	@Test
	public void popTest() {
		ImmutableMinHeap heap = new ImmutableMinHeap();

		heap = heap.push(10);
		heap = heap.push(1);
		heap = heap.push(3);
		heap = heap.push(2);
		heap = heap.push(5);

		Tuple<ImmutableMinHeap, Integer> tuple = heap.pop();
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
