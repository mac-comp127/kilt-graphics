package activityStarterCode.sortingArrays;

import org.junit.Test;

import static activityStarterCode.sortingArrays.ArraySort.sort;
import static org.junit.Assert.assertArrayEquals;

public class ArraySortTest {

    @Test
    public void testSingleElement() {
        assertArrayEquals(sort(new String[]{"7"}), new String[]{"7"});
    }

}
