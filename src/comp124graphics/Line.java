package comp124graphics;

import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Represents a line that can be drawn graphically in a canvaswindow
 * Created by bjackson on 9/14/2016.
 * @version 0.5
 */
public class Line extends GraphicsObject implements Strokable {

    private Line2D.Double shape;
    private Paint strokeColor;
    private BasicStroke stroke;
    private boolean isStroked = true;

    /**
     * Constructor to create the line object and initialize its instance variables.
     * The default creates a line starting at position x1,y1 and ending at x2, y2.
     * The line is drawn with a 1 pixel black stroke outline by default.
     * @param x1 x position of starting point
     * @param y1 y position of starting point
     * @param x2 x position of ending point
     * @param y2 y position of ending point
     */
    public Line(double x1, double y1, double x2, double y2){
        shape = new Line2D.Double(x1, y1, x2, y2);
        strokeColor = Color.black;
        stroke = new BasicStroke(1.0f);
    }

    /**
     * Draws the shape on the screen
     * @param gc
     */
    public void draw(Graphics2D gc){
        if(isStroked) {
            Paint originalColor = gc.getPaint();
            gc.setStroke(stroke);
            gc.setPaint(strokeColor);
            gc.draw(shape);
            gc.setPaint(originalColor); // set the color back to the original
        }
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
    public boolean isStroked() {
        return isStroked;
    }

    @Override
    public void setStroked(boolean stroked) {
        this.isStroked = stroked;
    }

    /**
     * Get the shape's x1 position
     * @return x position of starting point
     */
    public double getX1(){
        return shape.getX1();
    }

    /**
     * Get the shape's y1 position
     * @return y position of starting point
     */
    public double getY1(){
        return shape.getY1();
    }

    /**
     * Get the shape's x2 position
     * @return x position of ending point
     */
    public double getX2(){
        return shape.getX2();
    }

    /**
     * Get the shape's y2 position
     * @return y position of ending point
     */
    public double getY2(){
        return shape.getY2();
    }


    /**
     * Sets the shape's starting position to x, y
     * @param x
     * @param y
     */
    public void setStartPosition(double x, double y){
        shape.setLine(x, y, shape.getX2(), shape.getY2());
        changed();
    }

    /**
     * Sets the shape's ending position to x, y
     * @param x
     * @param y
     */
    public void setEndPosition(double x, double y){
        shape.setLine(shape.getX1(), shape.getY1(), x, y);
        changed();
    }

    /**
     * Sets the line to start at position x, y
     * @param x
     * @param y
     */
    public void setPosition(double x, double y){
        shape.setLine(x, y, (x-shape.getX1())+shape.getX2(), (y - shape.getY1())+shape.getY2());
        changed();
    }

    /**
     * Gets the position of the graphical object
     * @return position
     */
    public Point.Double getPosition(){
        return new Point.Double(shape.getX1(), shape.getY1());
    }


    /**
     * Move the shape from its current position by dx and dy.
     * @param dx
     * @param dy
     */
    public void move(double dx, double dy){
        shape.setLine(shape.getX1() + dx, shape.getY1() + dy, shape.getX2()+dx, shape.getY2()+dy);
        changed();
    }

    public boolean testHit(double x, double y){
        return shape.contains(x, y);
    }

    /**
     * Test for equality between line objects.
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other){
        if (other != null && other instanceof Line){
            Line otherShape = (Line)other;
            if (this.shape.equals(otherShape.shape)){
                return true;
            }
        }
        return false;
    }

    /**
     * String representation of the line
     * @return
     */
    @Override
    public String toString(){
        return "A line at position ("+getX1()+", "+getY1()+") and ("+getX2()+", "+getY2()+")";
    }

    /**
     * Returns an axis aligned bounding rectangle for the graphical object.
     * @return
     */
    public java.awt.Rectangle getBounds(){
        int left = (int)Math.min(getX1(), getX2());
        int top = (int)Math.min(getY1(), getY2());
        return new java.awt.Rectangle(left, top, (int)Math.abs(getX2()-getX1()), (int)Math.abs(getY1()-getY2()));
    }
}
