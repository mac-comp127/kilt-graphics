package edu.macalester.graphics;

import edu.macalester.graphics.testsupport.GraphicsObjectTestSuite;
import edu.macalester.graphics.testsupport.RenderingTest;

import static edu.macalester.graphics.testsupport.RenderingTestMode.*;

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
