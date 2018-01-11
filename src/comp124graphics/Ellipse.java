package comp124graphics;

import sun.java2d.SunGraphics2D;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 * Used to draw an ellipse on the screen.
 * Created by bjackson on 9/13/2016.
 * @version 0.5
 */
public class Ellipse extends GraphicsObject implements Colorable, FillColorable{

    private Ellipse2D.Double shape;
    private Paint fillColor;
    private Paint strokeColor;
    private boolean isFilled;
    private boolean isStroked;
    private BasicStroke stroke;

    /**
     * Constructor to create the ellipse object and initialize its instance variables.
     * The default creates an ellipse at position x,y with a bounding rectangle of width and height.
     * The ellipse is drawn with a 1 pixel black stroke outline by default.
     * @param x position
     * @param y position
     * @param width of the bounding rectangle
     * @param height of the bounding rectangle
     */
    public Ellipse(double x, double y, double width, double height){
        shape = new Ellipse2D.Double(x, y, width, height);
        fillColor = Color.black;
        strokeColor = Color.black;
        stroke = new BasicStroke(1.0f);
        isFilled = false;
        isStroked = true;
    }

    /**
     * Draws the shape on the screen
     * @param gc
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
     * Gets the color for the filled in rectangle
     * @return fill color
     */
    @Override
    public Paint getFillColor() {
        return fillColor;
    }

    /**
     * Set the fill color to fillColor
     * @param fillColor Color to fill the rectangle
     */
    @Override
    public void setFillColor(Paint fillColor) {
        this.fillColor = fillColor;
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
        changed();
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
     * @param filled
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
     * @param stroked
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
        return shape.getX();
    }

    /**
     * Get the shape's y position
     * @return y position
     */
    public double getY(){
        return shape.getY();
    }

    /**
     * Get the width of the bounding rectangle of the ellipse
     * @return bounding rectangle width
     */
    public double getWidth(){
        return shape.getWidth();
    }

    /**
     * Get the height of the bounding rectangle of the ellipse
     * @return bounding rectangle height
     */
    public double getHeight(){
        return shape.getHeight();
    }

    /**
     * Sets the shape's position to x, y
     * @param x
     * @param y
     */
    public void setPosition(double x, double y){
        shape.setFrame(x, y, shape.getWidth(), shape.getHeight());
        changed();
    }

    /**
     * Gets the position of the graphical object
     * @return position
     */
    public Point.Double getPosition(){
        return new Point.Double(shape.getX(), shape.getY());
    }

    /**
     * Set the width and height of the ellipse's bounding rectangle
     * @param width
     * @param height
     */
    public void setWidthAndHeight(double width, double height){
        shape.setFrame(shape.getX(), shape.getY(), width, height);
        changed();
    }

    /**
     * Move the shape from its current x, y position by dx and dy.
     * @param dx
     * @param dy
     */
    public void move(double dx, double dy){
        shape.setFrame(shape.getX() + dx, shape.getY() + dy, shape.getWidth(), shape.getHeight());
        changed();
    }

    /**
     * Tests whether the point (x, y) hits the shape on the graphics window
     * @return true if this shape is the topmost object at point (x, y)
     */
    public boolean testHit(double x, double y, Graphics2D gc){
        int devScale = ((SunGraphics2D)gc).getSurfaceData().getDefaultScale();
        AffineTransform transform = new AffineTransform();
        transform.setToScale(devScale, devScale);
        Point.Double point = new Point2D.Double(x, y);
        Point.Double transformedPoint = new Point2D.Double(x, y);
        transform.transform(point, transformedPoint);
        java.awt.Rectangle test = new java.awt.Rectangle((int)Math.round(transformedPoint.getX()), (int)Math.round(transformedPoint.getY()), 1*devScale,1*devScale);
        boolean hit = false;
        if (isFilled && gc.hit(test, shape, false)){
            hit = true;
        }
        if(isStroked && gc.hit(test, shape, true)){
            hit = true;
        }
        return hit;
    }

    /**
     * Tests two ellipse objects for equality.
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other){
        if (other != null && other instanceof Ellipse){
            Ellipse otherShape = (Ellipse) other;
            if (this.shape.equals(otherShape.shape)){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a string representation of the Ellipse
     * @return
     */
    @Override
    public String toString(){
        return "An ellipse at position ("+getX()+", "+getY()+") with width="+getWidth()+" and height="+getHeight();
    }

    /**
     * Returns an axis aligned bounding rectangle for the graphical object.
     * @return
     */
    public java.awt.Rectangle getBounds(){
        return new java.awt.Rectangle((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
    }

}
