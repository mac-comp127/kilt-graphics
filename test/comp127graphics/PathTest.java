package comp127graphics;

import comp127graphics.testsupport.GraphicsObjectTestSuite;
import comp127graphics.testsupport.RenderingTest;

import static comp127graphics.testsupport.RenderingTestMode.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

public class PathTest implements GraphicsObjectTestSuite {
    private Path path;

    @Override
    public GraphicsObject getGraphicsObject() {
        return path;
    }

    @RenderingTest(modes = { PLAIN, STROKED, FILLED, FILLED_AND_STROKED, HIT_TEST })
    void triangle() {
        path = Path.makeTriangle(60, 20, 20, 40, 35, 73);
        assertTrue(path.isClosed());
    }

    @RenderingTest(modes = { PLAIN, STROKED, FILLED, FILLED_AND_STROKED, HIT_TEST })
    void clockwise() {
        path = new Path(
            new Point(10, 10),
            new Point(90, 70),
            new Point(50, 90),
            new Point(90, 30),
            new Point(20, 80)
        );
        assertTrue(path.isClosed());
    }

    @RenderingTest(modes = { PLAIN, STROKED, FILLED, FILLED_AND_STROKED, HIT_TEST })
    void counterclockwise() {
        path = new Path(
            new Point(20, 80),
            new Point(90, 30),
            new Point(50, 90),
            new Point(90, 70),
            new Point(10, 10)
        );
        assertTrue(path.isClosed());
    }

    @RenderingTest(modes = { PLAIN, STROKED, FILLED, FILLED_AND_STROKED, HIT_TEST })
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
        assertFalse(path.isClosed());
    }

    @RenderingTest(modes = { FILLED_AND_STROKED, HIT_TEST })
    void transformed() {
        path = new Path(
            List.of(
                new Point(10, 10),
                new Point(90, 30),
                new Point(90, 70)),
            false
        );
        path.setVertices(
            List.of(
                new Point(10, 10),
                new Point(50, 90),
                new Point(90, 70),
                new Point(20, 80))
        );
        assertFalse(path.isClosed());
    }

    @RenderingTest(modes = { FILLED_AND_STROKED, HIT_TEST })
    void lineSegment() {
        path = new Path(List.of(
            new Point(10, 20),
            new Point(90, 80)));
        assertTrue(path.isClosed());
    }

    @RenderingTest(modes = { FILLED_AND_STROKED, HIT_TEST })
    void singlePoint() {
        path = new Path(List.of(
            new Point(10, 20)));
        assertTrue(path.isClosed());
    }

    @RenderingTest(modes = { FILLED_AND_STROKED, HIT_TEST })
    void empty() {
        path = new Path(List.of());
        assertTrue(path.isClosed());
    }
}
