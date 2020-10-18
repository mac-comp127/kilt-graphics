package edu.macalester.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * A line segment that can be drawn on the screen.
 * <p>
 * A line has a "start point" and an "end point". The only distinction is that the start point is
 * the line’s {@link getPosition() position}. If you call {@link setPosition(Point) setPosition()}
 * or {@link moveBy(Point) moveBy()}, both endpoints will move so the line segment has the same
 * length and angle, and the start point will end up at the position you specify.
 * <p>
 * To move one endpoint without affecting the other, use {@link setStartPosition(Point) setStartPosition()}
 * and {@link setEndPosition(Point) setEndPosition()}.
 *
 * @author Bret Jackson
 */
public class Line extends GraphicsObject implements Strokable {

    private Line2D.Double shape;
    private Paint strokeColor;
    private BasicStroke stroke;
    private boolean isStroked = true;

    /**
     * Creates a line starting at position (x1,y1) and ending at (x2,y2).
     * The line has a 1 pixel black stroke outline by default.
     *
     * @param x1 x position of starting point
     * @param y1 y position of starting point
     * @param x2 x position of ending point
     * @param y2 y position of ending point
     */
    public Line(double x1, double y1, double x2, double y2) {
        shape = new Line2D.Double(0, 0, x2 - x1, y2 - y1);
        setPosition(x1, y1);
        strokeColor = Color.black;
        stroke = new BasicStroke(1.0f);
    }

    @Override
    protected void drawInLocalCoordinates(Graphics2D gc) {
        if (isStroked) {
            Paint originalColor = gc.getPaint();
            gc.setStroke(stroke);
            gc.setPaint(strokeColor);
            gc.draw(shape);
            gc.setPaint(originalColor); // set the color back to the original
        }
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

    public double getStrokeWidth() {
        return stroke.getLineWidth();
    }

    public void setStrokeWidth(double width) {
        stroke = new BasicStroke((float) width);
        setStroked(true);
    }

    @Override
    public boolean isStroked() {
        return isStroked;
    }

    @Override
    public void setStroked(boolean stroked) {
        this.isStroked = stroked;
        changed();
    }

    /**
     * Get the shape's x1 position
     *
     * @return x position of starting point
     */
    public double getX1() {
        return shape.getX1() + getX();
    }

    /**
     * Get the shape's y1 position
     *
     * @return y position of starting point
     */
    public double getY1() {
        return shape.getY1() + getY();
    }

    /**
     * Get the shape's x2 position
     *
     * @return x position of ending point
     */
    public double getX2() {
        return shape.getX2() + getX();
    }

    /**
     * Get the shape's y2 position
     *
     * @return y position of ending point
     */
    public double getY2() {
        return shape.getY2() + getY();
    }

    /**
     * Sets the line's starting position to (x, y) without affecting the end position.
     */
    public void setStartPosition(double x, double y) {
        shape.setLine(0, 0, shape.getX2() + getX() - x, shape.getY2() + getY() - y);
        setPosition(x, y);
        changed();
    }

    /**
     * Sets the line's starting position to the given point without affecting the end position.
     */
    public void setStartPosition(Point p) {
        setStartPosition(p.getX(), p.getY());
    }

    /**
     * Sets the line's ending position to (x, y) without affecting the start position.
     */
    public void setEndPosition(double x, double y) {
        shape.setLine(0, 0, x - getX(), y - getY());
        changed();
    }

    /**
     * Sets the line's ending position to the given point without affecting the end position.
     */
    public void setEndPosition(Point p) {
        setEndPosition(p.getX(), p.getY());
    }

    @Override
    public boolean testHitInLocalCoordinates(double x, double y) {
        return shape.contains(x, y);
    }

    @Override
    protected Rectangle2D getBoundsLocal() {
        double left = Math.min(getX1(), getX2());
        double top = Math.min(getY1(), getY2());
        return new Rectangle2D.Double(left - getX(), top - getY(), Math.abs(getX2() - getX1()), Math.abs(getY1() - getY2()));
    }

    /**
     * Two lines are identical if the have the same start points and the same endpoints, regardless
     * of appearance.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Line)) {
            return false;
        }
        Line otherShape = (Line) other;
        return shape.equals(otherShape.shape);
    }

    @Override
    public int hashCode() {
        return shape.hashCode();
    }

    @Override
    public String toString() {
        return "A line at position (" + getX1() + ", " + getY1() + ") and (" + getX2() + ", " + getY2() + ")";
    }
}
