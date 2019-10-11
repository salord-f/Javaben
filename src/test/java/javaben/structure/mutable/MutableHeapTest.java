package javaben.structure.mutable;

import javaben.structure.Node;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MutableHeapTest {
	private MutableHeap heap;

	@Before
	public void init() {
		heap = new MutableHeap();
		for (int i = 30; i > 0; i--) {
			heap.add(i);
		}
	}

	@Test
	public void peekTest() {
		assertEquals(1, heap.peek());
	}

	@Test
	public void popTest() {
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
}
