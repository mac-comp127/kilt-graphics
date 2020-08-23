package comp127graphics;

import comp127graphics.testsupport.GraphicsObjectTestSuite;
import comp127graphics.testsupport.RenderingTest;

import static comp127graphics.testsupport.RenderingTestMode.*;

public class ArcTest implements GraphicsObjectTestSuite {
    private Arc arc;

    @Override
    public GraphicsObject getGraphicsObject() {
        return arc;
    }

    @RenderingTest(modes = { PLAIN, STROKED, HIT_TEST })
    void simple() {
        arc = new Arc(20, 30, 60, 42, 10, 70);
    }

    @RenderingTest(modes = { STROKED, HIT_TEST })
    void negativeSweep() {
        arc = new Arc(20, 30, 60, 42, -10, -70);
    }

    @RenderingTest(modes = { STROKED, HIT_TEST })
    void overswept() {
        arc = new Arc(20, 30, 60, 42, 10, 1000);
    }
}
