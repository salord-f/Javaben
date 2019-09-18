package javaben.sort;

import javaben.Generator;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class SortifinatorTest {
    private Generator generator = new Generator();

    @Test
    public void selectionTest() {
        List<Integer> sortedList = Sortifinator.selectionSort(generator.unsortedListGenerator(100, 100));
        for (int i = 1; i < 100; i++) {
            assertTrue(sortedList.get(i) <= sortedList.get(i - 1));
        }
    }
}
