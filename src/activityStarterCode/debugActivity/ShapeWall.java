package activityStarterCode.debugActivity;

import comp127graphics.CanvasWindow;
import comp127graphics.GraphicsGroup;
import comp127graphics.GraphicsText;
import comp127graphics.Point;
import comp127graphics.Path;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dkluver on 11/17/17.
 */
public class ShapeWall extends GraphicsGroup {
    private List<Point> points;

    public void drawPoly(int nSides) {
        GraphicsText gt = new GraphicsText(Integer.toString(nSides),50,50);
        //center in 100 x 100 box for polygon.
        gt.moveBy(-gt.getWidth()/2, gt.getHeight()/2);
        add(gt);

        List<Point> points = generatePoints(nSides);
        Path poly = new Path(points);
        add(poly);
    }

    private List<Point> generatePoints(int nSides) {
        for(int i = 0; i<nSides; i++) {
            List<Point> points = new ArrayList<>(nSides);
            // compute the angle for a given point in degrees
            double theta = (360*i)/nSides;
            // use trigonometry to compute point locations.
            double x = 50+50* Math.sin(theta);
            double y = 50+50* Math.cos(theta);
            // add points to the list
            points.add(new Point(x,y));
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
