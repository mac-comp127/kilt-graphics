package edu.macalester.graphics;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
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
    private final List<GraphicsObserver> observers = new ArrayList<>();
    private GraphicsGroup parent;

    private Point position = Point.ORIGIN;
    private double rotation = 0;
    private Point scale = Point.ONE_ONE;
    private Point anchor;
    private Object interpolationMode = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
    private final AffineTransform transform = new AffineTransform(), inverseTransform = new AffineTransform();

    final void draw(Graphics2D gc) {
        gc.setRenderingHint(RenderingHints.KEY_INTERPOLATION, interpolationMode);
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
     * Renders this graphics object to an offscreen pixel buffer.
     * @param image The destination buffer.
     */
    public void renderToBuffer(BufferedImage image) {
        var gc = image.createGraphics();
        gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gc.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        gc.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        draw(gc);
    }

    /**
     * Gets the position of the object on the canvas. The “position” is typically the upper left,
     * but this can vary. With text, for example, the y component of the position is the baseline,
     * not the top of the text.
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
     * @see #getPosition()
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
     * @see #getPosition()
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
        Rectangle2D bounds = getBounds();
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
     * Offsets the shape from its current position within its parent by the given delta.
     */
    public final void moveBy(Point delta) {
        setPosition(getPosition().add(delta));
    }

    /**
     * This graphic object’s visual rotation in degrees from its neutral position.
     * See {@link #setRotation(double) setRotation()} for details.
     */
    public final double getRotation() {
        return rotation;
    }

    /**
     * Changes this graphic object’s visual rotation from its neutral position. The offset is in
     * degrees, zero is neutral, and positive is clockwise.
     * <p>
     * Rotation affects the entire local coordinate system of the graphics object, so any size
     * parameters or child coordinates are within the rotated frame of reference.
     * <p>
     * By default, the object rotates around its center, but you can change this;
     * see {@link #setAnchor(Point) setAnchor()}.
     * <p>
     * This is an absolute rotation within the coordinate system of this object's parent. If you
     * want to adjust object’s rotation relative to its current value, use rotateBy().
     *
     * @see #setAnchor(Point)
     */
    public final void setRotation(double rotation) {
        this.rotation = rotation;
        updateTransform();
    }

    /**
     * Rotates the object around its anchor point by the given number of degrees. Positive is
     * clockwise.
     *
     * @see #setRotation(double)
     */    
    public final void rotateBy(double angle) {
        setRotation(getRotation() + angle);
    }

    /**
     * Returns the horizontal scaling factor for this graphics object.
     * @see #setScale(double,double)
     */
    public final double getScaleX() {
        return scale.getX();
    }
    
    /**
     * Returns the vertical scaling factor for this graphics object.
     * @see #setScale(double,double)
     */
    public final double getScaleY() {
        return scale.getY();
    }

    /**
     * Returns the scaling factor for this graphics object.
     * @see #setScale(double,double)
     */
    public final Point getScale() {
        return scale;
    }

    /**
     * Changes this graphics object's scaling factor.
     * @see #setScale(double,double)
     */
    public final void setScale(Point scale) {
        setScale(scale.getX(), scale.getY());
    }

    /**
     * Stretches / shrinks this graphics object by the given factor. A scale of 1 is natural size.
     * Either factor can be zero or negative; a negative factor flips the graphics.
     * <p>
     * Scaling affects the entire local coordinate system of the graphics object, so any size
     * parameters, stroke sizes, child coordinates, etc. are within the scaled frame of reference.
     * <p>
     * By default, the object scales around its center, but you can change this;
     * see {@link #setAnchor(Point) setAnchor()}.
     * <p>
     * Scaling always happens <i>before</i> rotation, so for example if you take a circle, call
     * {@code setRotation(45)}, then call {@code setScale(1, 0.1)}, you will see a narrow ellipse
     * whose major axis is at a 45° angle.
     *
     * @param scaleX The horizontal scale factor. 1 = original size.
     * @param scaleY The vertical scale factor. 1 = original size.
     * @see #setAnchor(Point)
     */
    public final void setScale(double scaleX, double scaleY) {
        this.scale = new Point(scaleX, scaleY);
        updateTransform();
    }

    /**
     * Scales this object uniformly, setting both the horizontal and vertical scaling factor to the
     * given value.
     * @see #setScale(double,double)
     */
    public final void setScale(double scale) {
        setScale(scale, scale);
    }


    /**
     * Sets the interpolation mode used when rendering this object. Has no effect unless the
     * object is scaled or rotated. "Bilinear" is the default. "Nearest Neighbor" looks best 
     * for pixel art. "Bicubic" may look better than bilinear in certain situations.
     * @param mode "Nearest Neighbor", "Bilinear", or "Bicubic". Case-insensitive.
     */
    public final void setInterpolationMode(String mode) {
        switch (mode.toLowerCase()) {
            case "nearest neighbor":
                interpolationMode = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
                break;
            case "bicubic":
                interpolationMode = RenderingHints.VALUE_INTERPOLATION_BICUBIC;
                break;
            default:
                interpolationMode = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
                break;
        }
    }

    /**
     * The point relative to which scaling and rotation occur.
     * See {@link #setAnchor(Point)} for details.
     */
    public Point getAnchor() {
        return anchor;
    }

    /**
     * Changes the point relative to which scaling and rotation occur.
     * <p>
     * A graphics object’s <b>anchor point</b> is the one point that remains in a fixed position
     * when scale and rotation change. It is in the pre-rotation, pre-scaling local coordinates of
     * this graphics object; it is relative to getPosition() in the parent’s coordinate space.
     * <p>
     * By default, the anchor point is the object’s center, and continually changes if the object’s
     * center changes. This can cause unstable motion if the object’s size is changing while it is
     * rotated and/or scaled; in this situation, you will usually want to set a fixed anchor point.
     * To set the anchor back to the default, call {@code setAnchor(null)}.
     *
     * @param anchor The new anchor point, or null to follow the object’s center.
     * @see #setRotation(double)
     * @see #setScale(double,double)
     */
    public void setAnchor(Point anchor) {
        this.anchor = anchor;
        updateTransform();
    }

    /**
     * Fixes scaling and rotation around the given coordinate.
     * @see #setAnchor(Point)
     */
    public void setAnchor(double x, double y) {
        setAnchor(new Point(x, y));
    }

    final AffineTransform getTransform() {
        return transform;
    }

    private void updateTransform() {
        Point transformAnchor = (anchor != null) ? anchor.add(position) : getCenter();
        transform.setToTranslation(transformAnchor.getX(), transformAnchor.getY());
        transform.rotate(Math.toRadians(rotation));
        transform.scale(scale.getX(), scale.getY());
        transform.translate(-transformAnchor.getX(), -transformAnchor.getY());
        transform.translate(position.getX(), position.getY());

        // Can't just use invert() for this because if
        // either scale is zero, the transform non-invertible
        inverseTransform.setToTranslation(-position.getX(), -position.getY());
        inverseTransform.translate(transformAnchor.getX(), transformAnchor.getY());
        inverseTransform.scale(1 / scale.getX(), 1 / scale.getY());
        inverseTransform.rotate(Math.toRadians(-rotation));
        inverseTransform.translate(-transformAnchor.getX(), -transformAnchor.getY());

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
     * Tests whether the given point is on the boundary or interior of this graphic object’s shape.
     * The point is in the parent object’s local coordinates, not canvas coordinates.
     * <p>
     * Does not account for appearance, including stroke width and transparency.
     */
    public final boolean testHit(double x, double y) {
        Point2D.Double pt = convertToLocal(x, y);
        return testHitInLocalCoordinates(pt.x, pt.y);
    }

    /**
     * Tests whether the given point is inside the boundary or interior of this object’s shape.
     * The point is in this object’s local coordinates.
     */
    public abstract boolean testHitInLocalCoordinates(double x, double y);

    /**
     * Returns true if the given point in the parent's coordinate space is within the bounds of this
     * object. Note that testHit() actually checks the actual shape, whereas this method only tests
     * the bounds. So, for example, a point in the upper left corner of an Ellipse’s bounding box
     * could return true for isInBounds(), but false for testHit().
     */
    public final boolean isInBounds(Point position) {
        return getBoundsInParent().contains(position.getX(), position.getY());
    }

    /**
     * Returns the topmost graphical object that touches the given position in the parent’s
     * coordinate space. If no such object exists, this method returns null. If this GraphicsObject
     * has child elements, this method might return a child.
     *
     * @param x position in the coordinate space of this object’s container
     * @param y position in the coordinate space of this object’s container
     * @return object at (x,y) or null if it does not exist.
     */
    public final GraphicsObject getElementAt(double x, double y) {
        Point2D.Double pt = convertToLocal(x, y);
        return getElementAtLocalCoordinates(pt.x, pt.y);
    }

    /**
     * Returns the topmost graphical object that touches the given position in this object’s
     * coordinate space.
     * @see #getElementAt(double,double)
     */
    public GraphicsObject getElementAtLocalCoordinates(double x, double y) {
        if (testHitInLocalCoordinates(x, y)) {
            return this;
        } else {
            return null;
        }
    }

    /**
     * Returns the width and height of this graphics object, in its <b>local</b> coordinates. This
     * does not account for any scaling and rotation of the object.
     */
    public final Point getSize() {
        Rectangle2D bounds = getBounds();
        return new Point(bounds.getWidth(), bounds.getHeight());
    }

    /**
     * Returns the width of this graphics object, in its local coordinates.
     */
    public final double getWidth() {
        return getSize().getX();
    }

    /**
     * Returns the height of this graphics object, in its local coordinates.
     */
    public final double getHeight() {
        return getSize().getY();
    }

    /**
     * Returns an upper limit on the width and height of this graphics object as it appears in
     * its parent’s coordinate space, <i>after</i> applying this object’s scaling and rotation.
     * <p>
     * Note that this may be an overestimate because it describes the size of the <i>transformed
     * bounding box</i>, not the transformed <i>shape</i>. So, for example, a circle’s transformed
     * size grows larger when the circle is rotated 45°.
     */
    public final Point getSizeInParent() {
        Rectangle2D bounds = getBoundsInParent();
        return new Point(bounds.getWidth(), bounds.getHeight());
    }

    /**
     * Returns an axis-aligned bounding rectangle for this graphics object in its parent’s coordinate
     * space, accounting for this object’s scaling and rotation.
     * <p>
     * Note that this may be an overestimate because it describes the size of the <i>transformed
     * bounding box</i>, not the transformed <i>shape</i>. So, for example, a circle’s transformed
     * bounding box grows larger when the circle is rotated 45°.
     */
    public final Rectangle2D getBoundsInParent() {
        Rectangle2D.Double bounds = new Rectangle2D.Double();
        bounds.setRect(getBounds());
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
    public abstract Rectangle2D getBounds();

    void forEachDescendant(Point origin, BiConsumer<GraphicsObject,Point> callback) {
        callback.accept(this, origin.add(getPosition()));
    }

    /**
     * Returns the group that contains this graphics object, or null if it does not belong to a group.
     */
    public GraphicsGroup getParent() {
        return parent;
    }

    /**
     * Returns the window that this Object is inside, or null if it does not belong to a window.
     */
    public CanvasWindow getCanvas() {
        return (parent == null) ? null : parent.getCanvas();
    }

    void setParent(GraphicsGroup parent) {
        if (parent != this.parent && parent != null && this.parent != null) {
            throw new IllegalStateException(
                "Cannot add " + this.getClass().getSimpleName() + " to group,"
                + " because it already belongs to a different group");
        }
        this.parent = parent;
    }

    /**
     * For internal use only. Supports UI components.
     */
    public JComponent getEmbeddedComponent() {
        return null;
    }

    /**
     * Two GraphicsObjects are equal if are of the same class and have the same shape (position, rotation,
     * scale, vertices), regardless of appearance (color and stroke width).
     */
    @Override
    public final boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        GraphicsObject that = (GraphicsObject) other;
        return transform.equals(that.transform)
            && getEqualityAttributes().equals(that.getEqualityAttributes());
    }

    @Override
    public final int hashCode() {
        return transform.hashCode() + 173 * getEqualityAttributes().hashCode();
    }

    /**
     * For internal use only.
     * 
     * Used to compute equals() and hashCode(). Returns an object whose equals() and hashCode() methods
     * encompass the subclass-specific values that should be used to compute equality for the whole
     * GraphicsObject.
     */
    protected abstract Object getEqualityAttributes();

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
