package javaben.structure.immutable;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ImmutableArrayTest {
    private ImmutableArray immutableArray;

    @Before
    public void init() {
        immutableArray = new ImmutableArray(10);
    }

    @Test
    public void setAndGetTest() {
        assertNull(immutableArray.get(0));
        assertNull(immutableArray.get(9));
        ImmutableArray immutableArray1 = immutableArray.set(0, 10);
        ImmutableArray immutableArray2 = immutableArray.set(5, 4);
        assertNull(immutableArray.get(0));
        assertNull(immutableArray.get(5));
        assertEquals(10, (int) immutableArray1.get(0));
        assertEquals(4, (int) immutableArray2.get(5));
        immutableArray1 = immutableArray2.set(1, 2);
        assertNull(immutableArray2.get(1));
        assertEquals(2, (int) immutableArray1.get(1));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void outOfBoundsTest() {
        immutableArray.get(10);
    }
}
