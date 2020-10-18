package edu.macalester.graphics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.awt.Color;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import edu.macalester.graphics.testsupport.GraphicsObjectTestSuite;
import edu.macalester.graphics.testsupport.RenderingTest;

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

    @RenderingTest
    void simple() {
        group = new GraphicsGroup();
        assertChangedAtEachStep(
            () -> group.add(circle),
            () -> group.add(triangle),
            () -> group.add(word, 24, 96)
        );
    }

    @RenderingTest
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

    @RenderingTest
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

    @RenderingTest
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

    @RenderingTest
    void rotated() {
        group = new GraphicsGroup();
        var subgroup = new GraphicsGroup(8, 4);
        assertChangedAtEachStep(
            () -> group.add(subgroup),
            () -> group.add(triangle),
            () -> subgroup.add(word, 20, 40),
            () -> word.setRotation(30),
            () -> triangle.setRotation(-30),
            () -> subgroup.setRotation(90),
            () -> group.setRotation(10)
        );
    }

    @RenderingTest
    void scaled() {
        group = new GraphicsGroup();
        var subgroup = new GraphicsGroup(8, 4);
        assertChangedAtEachStep(
            () -> group.add(subgroup),
            () -> group.add(circle),
            () -> subgroup.add(word, 20, 40),
            () -> word.setScale(-2),
            () -> circle.setScale(0.5, 1),
            () -> subgroup.setScale(1.1, 1),
            () -> group.setScale(1.1)
        );
    }

    @RenderingTest
    void scaledAndRotated() {
        group = new GraphicsGroup();
        var subgroup = new GraphicsGroup(8, 4);
        assertChangedAtEachStep(
            () -> group.add(subgroup),
            () -> group.add(circle),
            () -> subgroup.add(word, 20, 40),
            () -> circle.setRotation(20),
            () -> circle.setScale(0.5, 1),
            () -> subgroup.setScale(1.1, 1),
            () -> subgroup.setRotation(-10)
        );
    }


    @Test
    void transformedDimensions() {
        group = new GraphicsGroup();
        group.add(triangle, 20, 10);
        group.add(circle, 40, 60);
        assertEquals(new Point(36, 66), group.getSize());
        assertEquals(new Point(36, 66), group.getSizeInParent());

        circle.setScale(2);
        assertEquals(new Point(44, 74), group.getSize());
        assertEquals(new Point(44, 74), group.getSizeInParent());

        group.setScale(0.5);
        assertEquals(new Point(44, 74), group.getSize());
        assertEquals(new Point(22, 37), group.getSizeInParent());
    }

    @RenderingTest
    void anchored() {
        group = new GraphicsGroup();
        assertChangedAtEachStep(
            () -> group.add(word, 20, 40),
            () -> group.setRotation(30),
            () -> group.setScale(1.1, 1.5),
            () -> group.setAnchor(30, 80)
        );
    }

    @RenderingTest
    void empty() {
        group = new GraphicsGroup(1, 1);
    }

    @Test
    void boundsChangesPropagateUp() {
        group = new GraphicsGroup();
        var subgroup = new GraphicsGroup();
        var subsubgroup = new GraphicsGroup();
        subgroup.add(subsubgroup);
        group.add(subgroup);
        assertEquals(Point.ORIGIN, group.getSize());

        Rectangle rect = new Rectangle(-10, -20, 40, 50);
        subsubgroup.add(rect);
        assertEquals(new Point(40, 50), group.getSize());
        
        rect.setSize(3, 5);
        assertEquals(new Point(3, 5), group.getSize());
    }
}
