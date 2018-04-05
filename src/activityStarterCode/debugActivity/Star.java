package activityStarterCode.debugActivity;

import comp124graphics.CanvasWindow;
import comp124graphics.GraphicsGroup;
import comp124graphics.Polygon;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dkluver on 11/17/17.
 */
public class Star extends GraphicsGroup {
    public void draw() {
        // get the star shape, centered at (0,0)
        List<Point2D.Double> starPoints = getStarPoints();
        // translate the shape to be correctly centered)
        translateStarPoints(starPoints, 100, 100);

        Polygon poly = new Polygon(starPoints);
        poly.setFillColor(Color.YELLOW);
        poly.setFilled(true);
        add(poly);
    }

    private void translateStarPoints(List<Point2D.Double> points, double dx, double dy) {
        for(int i = 1; i<points.size(); i++) {
            Point2D.Double p = points.get(i);
            p.setLocation(p.getX()+dx, p.getY()+dy);
        }
    }

    // note - these points are all 100% correct. It would be too simple (and
    // hard to fix) for me to make an error in these points. To be clear, there
    // is no bug in this method.
    public List<Point2D.Double> getStarPoints() {
        List<Point2D.Double> points = new LinkedList<>();
        points.add(new Point2D.Double(58.78,   80.9));
        points.add(new Point2D.Double(0.0,     40.0));
        points.add(new Point2D.Double(-58.78,  80.9));
        points.add(new Point2D.Double(-38.04,  12.36));
        points.add(new Point2D.Double(-95.11, -30.9));
        points.add(new Point2D.Double(-23.51, -32.36));
        points.add(new Point2D.Double(0.0  ,  -100.0));
        points.add(new Point2D.Double(23.51,  -32.36));
        points.add(new Point2D.Double(95.11,  -30.9));
        points.add(new Point2D.Double(38.04,   12.36));
        return points;
    }

    public static void main(String[] args) {
        CanvasWindow window = new CanvasWindow("star", 200, 200);
        Star group = new Star();
        group.draw();
        window.add(group);
    }
}
