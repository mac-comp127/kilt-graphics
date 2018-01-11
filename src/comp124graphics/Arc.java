package comp124graphics;

import sun.java2d.SunGraphics2D;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;
import java.awt.geom.Point2D;


/**
 * Used to draw an arc on the CanvasWindow or GraphicsGroup.
 *
 * Created by shoop on 2/9/17.
 */
public class Arc extends GraphicsObject implements Colorable{

    private Arc2D.Double shape;
    private Paint strokeColor;
    private BasicStroke stroke;

    private double x; // upper left x position
    private double y; // upper left y position
    private double width;
    private double height;
    private double startAngle;
    private double sweepAngle;
    private int type;

    // from the Arc@D.Double documentation:
    //  The arc is a partial section of a full ellipse which inscribes the
    // framing rectangle of its parent RectangularShape. The angles are specified
    // relative to the non-square framing rectangle such that 45 degrees always
    // falls on the line from the center of the ellipse to the upper right corner
    // of the framing rectangle. As a result, if the framing rectangle is noticeably
    // longer along one axis than the other, the angles to the start and end of the
    // arc segment will be skewed farther along the longer axis of the frame.



    // g.drawArc( x-coordinate of top left corner , y-coordinate of top left corner ,
    //            width , height , start angle , angle swept out );
    //   0 start angle is positive x axis

    // TODO: need to likely add closed arcs and ability to fill them

    /**
     * This is an arc, based on Java's Arc2D.Double, which is described as follows:
     *
     *  The arc is a partial section of a full ellipse which inscribes the
     *  framing rectangle of its parent RectangularShape. The angles are specified
     *  relative to the non-square framing rectangle such that 45 degrees always
     *  falls on the line from the center of the ellipse to the upper right corner
     *  of the framing rectangle. As a result, if the framing rectangle is noticeably
     *  longer along one axis than the other, the angles to the start and end of the
     *  arc segment will be skewed farther along the longer axis of the frame.
     *  Note that 0 degrees is the positive x axis along the center of the ellipse,
     *  making the 90 degree point on a line from the center of the ellipse to the
     *  top center point of the upper arc of the ellipse.
     *
     * @param x
     * @param y
     * @param width
     * @param height
     * @param startAngle
     * @param sweepAngle  angle to sweep around from the starting angle, e.g. 180 would
     *                    create a 1/2 ellipse
     */
    public Arc(double x, double y, double width, double height, double startAngle, double sweepAngle) {
        shape = new Arc2D.Double(x, y, width, height, startAngle, sweepAngle, Arc2D.OPEN);
        strokeColor = Color.black;
        stroke = new BasicStroke(1.0f);

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.startAngle = startAngle;
        this.sweepAngle = sweepAngle;
        this.type = Arc2D.OPEN;
    }

    /**
     * Draws the shape on the screen
     * @param gc
     */
    @Override
    public void draw(Graphics2D gc){
        //Color originalColor = gc.getColor();
        gc.setStroke(stroke);
        //gc.setColor(strokeColor);
        gc.setPaint(strokeColor);
        gc.draw(shape);
        //gc.setColor(originalColor); // set the color back to the original
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

    @Override
    public void setPosition(double x, double y) {
        shape.setArc(x, y, width, height, startAngle, sweepAngle, type);
        this.x = x;
        this.y = y;
        changed();
    }


    @Override
    public void move(double dx, double dy) {
        shape.setArc(x + dx, y + dy, width, height, startAngle, sweepAngle, type);
        this.x = x + dx;
        this.y = y + dy;
        changed();
    }

    @Override
    public Point.Double getPosition() {
        return new Point.Double(shape.getX(), shape.getY());
    }

    @Override
    public boolean testHit(double x, double y, Graphics2D gc) {
        int devScale = ((SunGraphics2D)gc).getSurfaceData().getDefaultScale();
        AffineTransform transform = new AffineTransform();
        transform.setToScale(devScale, devScale);
        Point.Double point = new Point2D.Double(x, y);
        Point.Double transformedPoint = new Point2D.Double(x, y);
        transform.transform(point, transformedPoint);
        java.awt.Rectangle test = new java.awt.Rectangle((int)Math.round(transformedPoint.getX()),
                (int)Math.round(transformedPoint.getY()), 1*devScale,1*devScale);
        if (gc.hit(test, shape, false || gc.hit(test, shape, true))){
            return true;
        }
        return false;

    }

    @Override
    public Rectangle getBounds() {
       return shape.getBounds();
    }
}
