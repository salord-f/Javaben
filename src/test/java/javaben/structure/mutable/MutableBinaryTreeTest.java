package javaben.structure.mutable;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MutableBinaryTreeTest {
    private MutableBinaryTree mutableBinaryTree;

    @Before
    public void init() {
        mutableBinaryTree = new MutableBinaryTree();
        for (int i = 0; i < 10; i++) {
            mutableBinaryTree.add(i);
        }
    }

    @Test
    public void getTest() {
        for (int i = 0; i < 10; i++) {
            assertEquals(i, mutableBinaryTree.elementAt(i));
        }
    }

    @Test
    public void removeAtTest() {
        for (int i = 0; i < 10; i++) {
            assertEquals(10 - i, mutableBinaryTree.size);
            mutableBinaryTree.removeAt(9 - i);
        }
        assertEquals(0, mutableBinaryTree.size);
    }
}
