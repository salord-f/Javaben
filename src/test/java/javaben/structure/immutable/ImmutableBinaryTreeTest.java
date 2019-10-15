package javaben.structure.immutable;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ImmutableBinaryTreeTest {
    private ImmutableBinaryTree immutableBinaryTree;

    @Before
    public void init() {
        immutableBinaryTree = new ImmutableBinaryTree();
        for (int i = 0; i < 10; i++) {
            immutableBinaryTree = immutableBinaryTree.add(i);
        }
    }

    @Test
    public void immutabilityAddTest() {
        int sizeBefore = immutableBinaryTree.size;
        int realSizeBefore = immutableBinaryTree.sizeCheck();
        ImmutableBinaryTree immutableBinaryTree2 = immutableBinaryTree.add(10);
        assertEquals(sizeBefore, immutableBinaryTree.size);
        assertEquals(realSizeBefore, immutableBinaryTree.sizeCheck());
        assertEquals(sizeBefore + 1, immutableBinaryTree2.size);
        assertEquals(realSizeBefore + 1, immutableBinaryTree2.sizeCheck());
    }

    @Test
    public void immutabilityRemoveLastTest() {
        int sizeBefore = immutableBinaryTree.size;
        int realSizeBefore = immutableBinaryTree.sizeCheck();
        ImmutableBinaryTree immutableBinaryTree2 = immutableBinaryTree.removeLast();
        assertEquals(sizeBefore, immutableBinaryTree.size);
        assertEquals(realSizeBefore, immutableBinaryTree.sizeCheck());
        assertEquals(sizeBefore - 1, immutableBinaryTree2.size);
        assertEquals(realSizeBefore - 1, immutableBinaryTree2.sizeCheck());
    }

    @Test
    public void getTest() {
        for (int i = 0; i < 10; i++) {
            assertEquals(i, (int) immutableBinaryTree.elementAt(i));
        }
    }

}
