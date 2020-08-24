package comp127graphics;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.Color;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import comp127graphics.testsupport.ImageComparison;

public class CanvasWindowTest {
    private CanvasWindow canvas;
    
    @Test
    void geometry() {
        canvas = new CanvasWindow("geometry", 120, 80);
        assertEquals(120, canvas.getWidth());
        assertEquals(80, canvas.getHeight());
        assertEquals(new Point(60, 40), canvas.getCenter());
    }

    @Test
    void graphics() throws IOException {
        canvas = new CanvasWindow("graphics", 240, 160);
        compareScreenShot("graphics-0-empty");
    
        canvas.setBackground(Color.BLUE);

        var image0 = new Image("images/foxflower.png");
        canvas.add(image0);
        image0.setCenter(canvas.getCenter());  // position after add

        var ellipse = new Ellipse(20, 30, 64, 32);
        ellipse.setFillColor(Color.CYAN);
        canvas.add(ellipse, 40, 20);

        var image1 = new Image("images/foxbot.png");
        image1.setY(canvas.getHeight() - image1.getHeight());  // position before add
        canvas.add(image1);

        compareScreenShot("graphics-1-allItems");

        canvas.remove(ellipse);
        compareScreenShot("graphics-2-afterRemove");

        canvas.removeAll();
        compareScreenShot("graphics-3-afterRemoveAll");
    }

    @Test
    void getElementAt() throws IOException {
        var ellipse = new Ellipse(0, 0, 40, 30);
        var triangle = Path.makeTriangle(0, 50, 25, 0, 50, 50);
        var rect = new Rectangle(new Point(0, 0), new Point(40, 20));
        ellipse.setFillColor(Color.RED);
        triangle.setFillColor(Color.MAGENTA);
        rect.setFillColor(Color.ORANGE);

        canvas = new CanvasWindow("getElementAt", 100, 80);
        var group = new GraphicsGroup();
        group.add(triangle);
        group.add(rect);
        canvas.add(ellipse, 20, 20);
        canvas.add(group, 40, 10);

        // Doesn't add much test coverage benefit, but makes the subsequent lines easier to write
        compareScreenShot("getElementAt");

        assertEquals(ellipse, canvas.getElementAt(48, 37));
        assertEquals(triangle, canvas.getElementAt(new Point(52, 40)));
        assertEquals(rect, canvas.getElementAt(new Point(57, 28)));
        assertEquals(null, canvas.getElementAt(new Point(41, 51)));
    }

    @Test
    void embeddedComponentHandling() {
        // TODO
    }

    private void compareScreenShot(String testName) throws IOException {
        new ImageComparison(
            getClass().getSimpleName(),
            testName,
            canvas.screenShot(),
            100
        ).compare();
    }
}
