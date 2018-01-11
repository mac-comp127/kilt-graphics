package comp124graphics;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing some sort of graphical object that can be drawn and positioned in a canvaswindow.
 * Created by bjackson on 9/13/2016.
 * @version 0.5
 */
public abstract class GraphicsObject {

    private List<GraphicsObserver> observers = new ArrayList<GraphicsObserver>();

    /**
     * Draws the graphicsobject on the screen
     * @param gc
     */
    public abstract void draw(Graphics2D gc);

    /**
     * Move the shape from its current x, y position by dx and dy.
     * @param dx
     * @param dy
     */
    public abstract void move(double dx, double dy);

    /**
     * Sets the position of the graphical object
     * @param x position
     * @param y position
     */
    public abstract void setPosition(double x, double y);

    /**
     * Gets the position of the object on the canvas.
     * @return
     */
    public abstract Point.Double getPosition();

    /**
     * Tests whether the point (x, y) hits the shape on the graphics window
     * @return true if this shape is the topmost object at point (x, y)
     */
    public abstract boolean testHit(double x, double y, Graphics2D gc);

    /**
     * Returns an axis aligned bounding rectangle for the graphical object.
     * @return
     */
    public abstract java.awt.Rectangle getBounds();

    // ------ Observers ------

    /**
     * Adds an observer to be notified of this turtle's motions (typically for the purpose of
     * drawing them to paper).
     */
    public void addObserver(GraphicsObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes the given observer.
     */
    public void removeObserver(GraphicsObserver observer) {
        observers.remove(observer);
    }

    protected void changed() {
        for(GraphicsObserver observer : observers) {
            observer.graphicChanged(this);
        }
    }
}
