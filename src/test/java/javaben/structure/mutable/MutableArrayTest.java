package javaben.structure.mutable;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MutableArrayTest {
    private MutableArray mutableArray;

    @Before
    public void init() {
        mutableArray = new MutableArray(10);
    }

    @Test
    public void setAndGetTest() {
        assertNull(mutableArray.get(0));
        assertNull(mutableArray.get(9));
        mutableArray.set(0, 10);
        mutableArray.set(5, 4);
        assertEquals(10, (int) mutableArray.get(0));
        assertEquals(4, (int) mutableArray.get(5));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void outOfBoundsTest() {
        mutableArray.get(10);
    }
}
