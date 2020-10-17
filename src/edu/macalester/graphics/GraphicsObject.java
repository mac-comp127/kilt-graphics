package edu.macalester.graphics;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

import javax.swing.JComponent;

/**
 * Abstract class representing some sort of graphical object that can be drawn and positioned on a
 * canvas.
 *
 * @author Bret Jackson
 */
public abstract class GraphicsObject {
    private List<GraphicsObserver> observers = new ArrayList<GraphicsObserver>();
    private CanvasWindow canvas;
    private double rotation = 0;
    private Point scale = Point.ONE_ONE;
    private AffineTransform transform = new AffineTransform(), inverseTransform = new AffineTransform();

    final void draw(Graphics2D gc) {
        AffineTransform oldTransform = gc.getTransform();
        gc.transform(transform);
        drawInLocalCoordinates(gc);
        gc.setTransform(oldTransform);
    }

    /**
     * For internal use. Draws this graphics object on the screen in its local coordinates,
     * without rotation or scaling.
     */
    protected abstract void drawInLocalCoordinates(Graphics2D gc);

    /**
     * Gets the position of the object on the canvas. The location of the anchor point
     * that we call the “position” can vary, but is typically the upper left.
     */
    public abstract Point getPosition();

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
     * Returns the object's current horizontal position.
     * @see getPosition()
     */
    public double getX() {
        return getPosition().getX();
    }

    /**
     * Changes this object's horizontal position while preserving its vertical position.
     */
    public void setX(double x) {
        setPosition(new Point(x, getY()));
    }

    /**
     * Returns the object's current vertical position.
     * @see getPosition()
     */
    public double getY() {
        return getPosition().getY();
    }

    /**
     * Changes this object's vertical position while preserving its horizontal position.
     */
    public void setY(double y) {
        setPosition(new Point(getX(), y));
    }

    /**
     * Returns the center of this shape's bounding box.
     */
    public final Point getCenter() {
        Rectangle2D bounds = getBounds();
        // width and height can sometimes be NaN, e.g. in an empty Path. If the bounds
        // have NaNs, just use the nominal position from getPosition().
        return new Point(
            Double.isNaN(bounds.getCenterX()) ? getPosition().getX() : bounds.getCenterX(),
            Double.isNaN(bounds.getCenterY()) ? getPosition().getY() : bounds.getCenterY());
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

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
        updateTransform();
    }

    public double getScaleX() {
        return scale.getX();
    }
    
    public double getScaleY() {
        return scale.getY();
    }

    public Point getScale() {
        return scale;
    }

    public void setScale(Point scale) {
        setScale(scale.getX(), scale.getY());
    }

    public void setScale(double scaleX, double scaleY) {
        this.scale = new Point(scaleX, scaleY);
        updateTransform();
    }

    public void setScale(double scale) {
        setScale(scale, scale);
    }

    private void updateTransform() {
        Point center = getCenter();
        transform.setToTranslation(center.getX(), center.getY());
        transform.rotate(Math.toRadians(rotation));
        transform.scale(scale.getX(), scale.getY());
        transform.translate(-center.getX(), -center.getY());

        // Can't just use invert() for this, because if
        // the scale is zero, the transform non-invertible
        inverseTransform.setToTranslation(center.getX(), center.getY());
        inverseTransform.scale(1 / scale.getX(), 1 / scale.getY());
        inverseTransform.rotate(Math.toRadians(-rotation));
        inverseTransform.translate(-center.getX(), -center.getY());

        changed();
    }

    /**
     * Tests whether the given point is on the boundary or interior of this graphic object's shape.
     * The point is in this object's local coordinates, not canvas coordinates.
     * <p>
     * Does not account for appearance, including stroke width and transparency.
     */
    public final boolean testHit(double x, double y) {
        Point2D.Double pt = convertToLocal(x, y);
        return testHitInLocalCoordinates(pt.x, pt.y);
    }

    private Point2D.Double convertToLocal(double x, double y) {
        Point2D.Double pt = new Point2D.Double(x, y);
        inverseTransform.transform(pt, pt);
        return pt;
    }

    /**
     * For internal use. Draws this graphics object on the screen in its local coordinates,
     * without rotation or scaling.
     */
    public abstract boolean testHitInLocalCoordinates(double x, double y);

    /**
     * Returns true if the given point in the parent's coordinate space is within the bounds of this
     * object. Note that testHit() actually checks the actual shape, whereas this method only tests
     * the bounds. So, for example, a point in the upper left corner of an Ellipse’s bounding box
     * could return true for isInBounds(), but false for testHit().
     */
    public boolean isInBounds(Point position) {
        return getBounds().contains(position.getX(), position.getY());
    }

    /**
     * Returns the topmost graphical object that touches position x, y. If no such object exists,
     * returns null. If this GraphicsObject has child elements, this method might return a child.
     *
     * @param x position in the coordinate space of this object’s container
     * @param y position in the coordinate space of this object’s container
     * @return object at (x,y) or null if it does not exist.
     */
    public final GraphicsObject getElementAt(double x, double y) {
        Point2D.Double pt = convertToLocal(x, y);
        return getElementAtLocalCoordinates(pt.x, pt.y);
    }

    protected GraphicsObject getElementAtLocalCoordinates(double x, double y) {
        if (testHitInLocalCoordinates(x, y)) {
            return this;
        } else {
            return null;
        }
    }

    /**
     * Returns the width and height of this graphics object.
     */
    public Point getSize() {
        Rectangle2D bounds = getBounds();
        return new Point(bounds.getWidth(), bounds.getHeight());
    }

    /**
     * Returns an axis-aligned bounding rectangle for this graphical object.
     */
    public abstract Rectangle2D getBounds();

    void forEachDescendant(Point origin, BiConsumer<GraphicsObject,Point> callback) {
        callback.accept(this, origin.add(getPosition()));
    }

    /**
     * Returns the window that this Object is inside, or null if it does not belong to a window.
     */
    public CanvasWindow getCanvas() {
        return canvas;
    }

    void setCanvas(CanvasWindow canvas) {
        if (canvas != this.canvas && canvas != null && this.canvas != null) {
            throw new IllegalStateException("Trying to add graphics object to two different windows");
        }
        this.canvas = canvas;
    }

    /**
     * For internal use only. Supports UI components.
     */
    public JComponent getEmbeddedComponent() {
        return null;
    }

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
