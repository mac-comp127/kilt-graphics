package comp127graphics;

import java.util.Arrays;
import java.util.List;
import java.awt.*;
import java.util.stream.Stream;

import static java.lang.Math.*;


/**
 * An arbitrary polygon, possibly self-intersecting.
 *
 * The polygon is internally represented as a list of points, with an edge of the
 * polygon connecting each adjacent pair of points in the list, and an extra edge
 * connecting the first and last point in the list. As such the order of the list
 * is important. Incorrectly ordering the list can lead to self-intersecting
 * polygon, which are neat, but possibly not what you want.
 *
 * @author Daniel Kluver
 */
public class Polygon extends GraphicsObject implements Strokable, Fillable {

    private java.awt.Polygon shape;
    private Paint fillColor;
    private Paint strokeColor;
    private boolean isFilled;
    private boolean isStroked;
    private BasicStroke stroke;

    private double x;
    private double y;
    private double width;
    private double height;

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

        int[] xPoints = points.stream().mapToInt((p) -> (int) Math.round(p.getX())).toArray();
        int[] yPoints = points.stream().mapToInt((p) -> (int) Math.round(p.getY())).toArray();

        var xStats = Arrays.stream(xPoints).summaryStatistics();
        var yStats = Arrays.stream(yPoints).summaryStatistics();
        x = xStats.getMin();
        y = yStats.getMin();
        width = xStats.getMax() - x;
        height = yStats.getMax() - y;

        shape = new java.awt.Polygon(xPoints, yPoints, points.size());
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

    public void draw(Graphics2D gc){
        Paint originalColor = gc.getPaint();
        if (isFilled){
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

    public float getStrokeWidth(){
        return stroke.getLineWidth();
    }

    public void setStrokeWidth(float width){
        stroke = new BasicStroke(width);
        changed();
    }

    /**
     * Returns the leftmost position of the polygon's vertices.
     */
    public double getX(){
        return x;
    }

    /**
     * Returns the topmost position of the polygon's vertices.
     */
    public double getY(){
        return y;
    }

    /**
     * Returns the width of the shape, measured as the x distance from the leftmost
     * point to the rightmost point.
     */
    public double getWidth(){
        return width;
    }

    /**
     * Returns the width of the shape, measured as the x distance from the topmost
     * point to the bottommost point.
     */
    public double getHeight(){
        return height;
    }

    public void setPosition(double x, double y){
        double dx = x - getX();
        double dy = y - getY();
        shape.translate((int)Math.round(dx), (int)Math.round(dy)); // TODO: int conversion here will cause x/y to drift from shape
        this.x = x;
        this.y = y;
        changed();
    }

    public Point getPosition(){
        return new Point(getX(), getY());
    }

    /**
     * Tests whether the given point is on the boundary or interior of this polygon. Does not account for stroke width.
     */
    public boolean testHit(double x, double y){
        return shape.contains(x, y);
    }

    /**
     * Tests whether the given object is a Polygon with the same shape.
     * Ignores appearance, i.e. color and stroke width.
     */
    @Override
    public boolean equals(Object other){
        if (other instanceof Polygon){
            Polygon otherShape = (Polygon) other;
            return this.shape.equals(otherShape.shape);
        }
        return false;
    }

    /**
     * Returns a string representation of the Polygon
     */
    @Override
    public String toString(){
        return "Polygon with "+shape.npoints+" points at position ("+getX()+", "+getY()+") with width="+getWidth()+" and height="+getHeight();
    }

    public java.awt.Rectangle getBounds(){
        return shape.getBounds();
    }
}
