package edu.macalester.graphics.events;

import edu.macalester.graphics.Point;
import edu.macalester.graphics.CanvasWindow;

/**
 * Carries information about the mouse moving from one location to another.
 *
 * @author Paul Cantrell
 * @see CanvasWindow#onMouseMove(MouseMotionEventHandler)
 * @see CanvasWindow#onDrag(MouseMotionEventHandler)
 */
public final class MouseMotionEvent extends AbstractEvent {
    private final Point position, previousPosition, delta;

    /**
     * For internal use. Translates an underlying AWT event to a kilt-graphics event.
     */
    public MouseMotionEvent(java.awt.event.MouseEvent sourceEvent, Point previousPosition) {
        super(sourceEvent);
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

    @Override
    public String toString() {
        return "MouseMotionEvent{"
            + "position=" + position
            + ", previousPosition=" + previousPosition
            + ", delta=" + delta
            + ", modifiers=" + getModifiers()
            + '}';
    }
}
