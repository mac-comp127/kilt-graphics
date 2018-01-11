package comp124graphics;

import java.awt.geom.Point2D;
import java.util.Arrays;


/**
 * Used to draw a triangle on the CanvasWindow or GraphicsGroup.
 *
 * This is simply a subclass of polygon with helpful constructors to make
 * polygons when you have exactly 3 points to draw.
 *
 * Created by Kluver on 11/9/17.
 */
public class Triangle extends Polygon {

    /**
     * create a triangle from the x and y locations of the three corners of the
     * triangle (a, b, and c)
     */
    public Triangle(double ax, double ay, double bx, double by, double cx, double cy) {
        this(new Point2D.Double(ax,ay), new Point2D.Double(bx,by), new Point2D.Double(cx,cy));
    }

    /**
     *
     * create a triangle from Point2D.Double objects representing the locations
     * of the three corners of the triangle (a, b, and c)
     */
    public Triangle(Point2D.Double a, Point2D.Double b, Point2D.Double c) {
        super(Arrays.asList(a,b,c));
    }

    // Note, this inherits the polygon equals method.
    // This means that triangles will be considered equal to polygons that have
    // exactly 3 points which are equal to this triangle.

    /**
     * Returns a string representation of the triangle.
     */
    @Override
    public String toString(){
        return "A triangle at position ("+getX()+", "+getY()+") with width="+getWidth()+" and height="+getHeight();
    }

}
