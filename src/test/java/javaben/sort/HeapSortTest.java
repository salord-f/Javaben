package javaben.sort;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HeapSortTest {
    private HeapSort heapSort;
    private List<Integer> list;

    @Before
    public void init() {
        list = new ArrayList<>(Arrays.asList(7, 1, 6, 3, 0, 5, 2));
        heapSort = new HeapSort(list.size() - 1);
    }

    @Test
    public void getLesserChildTest() {
        assertEquals(1, heapSort.getLesserChild(list, 0));
        assertEquals(4, heapSort.getLesserChild(list, 1));
        assertEquals(6, heapSort.getLesserChild(list, 2));
        assertEquals(-1, heapSort.getLesserChild(list, 3));
        assertEquals(-1, heapSort.getLesserChild(list, 4));
        assertEquals(-1, heapSort.getLesserChild(list, 5));
        assertEquals(-1, heapSort.getLesserChild(list, 6));
    }

    @Test
    public void percolateDownTest() {
        heapSort.percolateDown(list, 0);
        assertEquals(new ArrayList<>(Arrays.asList(1, 0, 6, 3, 7, 5, 2)), list);
        heapSort.percolateDown(list, 0);
        assertEquals(new ArrayList<>(Arrays.asList(0, 1, 6, 3, 7, 5, 2)), list);
        heapSort.percolateDown(list, 2);
        assertEquals(new ArrayList<>(Arrays.asList(0, 1, 2, 3, 7, 5, 6)), list);
    }

    @Test
    public void heapifyTest() {
        heapSort.heapify(list);
        assertEquals(new ArrayList<>(Arrays.asList(0, 1, 2, 3, 7, 5, 6)), list);
    }
}
