package comp127graphics.events;

/**
 * A listener that can receive events when the mouse moves.
 *
 * @author Paul Cantrell
 * @see comp127graphics.CanvasWindow#onMouseMove(MouseMotionEventHandler)
 * @see comp127graphics.CanvasWindow#onDrag(MouseMotionEventHandler)
 */
public interface MouseMotionEventHandler {
    void handleEvent(MouseMotionEvent event);
}
