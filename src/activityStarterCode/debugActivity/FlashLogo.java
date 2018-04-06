package activityStarterCode.debugActivity;

import comp124graphics.CanvasWindow;
import comp124graphics.GraphicsGroup;
import comp124graphics.Polygon;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dkluver on 11/17/17.
 */
public class FlashLogo extends GraphicsGroup {
    public void draw() {
        List<Point2D.Double> points = new ArrayList<>();

        Point2D.Double flashPoint = new Point2D.Double(179, 21);
        points.add(flashPoint);

        flashPoint.setLocation(93, 105);
        points.add(flashPoint);

        flashPoint.setLocation(116, 100);
        points.add(flashPoint);

        flashPoint.setLocation(76, 146);
        points.add(flashPoint);

        flashPoint.setLocation(100, 136);
        points.add(flashPoint);

        flashPoint.setLocation(47, 204);
        points.add(flashPoint);

        flashPoint.setLocation(147, 115);
        points.add(flashPoint);

        flashPoint.setLocation(126, 118);
        points.add(flashPoint);

        flashPoint.setLocation(158, 81);
        points.add(flashPoint);

        flashPoint.setLocation(135, 85);
        points.add(flashPoint);

        Polygon poly = new Polygon(points);
        poly.setFillColor(Color.YELLOW);
        poly.setFilled(true);
        add(poly);
    }

    public static void main(String[] args) {
        // should draw a lightning bolt (the flash logo)
        CanvasWindow window = new CanvasWindow("flash logo", 255, 255);
        FlashLogo fl = new FlashLogo();
        fl.draw();
    }
}
