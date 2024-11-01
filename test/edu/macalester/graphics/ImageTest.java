package edu.macalester.graphics;

import edu.macalester.graphics.testsupport.GraphicsObjectTestSuite;
import edu.macalester.graphics.testsupport.RenderingTest;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static edu.macalester.graphics.testsupport.RenderingTestMode.PLAIN;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        try {
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

    @Test
    void pixelsConstructorsCheckBounds() {
        for (var ctorCall : List.of((Executable)
            () -> new Image(2, 3, new float[18], Image.PixelFormat.GRAYSCALE),  // too large
            () -> new Image(3, 2, new float[6], Image.PixelFormat.RGB)          // too small
        )) {
            assertThrows(IllegalArgumentException.class, ctorCall);
        }
    }

    @RenderingTest(modes = { PLAIN })
    void pixelsFloatGrayscale() {
        image = new Image(70, 90, generateFloatData(70, 90, 1), Image.PixelFormat.GRAYSCALE);
    }

    @RenderingTest(modes = { PLAIN })
    void pixelsFloatRGB() {
        image = new Image(100, 80, generateFloatData(100, 80, 3), Image.PixelFormat.RGB);
    }

    @RenderingTest(modes = { PLAIN })
    void pixelsFloatARGB() {
        image = new Image(97, 93, generateFloatData(97, 93, 4), Image.PixelFormat.ARGB);
    }

    private float[] generateFloatData(int w, int h, int chans) {
        float[] pixels = new float[w * h * chans];
        int i = 0;
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                for (int c = 0; c < chans; c++) {
                    // This is engineered to include a mix of 0, NaN, out of bounds, and values
                    // that are very different per channel
                    pixels[i++] = (float)
                        ( Math.sin(x * 3.0 * (c * 2.7 + 1) / w)
                        / Math.sin(y * 4.0 * (c * 1.9 + 1) / w));
                }
            }
        }
        assertTrue(Float.isNaN(pixels[0]));
        return pixels;
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
