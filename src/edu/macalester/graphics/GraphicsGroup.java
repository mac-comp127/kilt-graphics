package edu.macalester.graphics;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.BiConsumer;

/**
 * A group of graphical objects that can be added, moved, and removed as a single unit.
 * The group defines its own coordinate system, so the positions of objects added to it are relative
 * to the whole group's position.
 * <p>
 * Calling {@link setPosition(Point)} on a GraphicsGroup sets where the group’s local (0,0) shows up
 * within its parent. This means that a group’s position is not necessarily the upper left, the
 * center, or any other fixed relationship with the shapes inside the group. Instead, you determine
 * how the group’s graphics relate to the whole group’s position when you set the position of each
 * element within the group.
 * <p>
 * A graphics group’s size includes the extent of the bounding boxes of all its contents. The size
 * does not necessarily include the origin: a group containing only a 1x1 rectangle at (100, 100)
 * has a width and height of 1.
 *
 * @author Bret Jackson
 */
public class GraphicsGroup extends GraphicsObject implements GraphicsObserver {
    /**
     * Holds the objects to be drawn in calls to paintComponent
     */
    private final List<GraphicsObject> children;

    /**
     * Bounding rectangle around all of the graphicObjects contained in this group in window coordinates.
     */
    private Rectangle2D bounds;

    /**
     * Constructs a new group. Each group has its own local coordinate system. The group is
     * positioned on the canvas at canvas position (x, y) when it is added.
     */
    public GraphicsGroup(double x, double y) {
        children = new ArrayList<GraphicsObject>();
        setPosition(x, y);
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
        if (!children.removeIf(child -> child == gObject)) {
            throw new NoSuchElementException("The object to remove is not part of this graphics group. Either it is already removed, or it was never originally added.");
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
    public GraphicsObject getElementAtLocalCoordinates(double x, double y) {
        for (var it = children.listIterator(children.size()); it.hasPrevious(); ) {
            GraphicsObject obj = it.previous();
            GraphicsObject hit = obj.getElementAt(x, y);
            if (hit != null) {
                return hit;
            }
        }
        return null;
    }

    @Override
    protected void drawInLocalCoordinates(Graphics2D gc) {
        for (GraphicsObject obj : children) {
            obj.draw(gc);
        }
    }

    /**
     * Tests whether the point (x, y) hits some shape inside this group.
     */
    @Override
    public boolean testHitInLocalCoordinates(double x, double y) {
        return getElementAtLocalCoordinates(x, y) != null;
    }

    private void boundsNeedUpdate() {
        bounds = null;
    }

    @Override
    public Rectangle2D getBounds() {
        if (bounds == null) {
            Rectangle2D allBounds = null;
            for (GraphicsObject child : children) {
                Rectangle2D bounds = child.getBoundsInParent();
                if(bounds != null) {
                    if (allBounds == null) {
                        allBounds = bounds;
                    } else {
                        Rectangle2D.union(allBounds, bounds, allBounds);
                    }
                }
            }
            if (allBounds == null) {
                bounds = new Rectangle2D.Double();
            } else {
                bounds = allBounds;
            }
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

    @Override
    protected Object getEqualityAttributes() {
        return children;
    }

    @Override
    public String toString() {
        return "A graphics group at position (" + getX() + ", " + getY() + ") with size=" + getSize();
    }
}
