package comp127graphics;

import comp127graphics.testsupport.GraphicsObjectTestSuite;
import comp127graphics.testsupport.RenderingTest;

import static comp127graphics.testsupport.TestRenderingMode.*;

import java.util.List;

public class PathTest implements GraphicsObjectTestSuite {
    private Path path;

    @Override
    public GraphicsObject getGraphicsObject() {
        return path;
    }

    @RenderingTest
    void triangle() {
        path = Path.makeTriangle(60, 20, 20, 40, 35, 73);
    }

    @RenderingTest
    void clockwise() {
        path = new Path(
            new Point(10, 10),
            new Point(90, 30),
            new Point(90, 70),
            new Point(50, 90),
            new Point(20, 80)
        );
    }

    @RenderingTest
    void counterclockwise() {
        path = new Path(
            new Point(20, 80),
            new Point(50, 90),
            new Point(90, 70),
            new Point(90, 30),
            new Point(10, 10)
        );
    }

    @RenderingTest
    void open() {
        path = new Path(
            List.of(
                new Point(10, 10),
                new Point(90, 30),
                new Point(90, 70),
                new Point(50, 90),
                new Point(20, 80)),
            false
        );
    }

    @RenderingTest(modes = { FILLED_AND_STROKED, HIT_TEST })
    void lineSegment() {
        path = new Path(List.of(
            new Point(10, 20),
            new Point(90, 80)));
    }

    @RenderingTest(modes = { FILLED_AND_STROKED, HIT_TEST })
    void singlePoint() {
        path = new Path(List.of(
            new Point(10, 20)));
    }

    @RenderingTest(modes = { FILLED_AND_STROKED, HIT_TEST })
    void emptyu() {
        path = new Path(List.of());
    }
}
