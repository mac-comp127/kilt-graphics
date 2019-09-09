package marsh;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConditionalsTest {

    @Test
    public void testSquareNum() {
        assertEquals(9, Conditionals.squareNum(3));
    }

    @Test
    public void testGreaterThanTen() {
        assertEquals(false, Conditionals.greaterThanTen(9));
    }
}
