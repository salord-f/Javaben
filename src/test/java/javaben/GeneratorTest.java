package javaben;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GeneratorTest {
    private final static int SIZE = 10000;
    private final Generator generator = new Generator(516310114);

    @Test
    public void unsortedListGeneratorTest() {
        List<Integer> list = generator.unsortedListGenerator(SIZE);
        assertEquals(SIZE, list.size());
    }

    @Test
    public void sortedListGeneratorTest() {
        List<Integer> list = generator.sortedListGenerator(SIZE, "asc");
        assertEquals(SIZE, list.size());
        for (int i = 1; i < SIZE; i++) {
            assertTrue(list.get(i) > list.get(i - 1));
        }

        list = generator.sortedListGenerator(SIZE, "desc");
        assertEquals(SIZE, list.size());
        for (int i = 1; i < SIZE; i++) {
            assertTrue(list.get(i) < list.get(i - 1));
        }
    }
}
