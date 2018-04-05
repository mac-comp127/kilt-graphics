package activityStarterCode.debugActivity;

import comp124graphics.CanvasWindow;
import comp124graphics.GraphicsGroup;
import comp124graphics.GraphicsText;
import comp124graphics.Polygon;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dkluver on 11/17/17.
 */
public class ShapeWall extends GraphicsGroup {
    private List<Point2D.Double> points;

    public void drawPoly(int nSides) {
        GraphicsText gt = new GraphicsText(Integer.toString(nSides),50,50);
        //center in 100 x 100 box for polygon.
        gt.move(-gt.getWidth()/2, gt.getHeight()/2);
        add(gt);

        List<Point2D.Double> points = generatePoints(nSides);
        Polygon poly = new Polygon(points);
        add(poly);
    }

    private List<Point2D.Double> generatePoints(int nSides) {
        for(int i = 0; i<nSides; i++) {
            List<Point2D.Double> points = new ArrayList<>(nSides);
            // compute the angle for a given point in degrees
            double theta = (360*i)/nSides;
            // use trigonometry to compute point locations.
            double x = 50+50* Math.sin(theta);
            double y = 50+50* Math.cos(theta);
            // add points to the list
            points.add(new Point2D.Double(x,y));
        }
        return points;
    }

    public static void main(String[] args) {
        // should draw a series of polygons from simple to complex (more edges)
        CanvasWindow window = new CanvasWindow("ShapeWall",100*8, 100);
        for(int i = 8; i>0; i--) {
            ShapeWall group = new ShapeWall();
            group.drawPoly(i);
            group.setPosition(100*i,0);
            window.add(group);
        }
    }

}
