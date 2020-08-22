package comp127graphics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;

public class RectangleTest implements GraphicsObjectTestSuite {

    private Rectangle rect;

    @Override
    public GraphicsObject getGraphicsObject() {
        return rect;
    }
    
    @RenderingTest
    void defaultStyle() {
        rect = new Rectangle(8, 20, 25, 10);
    }
    
    @RenderingTest
    // @HitTest // TODO
    void unfilledCustomStroke() {
        rect = new Rectangle(new Point(10, 20), new Point(30, 40));
        assertChangedAtEachStep(
            () -> rect.setStrokeColor(Color.MAGENTA),
            () -> rect.setStrokeWidth(12)
        );
    }
    
    @RenderingTest
    void filledStroked() {
        rect = new Rectangle(17.5, 24, 60, 30);
        assertChangedAtEachStep(
            () -> rect.setStrokeColor(Color.CYAN),
            () -> rect.setStrokeWidth(3),
            () -> rect.setFillColor(Color.BLUE)
        );
    }
    
    @RenderingTest
    void filledNoStrokeWithRearrangement() {
        rect = new Rectangle(17, 24.5, 60.5, 30.1);
        assertChangedAtEachStep(
            () -> rect.setStroked(false),
            () -> rect.setFillColor(Color.BLUE),
            () -> rect.setCenter(new Point(45, 60)),
            () -> rect.setSize(new Point(56.5, 33.3))
        );
        assertEquals(new Rectangle(14.75, 44.95, 56.5, 33.3), rect);
    }
}
