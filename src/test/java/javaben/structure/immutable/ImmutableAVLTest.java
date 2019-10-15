package javaben.structure.immutable;

import javaben.structure.AVLNode;
import javaben.structure.immutable.ImmutableAVL;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ImmutableAVLTest {

    @Test
    public void insertTest() {
        ImmutableAVL tree = new ImmutableAVL();

        /* Constructing tree given in the above figure */
        tree = tree.insert(10);
        tree = tree.insert(20);
        tree = tree.insert(30);
        ImmutableAVL tree1 = tree.insert(40);
        tree1 = tree1.insert(50);
        tree1 = tree1.insert(25);

        assertEquals("30 20 10 25 40 50 ", tree1.getTree());
        assertEquals("20 10 30 ", tree.getTree());
    }

    @Test
    public void deleteTest() {
        ImmutableAVL tree = new ImmutableAVL();

        /* Constructing tree given in the above figure */
        tree = tree.insert(9);
        tree = tree.insert(5);
        tree = tree.insert(10);
        tree = tree.insert(0);
        tree = tree.insert(6);
        tree = tree.insert(11);
        tree = tree.insert(-1);
        tree = tree.insert(1);
        tree = tree.insert(2);

        assertEquals("9 1 0 -1 5 2 6 10 11 ", tree.getTree());

        ImmutableAVL tree1 = tree.delete(10);

        assertEquals("1 0 -1 9 5 2 6 11 ", tree1.getTree());
        assertEquals("9 1 0 -1 5 2 6 10 11 ", tree.getTree());
    }

    @Test
    public void searchTest() {
        ImmutableAVL tree = new ImmutableAVL();

        /* Constructing tree given in the above figure */
        tree = tree.insert(9);
        tree = tree.insert(5);
        tree = tree.insert(10);
        tree = tree.insert(0);
        tree = tree.insert(6);
        tree = tree.insert(11);
        tree = tree.insert(-1);
        tree = tree.insert(1);
        tree = tree.insert(2);

        AVLNode node = tree.search(2);
        assertEquals(2, (int) node.getValue());
    }

}
