package comp127graphics;

import java.awt.Color;

public class RectangleTest implements GraphicsObjectTestSuite {

    private Rectangle rect;

    @Override
    public GraphicsObject getGraphicsObject() {
        return rect;
    }
    
    @RenderingTest
    void unfilledCustomStroke() {
        rect = new Rectangle(10, 20, 30, 40);
        rect.setStrokeColor(Color.MAGENTA);
        rect.setStrokeWidth(7);
    }
}
