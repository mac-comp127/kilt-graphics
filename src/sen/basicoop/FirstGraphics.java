package sen.basicoop;

import comp124graphics.CanvasWindow;
import comp124graphics.Rectangle;

import java.awt.*;


public class FirstGraphics {
    public static void main(String [] args) {
        CanvasWindow canvas = new CanvasWindow("FirstGraphics", 500, 500);

        Rectangle redRect = new Rectangle(100, 50, 25, 40);
        redRect.setStrokeColor(new Color(255, 102, 102));
        redRect.setStroked(true);
        redRect.setStrokeWidth(3);
        canvas.add(redRect);

        Rectangle blueRect = new Rectangle(300, 200, 100, 100);
        blueRect.setStrokeColor(new Color(102, 102, 255));
        blueRect.setStroked(true);
        blueRect.setStrokeWidth(10);
        canvas.add(blueRect);
    }
}
