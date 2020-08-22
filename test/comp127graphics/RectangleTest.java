package comp127graphics;

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
    void filledNoStroke() {
        rect = new Rectangle(17, 24.5, 60.3, 30.1);
        assertChangedAtEachStep(
            () -> rect.setStroked(false),
            () -> rect.setFillColor(Color.BLUE)
        );
    }
}
