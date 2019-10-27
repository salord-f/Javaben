package javaben.structure.mutable;

import javaben.structure.AVLNode;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MutableAVLTest {

    @Test
    public void insertTest() {
        MutableAVL tree = new MutableAVL();

        /* Constructing tree given in the above figure */
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);

        assertEquals("30 20 10 25 40 50 ", tree.getTree());
    }

    @Test
    public void deleteTest() {
        MutableAVL tree = new MutableAVL();

        /* Constructing tree given in the above figure */
        tree.insert(9);
        tree.insert(5);
        tree.insert(10);
        tree.insert(0);
        tree.insert(6);
        tree.insert(11);
        tree.insert(-1);
        tree.insert(1);
        tree.insert(2);

        assertEquals("9 1 0 -1 5 2 6 10 11 ", tree.getTree());

        tree.delete(10);

        assertEquals("1 0 -1 9 5 2 6 11 ", tree.getTree());
    }

    @Test
    public void searchTest() {
        MutableAVL tree = new MutableAVL();

        /* Constructing tree given in the above figure */
        tree.insert(9);
        tree.insert(5);
        tree.insert(10);
        tree.insert(0);
        tree.insert(6);
        tree.insert(11);
        tree.insert(-1);
        tree.insert(1);
        tree.insert(2);

        AVLNode node = tree.search(2);
        assertEquals(2, (int) node.getValue());
    }

}
