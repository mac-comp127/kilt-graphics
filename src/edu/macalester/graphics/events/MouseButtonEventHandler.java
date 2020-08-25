package edu.macalester.graphics.events;

import edu.macalester.graphics.CanvasWindow;

/**
 * A listener that can receive events when the mouse button is clicked or released.
 *
 * @author Paul Cantrell
 * @see CanvasWindow#onClick(MouseButtonEventHandler)
 * @see CanvasWindow#onMouseDown(MouseButtonEventHandler)
 * @see CanvasWindow#onMouseUp(MouseButtonEventHandler)
 */
public interface MouseButtonEventHandler {
    void handleEvent(MouseButtonEvent event);
}
