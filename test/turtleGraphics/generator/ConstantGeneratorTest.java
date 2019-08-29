package turtleGraphics.generator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConstantGeneratorTest {
    @Test
    public void alwaysReturnsConstant() {
        ConstantGenerator gen = new ConstantGenerator(0.739);
        assertEquals(0.739, gen.next(), 0);
        assertEquals(0.739, gen.next(), 0);
        assertEquals(0.739, gen.next(), 0);
    }
}
