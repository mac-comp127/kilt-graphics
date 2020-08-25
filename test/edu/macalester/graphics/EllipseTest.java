package edu.macalester.graphics;

import static edu.macalester.graphics.testsupport.RenderingTestMode.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.macalester.graphics.testsupport.GraphicsObjectTestSuite;
import edu.macalester.graphics.testsupport.RenderingTest;

public class EllipseTest implements GraphicsObjectTestSuite {

    private Ellipse ellipse;

    @Override
    public GraphicsObject getGraphicsObject() {
        return ellipse;
    }
    
    @RenderingTest(modes = { PLAIN, STROKED, FILLED, FILLED_AND_STROKED, HIT_TEST })
    void regularEllipse() {
        ellipse = new Ellipse(17, 24.5, 60.5, 30.1);
        assertChangedAtEachStep(
            () -> ellipse.setCenter(new Point(45, 60)),
            () -> ellipse.setSize(new Point(56.5, 33.3)),
            () -> ellipse.moveBy(5, 3)
        );
        assertEquals(new Ellipse(19.75, 47.95, 56.5, 33.3), ellipse);
    }

    @RenderingTest(modes = { STROKED, FILLED, HIT_TEST })
    void degenerateEllipse() {
        ellipse = new Ellipse(10, 20, 30, 0);
    }

    @RenderingTest(modes = { STROKED, FILLED, HIT_TEST })
    void zeroEllipse() {
        ellipse = new Ellipse(20, 10, 0, 0);
    }

    @RenderingTest(modes = { STROKED, FILLED, HIT_TEST })
    void negativeSizeEllipse() {
        ellipse = new Ellipse(80, 70, -60, -50);
    }
}
