package edu.macalester.graphics;

import edu.macalester.graphics.testsupport.GraphicsObjectTestSuite;
import edu.macalester.graphics.testsupport.RenderingTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageTest implements GraphicsObjectTestSuite {
    public static final String FOXFLOWER_IMAGE = "res/foxflower.png";
    public static final String FOXBOT_IMAGE = "res/foxbot.png";

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
    void pixelAlignment() {
        image = new Image(FOXBOT_IMAGE);
        image.setPosition(-1.2, -50.8);
    }

    @RenderingTest
    void loadedFromBufferedImage() {
        try{
            InputStream resource = Image.class.getResourceAsStream("/" + FOXBOT_IMAGE);
            if (resource == null) {
                throw new IOException("No resource named /" + FOXBOT_IMAGE);
            }
            BufferedImage bufImg = ImageIO.read(resource);
            image = new Image(bufImg);
        } catch (IOException e) {
            image = new Image(20, 20, "skirl.png");
        }
    }

    @RenderingTest
    void empty() {
        image = new Image(1, 1);
        assertEquals(Point.ORIGIN, image.getSize());
    }

    @RenderingTest(osSpecificImageComparison = true)
    void missing() {
        image = new Image(20, 20, "frotzle.png");
    }
}
