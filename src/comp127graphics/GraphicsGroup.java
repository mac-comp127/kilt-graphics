package comp127graphics;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * A group of graphical objects that can be added, moved, and removed as a single unit.
 * The group defines its own coordinate system, so the positions of objects added to it are relative
 * to the whole group's position.
 *
 * @author Bret Jackson
 */
public class GraphicsGroup extends GraphicsObject implements GraphicsObserver {
    /**
     * Holds the objects to be drawn in calls to paintComponent
     */
    private ConcurrentLinkedDeque<GraphicsObject> children;

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
    private Rectangle2D bounds;

    /**
     * Constructs a new group. Each group has its own local coordinate system. The group is
     * positioned on the canvas at canvas position (x, y) when it is added.
     *
     * @param x
     * @param y
     */
    public GraphicsGroup(double x, double y) {
        this.x = x;
        this.y = y;
        children = new ConcurrentLinkedDeque<GraphicsObject>();
    }

    /**
     * Constructs a new group positioned at (0, 0).
     * When later used with CanvasWindow's add(GraphicsObject gObject, double x, double y), this group
     * will get placed at x, y.
     */
    public GraphicsGroup() {
        this(0.0, 0.0);
    }

    /**
     * Adds given graphical object to the list of objects drawn on the canvas. The last object added
     * is the one that will appear on top.
     */
    public void add(GraphicsObject gObject) {
        gObject.addObserver(this);
        children.add(gObject);
        gObject.setCanvas(getCanvas());
        changed();
    }

    /**
     * Adds the graphical object to the list of objects drawn on the canvas
     * at the position x, y.
     *
     * @param gObject the graphical object to be drawn
     * @param x       the x position of graphical object
     * @param y       the y position of graphical object
     */
    public void add(GraphicsObject gObject, double x, double y) {
        gObject.setPosition(x, y);
        this.add(gObject);
    }

    /**
     * Removes the object from being drawn
     *
     * @throws NoSuchElementException if gObject is not a part of the graphics group.
     */
    public void remove(GraphicsObject gObject) {
        gObject.removeObserver(this);
        gObject.setCanvas(null);
        boolean success = children.remove(gObject);
        if (!success) {
            throw new NoSuchElementException("The object to remove is not part of this graphics group. It may have already been removed or was never originally added.");
        }
        changed();
    }

    /**
     * Removes all of the objects in this group
     */
    public void removeAll() {
        Iterator<GraphicsObject> it = children.iterator();
        while (it.hasNext()) {
            GraphicsObject obj = it.next();
            obj.removeObserver(this);
            obj.setCanvas(null);
            it.remove();
        }
        changed();
    }

    /**
     * Returns the topmost graphical object that touches the given position. If no such object exists, returns null.
     *
     * @param p position in the coordinate space of the container of this group
     * @return object at (x,y) or null if it does not exist.
     */
    public GraphicsObject getElementAt(Point p) {
        return getElementAt(p.getX(), p.getY());
    }

    /**
     * Returns the topmost child of this group that touches position x, y. If no such object exists,
     * returns null. A GraphicsGroup will only return child elements; it never returns itself.
     */
    @Override
    public GraphicsObject getElementAt(double x, double y) {
        for (Iterator<GraphicsObject> it = children.descendingIterator(); it.hasNext(); ) {
            GraphicsObject obj = it.next();
            GraphicsObject hit = obj.getElementAt(x - this.x, y - this.y);
            if (hit != null) {
                return hit;
            }
        }
        return null;
    }

    @Override
    protected void draw(Graphics2D gc) {
        AffineTransform savedTransform = gc.getTransform();
        gc.translate(x, y);
        for (GraphicsObject obj : children) {
            obj.draw(gc);
        }
        gc.setTransform(savedTransform);
    }


    /**
     * Get the x position of the group's local coordinate system relative to the group's container.
     */
    public double getX() {
        return x;
    }

    /**
     * Get the y position of the group's local coordinate system relative to the group's container.
     */
    public double getY() {
        return y;
    }

    /**
     * Get the width of the rectangle that encloses all the elements in the group.
     */
    public double getWidth() {
        return getBounds().getWidth();
    }

    /**
     * Get the height of the rectangle that encloses all the elements in the group.
     */
    public double getHeight() {
        return getBounds().getHeight();
    }

    /**
     * Moves the entire group's coordinate system to (x,y).
     */
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
        changed();
    }

    /**
     * Get the position of the group's local coordinate system relative to the group's container.
     */
    public Point getPosition() {
        return new Point(x, y);
    }

    /**
     * Tests whether the point (x, y) hits some shape inside this group.
     */
    public boolean testHit(double x, double y) {
        return getElementAt(x, y) != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GraphicsGroup that = (GraphicsGroup) o;
        return Double.compare(that.x, x) == 0
                && Double.compare(that.y, y) == 0
                && children.equals(that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, children);
    }

    @Override
    public String toString() {
        return "A graphics group at position (" + getX() + ", " + getY() + ") with width=" + getWidth() + " and height=" + getHeight();
    }

    private void boundsNeedUpdate() {
        bounds = null;
    }

    /**
     * Returns an axis aligned bounding rectangle for the graphical object in canvas coordinates.
     */
    public Rectangle2D getBounds() {
        if (bounds == null) {
            Rectangle2D.Double newBounds = new Rectangle2D.Double(0, 0, 0, 0);
            for (GraphicsObject child : children) {
                if(child.getBounds() != null) {
                    Rectangle2D.union(newBounds, child.getBounds(), newBounds);
                }
            }
            newBounds.x += this.x;
            newBounds.y += this.y;
            bounds = newBounds;
        }
        return bounds;
    }

    /**
     * Returns an iterator over the contents of this group, in the order they will be drawn.
     */
    public Iterator<GraphicsObject> iterator() {
        return children.iterator();
    }

    void forEachDescendant(Point origin, BiConsumer<GraphicsObject,Point> callback) {
        super.forEachDescendant(origin, callback);

        Point groupOrigin = origin.add(getPosition());
        for (GraphicsObject child : children) {
            child.forEachDescendant(groupOrigin, callback);
        }
    }

    @Override
    void setCanvas(CanvasWindow canvas) {
        super.setCanvas(canvas);
        for (GraphicsObject child : children) {
            child.setCanvas(canvas);
        }
    }

    @Override
    protected void changed() {
        boundsNeedUpdate();
        super.changed();
    }

    /**
     * Implementation of GraphicsObserver method. Notifies Java to repaint the image if any of the objects drawn on the canvas
     * have changed.
     */
    public void graphicChanged(GraphicsObject changedObject) {
        boundsNeedUpdate();
        changed();
    }
}
