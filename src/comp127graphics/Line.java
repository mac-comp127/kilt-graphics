package comp127graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * A line segment that can be drawn on the screen.
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
        shape = new Line2D.Double(x1, y1, x2, y2);
        strokeColor = Color.black;
        stroke = new BasicStroke(1.0f);
    }

    protected void draw(Graphics2D gc) {
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
        return shape.getX1();
    }

    /**
     * Get the shape's y1 position
     *
     * @return y position of starting point
     */
    public double getY1() {
        return shape.getY1();
    }

    /**
     * Get the shape's x2 position
     *
     * @return x position of ending point
     */
    public double getX2() {
        return shape.getX2();
    }

    /**
     * Get the shape's y2 position
     *
     * @return y position of ending point
     */
    public double getY2() {
        return shape.getY2();
    }

    /**
     * Sets the line's starting position to (x, y) without affecting the end position.
     */
    public void setStartPosition(double x, double y) {
        shape.setLine(x, y, shape.getX2(), shape.getY2());
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
        shape.setLine(shape.getX1(), shape.getY1(), x, y);
        changed();
    }

    /**
     * Sets the line's ending position to the given point without affecting the end position.
     */
    public void setEndPosition(Point p) {
        setEndPosition(p.getX(), p.getY());
    }

    /**
     * Moves the line so that it starts at (x,y) and has the same length and direction.
     */
    public void setPosition(double x, double y) {
        shape.setLine(x, y, (x - shape.getX1()) + shape.getX2(), (y - shape.getY1()) + shape.getY2());
        changed();
    }

    public Point getPosition() {
        return new Point(shape.getX1(), shape.getY1());
    }

    public boolean testHit(double x, double y) {
        return shape.contains(x, y);
    }

    public Rectangle2D getBounds() {
        double left = Math.min(getX1(), getX2());
        double top = Math.min(getY1(), getY2());
        return new Rectangle2D.Double(left, top, Math.abs(getX2() - getX1()), Math.abs(getY1() - getY2()));
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
