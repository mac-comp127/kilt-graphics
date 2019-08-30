package activityStarterCode.debugActivity;

import comp124graphics.CanvasWindow;
import comp124graphics.GraphicsGroup;
import comp124graphics.Polygon;
import comp124graphics.Point;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dkluver on 11/17/17.
 */
public class Star extends GraphicsGroup {
    public void draw() {
        // get the star shape, centered at (0,0)
        List<Point> starPoints = getStarPoints();
        // translate the shape to be correctly centered)
        translateStarPoints(starPoints, 100, 100);

        Polygon poly = new Polygon(starPoints);
        poly.setFillColor(Color.YELLOW);
        poly.setFilled(true);
        add(poly);
    }

    private void translateStarPoints(List<Point> points, double dx, double dy) {
        for(int i = 1; i<points.size(); i++) {
            Point p = points.get(i);
            // Needs rework for new Point class
//            p.setLocation(p.getX()+dx, p.getY()+dy);
        }
    }

    // note - these points are all 100% correct. It would be too simple (and
    // hard to fix) for me to make an error in these points. To be clear, there
    // is no bug in this method.
    public List<Point> getStarPoints() {
        List<Point> points = new LinkedList<>();
        points.add(new Point(58.78,   80.9));
        points.add(new Point(0.0,     40.0));
        points.add(new Point(-58.78,  80.9));
        points.add(new Point(-38.04,  12.36));
        points.add(new Point(-95.11, -30.9));
        points.add(new Point(-23.51, -32.36));
        points.add(new Point(0.0  ,  -100.0));
        points.add(new Point(23.51,  -32.36));
        points.add(new Point(95.11,  -30.9));
        points.add(new Point(38.04,   12.36));
        return points;
    }

    public static void main(String[] args) {
        CanvasWindow window = new CanvasWindow("star", 200, 200);
        Star group = new Star();
        group.draw();
        window.add(group);
    }
}
