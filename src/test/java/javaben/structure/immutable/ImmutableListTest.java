package javaben.structure.immutable;

import javaben.structure.Tuple;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ImmutableListTest {
    private ImmutableList<Tuple<Integer, Integer>> immutableList;

    @Before
    public void init() {
        immutableList = new ImmutableList<>();
        for (int i = 0; i < 10; i++) {
            immutableList = immutableList.add(new Tuple<>(i, i));
        }
    }

    @Test
    public void immutabilityAddTest() {
        int sizeBefore = immutableList.size;
        int realSizeBefore = immutableList.sizeCheck();
        ImmutableList immutableList1 = immutableList.add(new Tuple<>(10, 10));
        assertEquals(sizeBefore, immutableList.size);
        assertEquals(realSizeBefore, immutableList.sizeCheck());
        assertEquals(sizeBefore + 1, immutableList1.size);
        assertEquals(realSizeBefore + 1, immutableList1.sizeCheck());
    }

    @Test
    public void getTest() {
        for (int i = 0; i < 10; i++) {
            assertEquals(new Tuple<>(i, i), immutableList.get(i));
        }
    }

    @Test
    public void iterableTest() {
        int i = 0;
        for (Tuple<Integer, Integer> tuple : immutableList) {
            assertEquals(new Tuple<>(i, i), tuple);
            i++;
        }
    }
}
