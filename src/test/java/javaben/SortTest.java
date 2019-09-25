package javaben;

import javaben.sort.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SortTest {
    private final static int SIZE = 10000;
    private final static int SEED = 516310114;
    private final Generator generator = new Generator(SEED);

    private void checkSortResult(List<Integer> result) {
        // System.out.println(result);
        for (int i = 1; i < SIZE; i++) {
            assertTrue(result.get(i) <= result.get(i - 1));
        }
    }

    private void checkSort(Sort sort) {
        List<Integer> list = generator.unsortedListGenerator(SIZE);
        sort.init(SIZE, SEED);
        checkSortResult(sort.sort(list));
    }

    @Test
    public void emptySortTest() {
        List<Integer> list = generator.unsortedListGenerator(SIZE);
        Sort emptySort = new EmptySort();
        emptySort.init(SIZE, SEED);
        List<Integer> result = emptySort.sort(list);
        for (int i = 0; i < list.size(); i++) {
            assertEquals(list.get(i), result.get(i));
        }
    }

    @Test
    public void countingSortTest() {
        Sort sort = new CountingSort();
        sort.testing = true;
        checkSort(sort);
    }

    @Test
    public void insertionSortTest() {
        checkSort(new InsertionSort());
    }

    @Test
    public void quickSortTest() {
        checkSort(new QuickSort());
    }

    @Test
    public void selectionSortTest() {
        checkSort(new SelectionSort());
    }

}