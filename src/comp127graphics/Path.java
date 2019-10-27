package comp127graphics;

import java.awt.Rectangle;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.List;
import java.util.Objects;

import static java.lang.Float.NaN;


/**
 * An arbitrary path or polygon with straight sides, possibly self-intersecting.
 * <p>
 * The path is internally represented as a list of points, with an edge of the
 * path connecting each adjacent pair of points in the list. The order of the list
 * is thus important. Incorrectly ordering the list can lead to self-intersecting
 * polygon, which are neat, but possibly not what you want.
 *
 * An optional final edge connects the first and last point in the list. You can
 * disable this by passing `false` to the second argument of setPoints().
 *
 * @author Daniel Kluver
 */
public class Path extends GraphicsObject implements Strokable, Fillable {

    private Path2D.Float shape;
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
    public static Path makeTriangle(double x0, double y0, double x1, double y1, double x2, double y2) {
        return new Path(
                new Point(x0, y0),
                new Point(x1, y1),
                new Point(x2, y2));
    }

    /**
     * Create a closed polygon whose vertices comes from the given list of points.
     * The list of points must not non-null.
     */
    public Path(List<Point> points) {
        this(points, true);
    }

    /**
     * Creates a closed polygon from vertices passed as separate arguments.
     */
    public Path(Point... points) {
        this(List.of(points));
    }

    /**
     * Create an optionally closed path whose vertices comes from the given list of points.
     * The list of points must not non-null.
     *
     * @param closed If true, a final line connects the end of the path back to the start, forming
     *  a polygon.
     */
    public Path(List<Point> points, boolean closed) {
        setVertices(points, closed);

        fillColor = Color.BLACK;
        setFilled(false);

        setStrokeColor(Color.BLACK);
        setStrokeWidth(1.0);
    }

    /**
     * Changes the verices of this path to a closed polygon, replacing any existing ones. The
     * coordinates are relative to the path’s container; the method ignores (and then changes)
     * the path’s current position.
     */
    public void setVertices(List<Point> points) {
        setVertices(points, true);
    }

    /**
     * Changes the verices of this path, replacing any existing ones. The coordinates are
     * relative to the path’s container; the method ignores (and then changes) the path’s
     * current position.
     *
     * @param closed If true, a final line connects the end of the path back to the start, forming
     *  a polygon.
     */
    public void setVertices(List<Point> points, boolean closed) {
        Objects.requireNonNull(points, "points");

        shape = new Path2D.Float(GeneralPath.WIND_EVEN_ODD, points.size());

        if(points.isEmpty()) {
            shape.moveTo(NaN, NaN);
        } else {
            shape.moveTo(points.get(0).getX(), points.get(0).getY());
            for (Point point : points.subList(1, points.size())) {
                shape.lineTo(point.getX(), point.getY());
            }
            if (closed) {
                shape.closePath();
            }
        }

        Rectangle shapeBounds = shape.getBounds();
        x = shapeBounds.getX();
        y = shapeBounds.getY();
        width = shapeBounds.getWidth();
        height = shapeBounds.getHeight();

        vertexCount = points.size();

        changed();
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
        stroke = new BasicStroke((float) width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
        setStroked(true);
    }

    /**
     * Returns the leftmost position of the path's vertices.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the topmost position of the path's vertices.
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
     * Tests whether the given point is on the interior of this path. Does not account for stroke width.
     */
    public boolean testHit(double x, double y) {
        return shape.contains(x, y);
    }

    @Override
    public Rectangle2D getBounds() {
        return shape.getBounds2D();
    }

    /**
     * Tests whether the given object is a Path with the same shape.
     * Ignores appearance, i.e. color and stroke width.
     */
    @Override
    public boolean equals(Object other) {
        if (other instanceof Path) {
            Path otherShape = (Path) other;
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
