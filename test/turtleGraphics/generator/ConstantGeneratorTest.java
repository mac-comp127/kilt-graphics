package turtleGraphics.generator;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConstantGeneratorTest {
    @Test
    public void alwaysReturnsConstant() {
        ConstantGenerator gen = new ConstantGenerator(0.739);
        assertEquals(0.739, gen.next(), 0);
        assertEquals(0.739, gen.next(), 0);
        assertEquals(0.739, gen.next(), 0);
    }
}
