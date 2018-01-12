package comp124graphics;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * This is a graphical object that is used to group other graphical objects together.
 * It defines it's own coordinate system so that objects that the x,y position of objects added to it are relative
 * to its origin placed on the canvaswindow.
 * Created by bjackson on 9/15/2016.
 *
 * @version 0.5
 */
public class GraphicsGroup extends GraphicsObject implements GraphicsObserver {

    /**
     * Holds the objects to be drawn in calls to paintComponent
     */
    private ConcurrentLinkedDeque<GraphicsObject> gObjects;

    /**
     * X position of group in canvas space
     */
    private double x;

    /**
     * Y position of group in canvas space
     */
    private double y;

    /**
     * Bounding rectangle around all of the graphicObjects contained in this group in window coordinates.
     */
    private java.awt.Rectangle bounds;

    /**
     * Buffer to draw the sub graphics objects on
     */
    private BufferedImage imgBuffer;
    private Graphics2D subCanvas;

    /**
     * Constructs a new group. Each group has it's own local coordinate system. The group is then positioned on the canvas
     * at canvas position (x, y) when it is added.
     * @param x
     * @param y
     */
    public GraphicsGroup(double x, double y){
        this.x = x;
        this.y = y;
        gObjects = new ConcurrentLinkedDeque<GraphicsObject>();
        bounds = new java.awt.Rectangle(0,0,-1, -1);
    }

    /**
     * Constructs a new group. Each group has it's own local coordinate system. The group is defaulted
     * to be positioned on the canvas at canvas position (0, 0) initially.
     * When later used with CanvasWindow's add(GraphicsObject gObject, double x, double y), this group
     * will get placed at x, y.
     */
     public GraphicsGroup() {
        this(0.0,0.0);
    }

    /**
     * Adds the graphical object to the list of objects drawn on the canvas
     * @param gObject
     */
    public void add(GraphicsObject gObject){
        gObject.addObserver(this);
        gObjects.add(gObject);
        //java.awt.Rectangle objBounds = gObject.getBounds();
        bounds = bounds.union(gObject.getBounds());
        changed();
    }

    /**
     * Adds the graphical object to the list of objects drawn on the canvas
     * at the position x, y.
     *
     * @param gObject  the graphical object to be drawn
     * @param x        the x position of graphical object
     * @param y        the y position of graphical object
     */
    public void add(GraphicsObject gObject, double x, double y){
        gObject.setPosition(x, y);
        this.add(gObject);
    }

    /**
     * Removes the object from being drawn
     * @param gObject
     * @throws NoSuchElementException if gObject is not a part of the graphics group.
     */
    public void remove(GraphicsObject gObject){
        gObject.removeObserver(this);
        boolean success = gObjects.remove(gObject);
        if (!success){
            throw new NoSuchElementException("The object to remove is not part of this graphics group. It may have already been removed or was never originally added.");
        }
        changed();
    }

    /**
     * Removes all of the objects currently drawn on the canvas
     */
    public void removeAll(){
        Iterator<GraphicsObject> it = gObjects.iterator();
        while(it.hasNext()){
            GraphicsObject obj = it.next();
            obj.removeObserver(this);
            it.remove();
        }
        changed();
    }

    /**
     *  * Returns the topmost graphical object underneath position x, y. If no such object exists it returns null.
     * @param x position in the coordinate space of the container of this group
     * @param y position in the coordinate space of the container of this group
     * @return object at (x,y) or null if it does not exist.
     */
    public GraphicsObject getElementAt(double x, double y){
        Iterator<GraphicsObject> it = gObjects.descendingIterator();
        while(it.hasNext()){
            GraphicsObject obj = it.next();
            if (obj.testHit(x-this.x, y-this.y, subCanvas)){
                return obj;
            }
        }
        return null;
    }

    /**
     * Draws the rectangle on the screen
     * @param gc
     */
    public void draw(Graphics2D gc){
        if (bounds.isEmpty()) {
            return;
        }
        imgBuffer = new BufferedImage((int)Math.ceil(bounds.getX()+bounds.getWidth()), (int)Math.ceil(bounds.getY()+bounds.getHeight()), BufferedImage.TYPE_4BYTE_ABGR);
        subCanvas = imgBuffer.createGraphics();
        enableAntialiasing();
        subCanvas.setBackground(new Color(1, 1, 1, 0));
        subCanvas.clearRect(0, 0, (int)Math.ceil(bounds.getX()+bounds.getWidth()), (int)Math.ceil(bounds.getY()+bounds.getHeight()));

        for(GraphicsObject obj: gObjects){
            obj.draw(subCanvas);
        }
        // We need to draw on the sub canvas so that getElement at works properly

        //gc.drawImage(imgBuffer, (int)x, (int)y, null);

        gc.translate(x, y);
        for(GraphicsObject obj : gObjects){
            obj.draw(gc);
        }
        gc.translate(-x, -y);

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
     * Get the width of the rectangle
     * @return rectangle width
     */
    public double getWidth(){
        return bounds.getWidth();
    }

    /**
     * Get the height of the rectangle
     * @return rectangle height
     */
    public double getHeight(){
        return bounds.getHeight();
    }

    /**
     * Sets the shape's position to x, y
     * @param x
     * @param y
     */
    public void setPosition(double x, double y){
        this.x = x;
        this.y = y;
        changed();
    }

    /**
     * Gets the position of the graphical object
     * @return position
     */
    public Point.Double getPosition(){
        return new Point2D.Double(x, y);
    }

    /**
     * Move the shape from its current x, y position by dx and dy.
     * @param dx
     * @param dy
     */
    public void move(double dx, double dy){
        this.x += dx;
        this.y += dy;
        changed();
    }

    /**
     * Tests whether the point (x, y) hits the shape on the graphics window
     * @return true if this shape is the topmost object at point (x, y)
     */
    public boolean testHit(double x, double y, Graphics2D gc){
        java.awt.Rectangle test = new java.awt.Rectangle((int)Math.round(x), (int)Math.round(y), 1,1);
        for(GraphicsObject obj : gObjects) {
            if (obj.testHit(x-this.x, y-this.y, gc)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object other){
        if (other != null && other instanceof GraphicsGroup){
            GraphicsGroup otherShape = (GraphicsGroup) other;
            if (this.x == otherShape.x && this.y == otherShape.y && this.gObjects.equals(otherShape.gObjects)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return "A graphics group at position ("+getX()+", "+getY()+") with width="+getWidth()+" and height="+getHeight();
    }

    /**
     * Returns an axis aligned bounding rectangle for the graphical object in canvas coordinates.
     * @return
     */
    public java.awt.Rectangle getBounds(){
        return new java.awt.Rectangle((int) Math.ceil(this.x + bounds.getX()), (int) Math.ceil(this.y + bounds.getY()), (int) Math.ceil(bounds.getWidth()), (int) Math.ceil(bounds.getHeight()));
    }

    public Iterator<GraphicsObject> iterator(){
        return gObjects.iterator();
    }

    /**
     * Implementation of GraphicsObserver method. Notifies Java to repaint the image if any of the objects drawn on the canvas
     * have changed.
     * @param changedObject
     */
    public void graphicChanged(GraphicsObject changedObject){
        updateBounds();
        changed();
    }

    /**
     * Enables antialiasing on the drawn shapes.
     */
    private void enableAntialiasing() {
        subCanvas.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        subCanvas.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        subCanvas.setRenderingHint(
                RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE);
    }

    private void updateBounds(){
        java.awt.Rectangle newBounds = null;
        for(GraphicsObject gObject: gObjects) {
            if (newBounds != null) {
                newBounds = newBounds.union(gObject.getBounds());
            } else {
                java.awt.Rectangle objBounds = gObject.getBounds();
                if (objBounds != null) {
                    newBounds = new java.awt.Rectangle((int) objBounds.getX(), (int) objBounds.getY(), (int) objBounds.getWidth(), (int) objBounds.getHeight());
                }
            }
        }
        bounds = newBounds;
    }
}
