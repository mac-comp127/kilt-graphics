package comp127graphics.events;

import comp127graphics.Point;

/**
 * Carries information about the mouse button being pressed or released.
 *
 * @author Paul Cantrell
 * @see comp127graphics.CanvasWindow#onClick(MouseButtonEventHandler)
 * @see comp127graphics.CanvasWindow#onMouseDown(MouseButtonEventHandler)
 * @see comp127graphics.CanvasWindow#onMouseUp(MouseButtonEventHandler)
 */
public final class MouseButtonEvent extends AbstractEvent {
    private final Point position;

    /**
     * For internal use. Translates an underlying AWT event to a comp127graphics event.
     */
    public MouseButtonEvent(java.awt.event.MouseEvent sourceEvent) {
        super(sourceEvent);
        position = new Point(sourceEvent.getPoint());
    }

    /**
     * Returns the location of the mouse pointer when the button event occurred, in CanvasWindow
     * coordinates.
     */
    public Point getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "MouseButtonEvent{"
            + "position=" + position
            + ", modifiers=" + getModifiers()
            + '}';
    }
}
