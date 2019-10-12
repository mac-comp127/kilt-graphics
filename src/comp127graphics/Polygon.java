package comp127graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.List;


/**
 * An arbitrary polygon, possibly self-intersecting.
 * <p>
 * The polygon is internally represented as a list of points, with an edge of the
 * polygon connecting each adjacent pair of points in the list, and an extra edge
 * connecting the first and last point in the list. As such the order of the list
 * is important. Incorrectly ordering the list can lead to self-intersecting
 * polygon, which are neat, but possibly not what you want.
 *
 * @author Daniel Kluver
 */
public class Polygon extends GraphicsObject implements Strokable, Fillable {

    private GeneralPath shape;
    private Paint fillColor;
    private Paint strokeColor;
    private boolean isFilled;
    private boolean isStroked;
    private BasicStroke stroke;

    private double x;
    private double y;
    private double width;
    private double height;
    private int vertexCount;

    /**
     * Convenience method to create a triangle from three individual coordinates.
     */
    public static Polygon makeTriangle(double x0, double y0, double x1, double y1, double x2, double y2) {
        return new Polygon(
                new Point(x0, y0),
                new Point(x1, y1),
                new Point(x2, y2));
    }

    /**
     * Create a polygon whose vertices comes from the given list of points.
     * The list of points must not be null, and there must be at least 3 points.
     * You cannot make a polygon with fewer than 3 points.
     */
    public Polygon(List<Point> points) {
        if (points == null) {
            throw new NullPointerException("list of points must be non-null");
        }
        if (points.size() < 3) {
            throw new IllegalArgumentException("Not enough points to make a polygon: " + points.size() + " < 3");
        }

        shape = new GeneralPath(GeneralPath.WIND_EVEN_ODD, points.size());

        shape.moveTo(points.get(0).getX(), points.get(0).getY());
        for (Point point : points.subList(1, points.size())) {
            shape.lineTo(point.getX(), point.getY());
        }
        shape.closePath();

        Rectangle shapeBounds = shape.getBounds();
        x = shapeBounds.getX();
        y = shapeBounds.getY();
        width = shapeBounds.getWidth();
        height = shapeBounds.getHeight();
        vertexCount = points.size();

        fillColor = Color.black;
        strokeColor = Color.black;
        stroke = new BasicStroke(1.0f);
        isFilled = false;
        isStroked = true;
    }

    /**
     * Creates a polygon from vertices passed as separate arguments.
     */
    public Polygon(Point... points) {
        this(List.of(points));
    }

    protected void draw(Graphics2D gc) {
        Paint originalColor = gc.getPaint();
        if (isFilled) {
            gc.setPaint(fillColor);
            gc.fill(shape);
        }
        if (isStroked) {
            gc.setStroke(stroke);
            gc.setPaint(strokeColor);
            gc.draw(shape);
        }
        gc.setPaint(originalColor); // set the color back to the original
    }

    @Override
    public Paint getFillColor() {
        return fillColor;
    }

    @Override
    public void setFillColor(Paint fillColor) {
        this.fillColor = fillColor;
        setFilled(true);
        changed();
    }

    @Override
    public Paint getStrokeColor() {
        return strokeColor;
    }

    @Override
    public void setStrokeColor(Paint strokeColor) {
        this.strokeColor = strokeColor;
        setStroked(true);
    }

    public boolean isFilled() {
        return isFilled;
    }

    public void setFilled(boolean filled) {
        isFilled = filled;
        changed();
    }

    public boolean isStroked() {
        return isStroked;
    }

    public void setStroked(boolean stroked) {
        isStroked = stroked;
        changed();
    }

    public double getStrokeWidth() {
        return stroke.getLineWidth();
    }

    public void setStrokeWidth(double width) {
        stroke = new BasicStroke((float) width);
        changed();
    }

    /**
     * Returns the leftmost position of the polygon's vertices.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the topmost position of the polygon's vertices.
     */
    public double getY() {
        return y;
    }

    /**
     * Returns the width of the shape, measured as the x distance from the leftmost
     * point to the rightmost point.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns the width of the shape, measured as the x distance from the topmost
     * point to the bottommost point.
     */
    public double getHeight() {
        return height;
    }

    public void setPosition(double x, double y) {
        double dx = x - getX();
        double dy = y - getY();
        shape.transform(AffineTransform.getTranslateInstance(dx, dy));
        this.x = x;
        this.y = y;
        changed();
    }

    public Point getPosition() {
        return new Point(getX(), getY());
    }

    /**
     * Tests whether the given point is on the boundary or interior of this polygon. Does not account for stroke width.
     */
    public boolean testHit(double x, double y) {
        return shape.contains(x, y);
    }

    @Override
    public Rectangle2D getBounds() {
        return shape.getBounds2D();
    }

    /**
     * Tests whether the given object is a Polygon with the same shape.
     * Ignores appearance, i.e. color and stroke width.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Polygon) {
            Polygon otherShape = (Polygon) other;
            return this.shape.equals(otherShape.shape);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return shape.hashCode();
    }

    /**
     * Returns a string representation of the Polygon
     */
    @Override
    public String toString() {
        return "Polygon with " + vertexCount + " points at position (" + getX() + ", " + getY() + ") with width=" + getWidth() + " and height=" + getHeight();
    }
}
