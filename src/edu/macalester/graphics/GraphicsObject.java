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

    private Point position = Point.ORIGIN;
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
    public final Point getPosition() {
        return position;
    }

    /**
     * Moves this object to the given position.
     *
     * @param x position
     * @param y position
     */
    public final void setPosition(double x, double y) {
        setPosition(new Point(x, y));
    }

    /**
     * Moves this object to the given position.
     */
    public final void setPosition(Point position) {
        this.position = position;
        updateTransform();
    }

    /**
     * Returns the object's current horizontal position.
     * @see getPosition()
     */
    public final double getX() {
        return getPosition().getX();
    }

    /**
     * Changes this object's horizontal position while preserving its vertical position.
     */
    public final void setX(double x) {
        setPosition(new Point(x, getY()));
    }

    /**
     * Returns the object's current vertical position.
     * @see getPosition()
     */
    public final double getY() {
        return getPosition().getY();
    }

    /**
     * Changes this object's vertical position while preserving its horizontal position.
     */
    public final void setY(double y) {
        setPosition(new Point(getX(), y));
    }

    /**
     * Returns the center of this shape's bounding box.
     */
    public final Point getCenter() {
        Rectangle2D bounds = getBoundsLocal();
        // width and height can sometimes be NaN, e.g. in an empty Path. If the bounds
        // have NaNs, just use the nominal position from getPosition().
        return new Point(
            getX() + zeroIfNaN(bounds.getCenterX()),
            getY() + zeroIfNaN(bounds.getCenterY()));
    }

    private static double zeroIfNaN(double x) {
        return Double.isNaN(x) ? 0 : x;
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
    public final void moveBy(Point delta) {
        setPosition(getPosition().add(delta));
    }

    public final double getRotation() {
        return rotation;
    }

    public final void setRotation(double rotation) {
        this.rotation = rotation;
        updateTransform();
    }

    public final double getScaleX() {
        return scale.getX();
    }
    
    public final double getScaleY() {
        return scale.getY();
    }

    public final Point getScale() {
        return scale;
    }

    public final void setScale(Point scale) {
        setScale(scale.getX(), scale.getY());
    }

    public final void setScale(double scaleX, double scaleY) {
        this.scale = new Point(scaleX, scaleY);
        updateTransform();
    }

    public final void setScale(double scale) {
        setScale(scale, scale);
    }

    final AffineTransform getTransform() {
        return transform;
    }

    private void updateTransform() {
        Point center = getCenter();
        transform.setToTranslation(center.getX(), center.getY());
        transform.rotate(Math.toRadians(rotation));
        transform.scale(scale.getX(), scale.getY());
        transform.translate(-center.getX(), -center.getY());
        transform.translate(position.getX(), position.getY());

        // Can't just use invert() for this, because if
        // the scale is zero, the transform non-invertible
        inverseTransform.setToTranslation(-position.getX(), -position.getY());
        inverseTransform.translate(center.getX(), center.getY());
        inverseTransform.scale(1 / scale.getX(), 1 / scale.getY());
        inverseTransform.rotate(Math.toRadians(-rotation));
        inverseTransform.translate(-center.getX(), -center.getY());

        changed();
    }

    private Point2D.Double convertToLocal(double x, double y) {
        Point2D.Double pt = new Point2D.Double(x, y);
        inverseTransform.transform(pt, pt);
        return pt;
    }

    private Point2D.Double convertFromLocal(double x, double y) {
        Point2D.Double pt = new Point2D.Double(x, y);
        transform.transform(pt, pt);
        return pt;
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
    public final boolean isInBounds(Point position) {
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
    public final Point getSize() {
        Rectangle2D bounds = getBounds();
        return new Point(bounds.getWidth(), bounds.getHeight());
    }

    /**
     * Returns an axis-aligned bounding rectangle for this graphical object.
     */
    public final Rectangle2D getBounds() {
        Rectangle2D.Double bounds = new Rectangle2D.Double();
        bounds.setRect(getBoundsLocal());
        Point2D.Double
            pt0 = convertFromLocal(bounds.getMinX(), bounds.getMinY()),
            pt1 = convertFromLocal(bounds.getMaxX(), bounds.getMinY()),
            pt2 = convertFromLocal(bounds.getMinX(), bounds.getMaxY()),
            pt3 = convertFromLocal(bounds.getMaxX(), bounds.getMaxY());
        bounds.x      = min(pt0.x, pt1.x, pt2.x, pt3.x);
        bounds.y      = min(pt0.y, pt1.y, pt2.y, pt3.y);
        bounds.width  = max(pt0.x, pt1.x, pt2.x, pt3.x) - bounds.x;
        bounds.height = max(pt0.y, pt1.y, pt2.y, pt3.y) - bounds.y;
        return bounds;
    }

    private static double min(double a, double b, double c, double d) {
        return Math.min(Math.min(Math.min(a, b), c), d);
    }

    private static double max(double a, double b, double c, double d) {
        return Math.max(Math.max(Math.max(a, b), c), d);
    }

    /**
     * Returns the bounding box of this graphics object in its local coordinates.
     */
    protected abstract Rectangle2D getBoundsLocal();

    void forEachDescendant(Point origin, BiConsumer<GraphicsObject,Point> callback) {
        callback.accept(this, origin.add(getPosition()));
    }

    /**
     * Returns the window that this Object is inside, or null if it does not belong to a window.
     */
    public final CanvasWindow getCanvas() {
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
    public final void addObserver(GraphicsObserver observer) {
        observers.add(observer);
    }

    /**
     * Removes the given observer.
     */
    public final void removeObserver(GraphicsObserver observer) {
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
