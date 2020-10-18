package edu.macalester.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Arc2D;
import java.awt.geom.Rectangle2D;


/**
 * A partial section of an ellipse.
 *
 * @author Libby Shoop
 */
public class Arc extends GraphicsObject implements Strokable {

    private Arc2D.Double shape;
    private Paint strokeColor;
    private BasicStroke stroke;
    private boolean isStroked = true;

    // TODO: need to likely add closed arcs and ability to fill them

    /**
     * This is an arc, based on Java's Arc2D.Double, which Java describes as follows:
     * <blockquote>
     * The arc is a partial section of a full ellipse which inscribes the
     * framing rectangle of its parent RectangularShape. The angles are specified
     * relative to the non-square framing rectangle such that 45 degrees always
     * falls on the line from the center of the ellipse to the upper right corner
     * of the framing rectangle. As a result, if the framing rectangle is noticeably
     * longer along one axis than the other, the angles to the start and end of the
     * arc segment will be skewed farther along the longer axis of the frame.
     * Note that 0 degrees is the positive x axis along the center of the ellipse,
     * making the 90 degree point on a line from the center of the ellipse to the
     * top center point of the upper arc of the ellipse.
     * </blockquote>
     *
     * @param sweepAngle angle to sweep around from the starting angle, e.g. 180 would
     *                   create a 1/2 ellipse
     */
    public Arc(double x, double y, double width, double height, double startAngle, double sweepAngle) {
        shape = new Arc2D.Double(x, y, width, height, startAngle, sweepAngle, Arc2D.OPEN);
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

    @Override
    public boolean testHitInLocalCoordinates(double x, double y) {
        return shape.contains(x, y);
    }

    @Override
    public Rectangle2D getBounds() {
        return shape.getBounds2D();
    }
}
