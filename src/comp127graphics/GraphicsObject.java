package comp127graphics;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing some sort of graphical object that can be drawn and positioned in a canvaswindow.
 *
 * @author Bret Jackson
 */
public abstract class GraphicsObject {

    private List<GraphicsObserver> observers = new ArrayList<GraphicsObserver>();

    /**
     * Gets the position of the object on the canvas. The location of the anchor point
     * that we call the “position” can vary, but is typically the upper left.
     */
    public abstract Point getPosition();

    /**
     * For internal use. Draws this graphics object on the screen.
     */
    protected abstract void draw(Graphics2D gc);

    /**
     * Moves this object to the given position.
     *
     * @param x position
     * @param y position
     */
    public abstract void setPosition(double x, double y);

    /**
     * Moves this object to the given position.
     */
    public final void setPosition(Point pos) {
        setPosition(pos.getX(), pos.getY());
    }

    /**
     * Returns the center of this shape's bounding box.
     */
    public final Point getCenter() {
        Rectangle2D bounds = getBounds();
        return new Point(bounds.getCenterX(), bounds.getCenterY());
    }

    /**
     * Moves the shape so its bounding box is centered at the given point.
     */
    public final void setCenter(double x, double y) {
        setCenter(new Point(x, y));
    }

    /**
     * Moves the shape so its bounding box is centered at the given point.
     */
    public final void setCenter(Point point) {
        moveBy(point.subtract(getCenter()));
    }

    /**
     * Move the shape from its current (x, y) position to (x + dx, y + dy).
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
     * Tests whether the given point is on the boundary or interior of this graphic object's shape.
     * The point is in this object's local coordinates, not canvas coordinates.
     * <p>
     * Does not account for appearance, including stroke width and transparency.
     */
    public abstract boolean testHit(double x, double y);

    /**
     * For internal use. Returns an axis-aligned bounding rectangle for the graphical object.
     */
    public abstract Rectangle2D getBounds();

    // ------ Observers ------

    /**
     * Adds an observer to be notified of visual changes to this graphics object (typically for the
     * purpose of knowing when to draw it).
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

    /**
     * Triggers a notifications to observers that this object's appearance has changed. Subclasses
     * should call this whenever anything changes that would alter this object's appearance.
     */
    protected void changed() {
        for (GraphicsObserver observer : observers) {
            observer.graphicChanged(this);
        }
    }
}
