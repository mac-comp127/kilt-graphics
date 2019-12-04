package comp127graphics.events;

/**
 * A listener that can receive events when the mouse moves.
 *
 * @author Paul Cantrell
 * @see comp127graphics.CanvasWindow#onKeyDown(KeyboardEventHandler)
 * @see comp127graphics.CanvasWindow#onKeyUp(KeyboardEventHandler)
 */
public interface KeyboardEventHandler {
    void handleEvent(KeyboardEvent event);
}
