package comp127graphics;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Represents a 2D rectangle that is drawn on the screen.
 * Created by bjackson on 9/13/2016.
 * @version 0.5
 */
public class Rectangle extends GraphicsObject implements Strokable, Fillable {

    private Rectangle2D.Double shape;
    private Paint fillColor;
    private Paint strokeColor;
    private boolean isFilled;
    private boolean isStroked;
    private BasicStroke stroke;

    /**
     * Constructor to create the rectangle object and initialize its instance variables.
     * The default creates a rectangle at position x,y with the specified width and height.
     * The rectangle is drawn with a 1 pixel black stroke outline by default.
     * @param x position
     * @param y position
     * @param width
     * @param height
     */
    public Rectangle(double x, double y, double width, double height){
        shape = new Rectangle2D.Double(x, y, width, height);
        fillColor = Color.black;
        strokeColor = Color.black;
        stroke = new BasicStroke(1.0f);
        isFilled = false;
        isStroked = true;
    }

    /**
     * Draws the rectangle on the screen
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
     * Get the width of the rectangle
     * @return rectangle width
     */
    public double getWidth(){
        return shape.getWidth();
    }

    /**
     * Get the height of the rectangle
     * @return rectangle height
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

    public Point getPosition(){
        return new Point(shape.getX(), shape.getY());
    }

    /**
     * Set the width and height of the rectangle
     * @param width
     * @param height
     */
    public void setWidthAndHeight(double width, double height){
        shape.setFrame(shape.getX(), shape.getY(), width, height);
        changed();
    }

    public boolean testHit(double x, double y){
        return shape.contains(x, y);
    }

    /**
     * Tests for equality between two rectangle objects.
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other){
        if (other != null && other instanceof Rectangle){
            Rectangle otherShape = (Rectangle)other;
            if (this.shape.equals(otherShape.shape)){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a string representation of the rectangle
     * @return
     */
    @Override
    public String toString(){
        return "A rectangle at position ("+getX()+", "+getY()+") with width="+getWidth()+" and height="+getHeight();
    }

    /**
     * Returns an axis aligned bounding rectangle for the graphical object.
     * @return
     */
    public java.awt.Rectangle getBounds(){
        return new java.awt.Rectangle((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
    }
}
