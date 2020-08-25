package edu.macalester.graphics.events;

import java.awt.event.KeyEvent;
import java.util.function.Consumer;

import edu.macalester.graphics.CanvasWindow;

/**
 * Carries information about a key being pressed or released.
 *
 * @author Paul Cantrell
 * @see CanvasWindow#onKeyDown(KeyboardEventHandler)
 * @see CanvasWindow#onKeyUp(KeyboardEventHandler)
 * @see CanvasWindow#onCharacterTyped(Consumer) 
 */
public final class KeyboardEvent extends AbstractEvent {

    private final Key key;

    /**
     * For internal use. Translates an underlying AWT event to a kilt-graphics event.
     */
    public KeyboardEvent(KeyEvent sourceEvent) {
        super(sourceEvent);
        this.key = Key.fromAwtKeyCode(
            sourceEvent.getKeyCode());
    }

    /**
     * The key that was pressed or released.
     */
    public Key getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "KeyboardEvent{"
            + "key=" + key
            + ",modifiers=" + getModifiers()
            + '}';
    }
}
