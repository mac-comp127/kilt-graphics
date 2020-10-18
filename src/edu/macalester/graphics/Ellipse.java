package edu.macalester.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * An ellipse that can be drawn on the screen.
 * <p>
 * An ellipse’s {@link getPosition() position} is the upper left corner of its bounding box.
 *
 * @author Bret Jackson
 */
public class Ellipse extends GraphicsObject implements Strokable, Fillable {

    private Ellipse2D.Double shape;
    private Paint fillColor;
    private Paint strokeColor;
    private boolean isFilled;
    private boolean isStroked;
    private BasicStroke stroke;

    /**
     * Creates an ellipse whose upper left is at (x,y), and which has the specified width and height.
     * It has a 1 pixel black stroke outline by default.
     *
     * @param x      position
     * @param y      position
     * @param width  of the bounding rectangle
     * @param height of the bounding rectangle
     */
    public Ellipse(double x, double y, double width, double height) {
        shape = new Ellipse2D.Double(0, 0, width, height);
        setPosition(x, y);
        fillColor = Color.black;
        strokeColor = Color.black;
        stroke = new BasicStroke(1.0f);
        isFilled = false;
        isStroked = true;
    }

    @Override
    protected void drawInLocalCoordinates(Graphics2D gc) {
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
     * Changes the width and height of the ellipse, preserving its upper left corner's position.
     */
    public void setSize(double width, double height) {
        shape.setFrame(0, 0, width, height);
        changed();
    }

    /**
     * Changes the width and height of the ellipse, preserving the position of its upper left corner.
     */
    public void setSize(Point size) {
        setSize(size.getX(), size.getY());
    }

    @Override
    public boolean testHitInLocalCoordinates(double x, double y) {
        return shape.contains(x, y);
    }

    @Override
    public Rectangle2D getBounds() {
        return shape.getBounds2D();
    }

    /**
     * Two ellipses are equal if the have the same position and size, regardless of appearance.
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Ellipse)) {
            return false;
        }
        Ellipse otherShape = (Ellipse) other;
        return shape.equals(otherShape.shape);
    }

    @Override
    public int hashCode() {
        return shape.hashCode();
    }

    @Override
    public String toString() {
        return "Ellipse at position " + getPosition() + " with size=" + getSize();
    }
}
