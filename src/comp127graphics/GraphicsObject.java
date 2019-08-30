package comp127graphics;

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
     * Moves this object to the given position.
     * @param x position
     * @param y position
     */
    public abstract void setPosition(double x, double y);

    /**
     * Moves this object to the given position.
     */
    public void setPosition(Point pos) {
        setPosition(pos.getX(), pos.getY());
    }

    /**
     * Move the shape from its current x, y position by dx and dy.
     * @param dx
     * @param dy
     */
    public final void moveBy(double dx, double dy) {
        moveBy(new Point(dx, dy));
    }

    /**
     * Offsets the shape from its current position by the given delta.
     */
    public void moveBy(Point delta) {
        setPosition(getPosition().add(delta));
    }

    /**
     * Gets the position of the object on the canvas.
     * @return
     */
    public abstract Point getPosition();

    /**
     * Tests whether the given point is on the boundary or interior of this graphic object's shape.
     * The point is in this object's local coordinates, not canvas coordinates.
     *
     * Does not account for appearance, including stroke width and transparency.
     */
    public abstract boolean testHit(double x, double y);

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
