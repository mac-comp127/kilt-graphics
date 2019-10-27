package comp127graphics;

import java.awt.geom.Point2D;
import java.util.Objects;

/**
 * A point in two-dimensional space.
 * <p>
 * This class is immutable. All methods return a new Point.
 *
 * @author Paul Cantrell
 */
public final class Point {
    /**
     * The point (0,0).
     */
    public static final Point ORIGIN = new Point(0, 0);

    /**
     * The point (1,0).
     */
    public static final Point UNIT_X = new Point(1, 0);

    /**
     * The point (0,1).
     */
    public static final Point UNIT_Y = new Point(0, 1);

    /**
     * A point at (-∞,-∞).
     */
    public static final Point MIN = new Point(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);

    /**
     * A point at (∞,∞).
     */
    public static final Point MAX = new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);


    private final double x, y;

    /**
     * Creates a point with the given horizontal and vertical components.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns the unit vector at the given angle in radians.
     */
    public static Point atAngle(double theta) {
        return new Point(Math.cos(theta), Math.sin(theta));
    }

    /**
     * The vertical position of this point.
     */
    public double getX() {
        return x;
    }

    /**
     * The horizontal position of this point.
     */
    public double getY() {
        return y;
    }

    /**
     * Returns a point with the same y as this one, but a different x.
     */
    public Point withX(double x) {
        return new Point(x, y);
    }

    /**
     * Returns a point with the same x as this one, but a different y.
     */
    public Point withY(double y) {
        return new Point(x, y);
    }

    /**
     * Returns the angle in radians counterclockwise (or clockwise if you view y as up) from the
     * x-axis to this point.
     */
    public double angle() {
        return Math.atan2(y, x);
    }

    /**
     * Returns the distance from this point to the origin.
     */
    public double magnitude() {
        return Math.hypot(x, y);
    }

    /**
     * Returns the distance between this point and another point.
     */
    public double distance(Point p) {
        return Math.hypot(p.x - x, p.y - y);
    }

    /**
     * Treats the given point like a displacement vector and adds it to the current point.
     */
    public Point add(Point p) {
        return new Point(x + p.x, y + p.y);
    }

    /**
     * Treats p like a displacement vector and subtracts it from the current point
     */
    public Point subtract(Point p) {
        return new Point(x - p.x, y - p.y);
    }

    /**
     * Multiplies this point's coordinates by a scalar.
     */
    public Point scale(double s) {
        return scale(s, s);
    }

    /**
     * Multiplies x by sx and y by sy.
     */
    public Point scale(double sx, double sy) {
        return new Point(x * sx, y * sy);
    }

    /**
     * Returns a point whose x is the minimum of the two given points' x coordinates, and whose y is
     * the minimum of the two given points' y coordinates.
     */
    public static Point min(Point p0, Point p1) {
        return new Point(
                Math.min(p0.x, p1.x),
                Math.min(p0.y, p1.y));
    }

    /**
     * Returns a point whose x is the maximum of the two given points' x coordinates, and whose y is
     * the maximum of the two given points' y coordinates.
     */
    public static Point max(Point p0, Point p1) {
        return new Point(
                Math.max(p0.x, p1.x),
                Math.max(p0.y, p1.y));
    }

    /**
     * Returns the point rotated around the origin by the given angle in radians.
     */
    public Point rotate(double angle) {
        return new Point(
                getX() * Math.cos(angle) - getY() * Math.sin(angle),
                getX() * Math.sin(angle) + getY() * Math.cos(angle));
    }

    /**
     * Returns the point rotated around a given axis point by the given angle in radians.
     */
    public Point rotate(double angle, Point center) {
        return subtract(center)
                .rotate(angle)
                .add(center);
    }

    /**
     * Linearly interpolates by alpha between the two points.
     *
     * @param p0    The point for alpha = 0
     * @param p1    The point for alpha = 1
     * @param alpha The continuum from p0 to p1
     */
    public static Point interpolate(Point p0, Point p1, double alpha) {
        return p0.add(
                p1.subtract(p0).scale(alpha));
    }

    /**
     * Two points are equal if they have the same coordinates.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Point that = (Point) o;
        return Double.compare(this.x, that.x) == 0
                && Double.compare(this.y, that.y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    // ------ Java AWT Compatibility

    /**
     * Converts a Java AWT Point2D to a comp127graphics Point.
     */
    public Point(Point2D point) {
        this(point.getX(), point.getY());
    }

    /**
     * Converts this point to the Java AWT Point2D.
     */
    public Point2D.Double toPoint2D() {
        return new Point2D.Double(x, y);
    }
}
