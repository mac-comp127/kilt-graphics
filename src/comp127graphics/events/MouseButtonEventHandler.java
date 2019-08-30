package comp127graphics.events;

/**
 * A listener that can receive events when the mouse button is clicked or released.
 *
 * @see comp127graphics.CanvasWindow#onClick(MouseButtonEventHandler)
 * @see comp127graphics.CanvasWindow#onMouseDown(MouseButtonEventHandler)
 * @see comp127graphics.CanvasWindow#onMouseUp(MouseButtonEventHandler)
 *
 * @author Paul Cantrell
 */
public interface MouseButtonEventHandler {
    void handleEvent(MouseButtonEvent event);
}
