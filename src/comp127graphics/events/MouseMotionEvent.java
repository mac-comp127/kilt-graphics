package comp127graphics.events;

import comp127graphics.Point;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Set;

/**
 * Carries information about the mouse moving from one location to another.
 *
 * @author Paul Cantrell
 * @see comp127graphics.CanvasWindow#onMouseMove(MouseMotionEventHandler)
 * @see comp127graphics.CanvasWindow#onDrag(MouseMotionEventHandler)
 */
public final class MouseMotionEvent extends AbstractEvent {
    private final Point position, previousPosition, delta;

    /**
     * For internal use. Translates an underlying AWT event to a comp127graphics event.
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
