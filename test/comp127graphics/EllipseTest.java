package comp127graphics;

import static comp127graphics.TestRenderingMode.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EllipseTest implements GraphicsObjectTestSuite {

    private Ellipse ellipse;

    @Override
    public GraphicsObject getGraphicsObject() {
        return ellipse;
    }
    
    @RenderingTest
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
