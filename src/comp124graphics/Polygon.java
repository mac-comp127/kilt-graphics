package comp124graphics;

import java.util.List;
import java.awt.*;
import java.awt.geom.Point2D;

import static java.lang.Math.*;


/**
 * Used to draw an arbitrary polygon on a CanvasWindow or in a GraphicsGroup.
 *
 * The polygon is internally represented as a list of points, with an edge of the
 * polygon connecting each adjacent pair of points in the list, and an extra edge
 * connecting the first and last point in the list. As such the order of the list
 * is important. Incorrectly ordering the list can lead to self-intersecting
 * polygon, which are neat, but it is also hard to predict how they will get filled.
 *
 * Created by Kluver on 11/9/17.
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
     * Create a polygon from a given list of points.
     * The list of points must not be null, and there must be at least 3 points.
     * You cannot make a polygon with fewer than 3 points.
     *
     */
    public Polygon(List<Point2D.Double> points) {
        if (points == null) {
            throw new NullPointerException("list of points must be non-null");
        } else if (points.size()<3) {
            throw new IllegalArgumentException("Not enough points to make a polygon");
        }
        int[] xPoints = new int[points.size()];
        int[] yPoints = new int[points.size()];

        x = Double.POSITIVE_INFINITY;
        y = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;

        int i = 0;
        for(Point2D.Double point : points) {
            x = min(x, point.getX());
            y = min(y, point.getY());
            maxX = max(maxX, point.getX());
            maxY = max(maxY, point.getY());
            xPoints[i] = (int) Math.round(point.getX());
            yPoints[i] = (int) Math.round(point.getY());
            i++;
        }
        width = maxX - x;
        height = maxY - y;

        shape = new java.awt.Polygon(xPoints, yPoints, points.size());
        fillColor = Color.black;
        strokeColor = Color.black;
        stroke = new BasicStroke(1.0f);
        isFilled = false;
        isStroked = true;
    }

    /**
     * Draws the polygon on the screen
     */
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

    /**
     * Gets the color for the filled in polygon
     * @return fill color
     */
    @Override
    public Paint getFillColor() {
        return fillColor;
    }

    /**
     * Set the fill color to fillColor
     * @param fillColor Color to fill the polygon
     */
    @Override
    public void setFillColor(Paint fillColor) {
        this.fillColor = fillColor;
        setFilled(true);
        changed();
    }

    /**
     * Gets the stroke color used to draw the shape outline
     * @return stroke color
     */
    @Override
    public Paint getStrokeColor() {
        return strokeColor;
    }

    /**
     * Set the stroke outline color for the shape
     * @param strokeColor for outline
     */
    @Override
    public void setStrokeColor(Paint strokeColor) {
        this.strokeColor = strokeColor;
        setStroked(true);
    }

    /**
     * Gets whether the shape should be drawn filled.
     * @return true if the shape is filled
     */
    public boolean isFilled() {
        return isFilled;
    }

    /**
     * Set whether the shape should be drawn filled
     */
    public void setFilled(boolean filled) {
        isFilled = filled;
        changed();
    }

    /**
     * Get whether the outline stroke should be drawn
     * @return true if outline is drawn
     */
    public boolean isStroked() {
        return isStroked;
    }

    /**
     * Sets whether the outline stroke should be drawn
     */
    public void setStroked(boolean stroked) {
        isStroked = stroked;
        changed();
    }

    /**
     * Gets the width of the outline stroke
     * @return width of stroke outline
     */
    public float getStrokeWidth(){
        return stroke.getLineWidth();
    }

    /**
     * Sets the width of the stroke outline
     * @param width of outline
     */
    public void setStrokeWidth(float width){
        stroke = new BasicStroke(width);
        changed();
    }

    /**
     * Get the shape's x position
     * @return x position
     */
    public double getX(){
        return x;
    }

    /**
     * Get the shape's y position
     * @return y position
     */
    public double getY(){
        return y;
    }

    /**
     * Get the width of the shape, measured as the x distance from the leftmost
     * point to the rightmost point
     * @return shape width
     */
    public double getWidth(){
        return width;
    }

    /**
     * Get the height of the shape, measured as the y distance from the bottom
     * point to the top point.
     * @return shape height
     */
    public double getHeight(){
        return height;
    }

    /**
     * Sets the shape's position to x, y
     */
    public void setPosition(double x, double y){
        double dx = x-getX();
        double dy = y-getY();
        move(dx, dy);
    }

    public Point.Double getPosition(){
        return new Point2D.Double(getX(), getY());
    }

    /**
     * Move the shape from its current x, y position by dx and dy.
     */
    public void move(double dx, double dy){
        shape.translate((int)Math.round(dx), (int)Math.round(dy));
        x+=dx;
        y+=dy;
        changed();
    }

    /**
     * Tests whether the given point is on the boundary or interior of this polygon. Does not account for stroke width.
     */
    public boolean testHit(double x, double y){
        return shape.contains(x, y);
    }

    /**
     * Tests for equality between two Polygon objects.
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
        return "A polygon with "+shape.npoints+" points at position ("+getX()+", "+getY()+") with width="+getWidth()+" and height="+getHeight();
    }

    /**
     * Returns an axis aligned bounding rectangle for the graphical object.
     */
    public java.awt.Rectangle getBounds(){
        return shape.getBounds();
    }
}
