package comp127graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;

/**
 * A rectangle that can be drawn on the screen.
 *
 * @author Bret Jackson
 */
public class Rectangle extends GraphicsObject implements Strokable, Fillable {

    private Rectangle2D.Double shape;
    private Paint fillColor;
    private Paint strokeColor;
    private boolean isFilled;
    private boolean isStroked;
    private BasicStroke stroke;

    /**
     * Creates a rectangle whose upper left is at (x,y) with the specified width and height.
     * The rectangle has a 1 pixel black stroke outline by default.
     */
    public Rectangle(double x, double y, double width, double height) {
        shape = new Rectangle2D.Double(x, y, width, height);
        fillColor = Color.black;
        strokeColor = Color.black;
        stroke = new BasicStroke(1.0f);
        isFilled = false;
        isStroked = true;
    }

    /**
     * Creates a rectangle with the given upper left corner and size.
     * The rectangle has a 1 pixel black stroke outline by default.
     */
    public Rectangle(Point upperLeft, Point size) {
        this(upperLeft.getX(), upperLeft.getY(), size.getX(), size.getY());
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
        setStroked(true);
    }

    /**
     * Returns the X position of the rectangle's left edge.
     */
    public double getX() {
        return shape.getX();
    }

    /**
     * Returns the Y position of the rectangle's top edge.
     */
    public double getY() {
        return shape.getY();
    }

    /**
     * Returns the width of the rectangle.
     */
    public double getWidth() {
        return shape.getWidth();
    }

    /**
     * Returns the height of the rectangle.
     */
    public double getHeight() {
        return shape.getHeight();
    }

    /**
     * Returns the position of the rectangle's upper left corner.
     */
    public Point getPosition() {
        return new Point(shape.getX(), shape.getY());
    }

    /**
     * Moves this rectangle's upper left corner to (x, y), preserving its size.
     */
    public void setPosition(double x, double y) {
        shape.setFrame(x, y, shape.getWidth(), shape.getHeight());
        changed();
    }

    /**
     * Changes the width and height of the rectangle, preserving its upper left corner's position.
     */
    public void setSize(double width, double height) {
        shape.setFrame(shape.getX(), shape.getY(), width, height);
        changed();
    }

    /**
     * Changes the width and height of the rectangle, preserving its upper left corner's position.
     */
    public void setSize(Point size) {
        setSize(size.getX(), size.getY());
    }

    public boolean testHit(double x, double y) {
        return shape.contains(x, y);
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Tests whether the given object is a Rectangle with the same position and size.
     * Ignores appearance, i.e. color and stroke width.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Rectangle)) {
            return false;
        }
        Rectangle otherShape = (Rectangle) other;
        return shape.equals(otherShape.shape);
    }

    @Override
    public int hashCode() {
        return shape.hashCode();
    }

    /**
     * Returns a string representation of the rectangle
     */
    @Override
    public String toString() {
        return "Rectangle at position (" + getX() + ", " + getY() + ") with width=" + getWidth() + " and height=" + getHeight();
    }
}
