package comp127graphics;

import comp127graphics.testsupport.GraphicsObjectTestSuite;
import comp127graphics.testsupport.RenderingTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ImageTest implements GraphicsObjectTestSuite {
    private static final String FOXFLOWER_IMAGE = "images/foxflower.png";
    private static final String FOXBOT_IMAGE = "images/foxbot.png";

    private Image image;

    @Override
    public GraphicsObject getGraphicsObject() {
        return image;
    }
    
    @RenderingTest
    void simple() {
        image = new Image(-30, -40, FOXFLOWER_IMAGE);
    }
        
    @RenderingTest
    void scaledX() {
        image = new Image(FOXBOT_IMAGE);
        assertChangedAtEachStep(
            () -> image.setMaxWidth(80),
            () -> image.setCenter(50, 50)
        );
    }
        
    @RenderingTest
    void scaledY() {
        image = new Image(FOXBOT_IMAGE);
        assertChangedAtEachStep(
            () -> image.setMaxHeight(80),
            () -> image.setCenter(50, 50)
        );
    }
        
    @RenderingTest
    void scaledAndImageChanged() {
        image = new Image(FOXBOT_IMAGE);
        assertChangedAtEachStep(
            () -> image.setMaxWidth(90),
            () -> image.setMaxHeight(90),
            () -> image.setImagePath(FOXFLOWER_IMAGE),
            () -> image.setCenter(50, 50)
        );
        assertEquals(210, image.getImageWidth());
        assertEquals(213, image.getImageHeight());
    }

    @RenderingTest
    void empty() {
        image = new Image(1, 1);
        assertEquals(Point.ORIGIN, image.getSize());
    }

    @RenderingTest
    void missing() {
        image = new Image(20, 20, "frotzle.png");
    }
}
