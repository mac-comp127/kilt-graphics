package sen.expr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestSquarer {
    @Test
    public void testSimple() {
        assertEquals(16, Squarer.doSquare(4.0), 0.0001);
    }

    @Test
    public void testNegatives() {
        assertEquals(2.25, Squarer.doSquare(-1.5), 0.0001);
    }
}
