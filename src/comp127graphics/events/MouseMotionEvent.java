package comp127graphics.events;

import comp127graphics.Point;

/**
 * Carries information about the mouse moving from one location to another
 *
 * @see comp127graphics.CanvasWindow#onMouseMove(MouseMotionEventHandler)
 * @see comp127graphics.CanvasWindow#onDrag(MouseMotionEventHandler)
 *
 * @author Paul Cantrell
 */
public final class MouseMotionEvent {
    private final Point position, previousPosition, delta;

    /**
     * For internal use. Translates an underlying AWT event to a comp127graphics event.
     */
    public MouseMotionEvent(java.awt.event.MouseEvent sourceEvent, Point previousPosition) {
        position = new Point(sourceEvent.getPoint());
        this.previousPosition = previousPosition;
        delta = position.subtract(previousPosition);
    }

    /**
     * Returns mouse pointer's current location at the time of the event, in CanvasWindow coordinates.
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Returns mouse pointer's location at the time of the previously reported event, in
     * CanvasWindow coordinates.
     */
    public Point getPreviousPosition() {
        return previousPosition;
    }

    /**
     * Returns the difference between the mouse's previous and current positions.
     */
    public Point getDelta() {
        return delta;
    }
}
