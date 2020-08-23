package comp127graphics;

import static comp127graphics.testsupport.RenderingTestMode.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import comp127graphics.testsupport.GraphicsObjectTestSuite;
import comp127graphics.testsupport.RenderingTest;

public class GraphicsGroupTest implements GraphicsObjectTestSuite {
    private GraphicsGroup group;
    private final Ellipse circle;
    private final Path triangle;
    private final GraphicsText word;

    public GraphicsGroupTest() {
        circle = new Ellipse(24, 8, 16, 16);
        circle.setFillColor(new Color(240, 0, 192));
        circle.setStrokeColor(new Color(0, 0, 32));

        triangle = Path.makeTriangle(12, 12, 24, 12, 18, 4);
        triangle.setFillColor(new Color(0, 192, 16));
        triangle.setStrokeColor(new Color(0, 32, 0));

        word = new GraphicsText("kilt");
        word.setFont("Arial", FontStyle.BOLD, 16);
        word.setFillColor(new Color(0, 0, 64));
    }

    @Override
    public GraphicsObject getGraphicsObject() {
        return group;
    }

    @RenderingTest(modes = { PLAIN, HIT_TEST })
    void simple() {
        group = new GraphicsGroup();
        assertChangedAtEachStep(
            () -> group.add(circle),
            () -> group.add(triangle),
            () -> group.add(word, 24, 96)
        );
    }

    @RenderingTest(modes = { PLAIN, HIT_TEST })
    void changesPropagateToGroup() {
        group = new GraphicsGroup();
        assertChangedAtEachStep(
            () -> group.add(circle),
            () -> group.add(triangle),
            () -> group.add(word),
            () -> word.setPosition(64, 48),
            () -> circle.setFillColor(Color.YELLOW),
            () -> triangle.setPosition(0, 72),
            () -> group.remove(triangle),
            () -> group.setPosition(8, 16)
        );
        assertNotChanged(() -> triangle.setStrokeWidth(10));
    }

    @RenderingTest(modes = { PLAIN, HIT_TEST })
    void removeAll() {
        group = new GraphicsGroup();
        assertChangedAtEachStep(
            () -> group.add(circle),
            () -> group.add(triangle),
            () -> group.add(word),
            () -> group.removeAll(),
            () -> group.add(circle)
        );
        assertNotChanged(() -> triangle.setStrokeWidth(10));
        assertNotChanged(() -> word.setText("bagpipe"));
    }

    @Test
    void removeElementNotPresent() {
        group = new GraphicsGroup();
        group.add(triangle);
        assertThrows(NoSuchElementException.class, () ->
            group.remove(circle));
        group.remove(triangle);
        assertThrows(NoSuchElementException.class, () ->
            group.remove(triangle));
    }

    @RenderingTest(modes = { PLAIN, HIT_TEST })
    void nested() {
        group = new GraphicsGroup();
        var subgroup = new GraphicsGroup(8, 4);
        var subsubgroup = new GraphicsGroup(9, 13);
        assertChangedAtEachStep(
            () -> group.add(subgroup),
            () -> subgroup.add(subsubgroup),
            () -> subgroup.add(word),
            () -> subsubgroup.add(circle),
            () -> subsubgroup.add(triangle),
            () -> subgroup.moveBy(0, 42)
        );

        assertEquals(word, group.getElementAt(10, 41));
        assertEquals(triangle, group.getElementAt(new Point(33, 66)));
        assertEquals(circle, group.getElementAt(48, 72));
        assertEquals(null, group.getElementAt(42, 67));
    }

    @RenderingTest(modes = { PLAIN, HIT_TEST })
    void empty() {
        group = new GraphicsGroup(1, 1);
    }
}
