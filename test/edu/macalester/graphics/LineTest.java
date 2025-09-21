package edu.macalester.graphics;

import edu.macalester.graphics.testsupport.GraphicsObjectTestSuite;
import edu.macalester.graphics.testsupport.RenderingTest;

import static edu.macalester.graphics.testsupport.RenderingTestMode.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LineTest implements GraphicsObjectTestSuite {
    private Line line;

    @Override
    public GraphicsObject getGraphicsObject() {
        return line;
    }

    @RenderingTest(modes = { PLAIN, STROKED, HIT_TEST })
    void horizontal() {
        line = new Line(10, 50, 90, 50);
    }

    @RenderingTest(modes = { STROKED, HIT_TEST })
    void diagonal() {
        line = new Line(new Point(1, 2), new Point(3, 4));
        assertChangedAtEachStep(
            () -> line.setStartPosition(new Point(40, 32)),
            () -> line.setEndPosition(new Point(60, 20)),
            () -> line.setPosition(new Point(31, 21))
        );
        assertEquals(31, line.getX1());
        assertEquals(21, line.getY1());
        assertEquals(51, line.getX2());
        assertEquals(9,  line.getY2());
        assertEquals(new Point(31, 21), line.getStartPosition());
        assertEquals(new Point(51, 9), line.getEndPosition());
    }
}