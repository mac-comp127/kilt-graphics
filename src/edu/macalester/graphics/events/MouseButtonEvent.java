package edu.macalester.graphics.events;

import edu.macalester.graphics.Point;
import edu.macalester.graphics.CanvasWindow;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

/**
 * Carries information about the mouse button being pressed or released.
 *
 * @author Paul Cantrell
 * @see CanvasWindow#onClick(MouseButtonEventHandler)
 * @see CanvasWindow#onMouseDown(MouseButtonEventHandler)
 * @see CanvasWindow#onMouseUp(MouseButtonEventHandler)
 */
public final class MouseButtonEvent extends AbstractEvent {
    private final Point position;
    private final int button;
    private final int eventId;
    private final int modifiersEx;

    // mouse buttons:
    public static final int BUTTON1 = MouseEvent.BUTTON1;
    public static final int BUTTON2  = MouseEvent.BUTTON2;
    public static final int BUTTON3  = MouseEvent.BUTTON3;
    // The AWT MouseEvent doesn't define this, but the clicks raise this event.
    public static final int BUTTON4  = 4;
    public static final int BUTTON5  = 5;
    public static final int NOBUTTON = MouseEvent.NOBUTTON;

    // events:
    public static final int MOUSE_CLICKED = MouseEvent.MOUSE_CLICKED;
    public static final int MOUSE_DRAGGED = MouseEvent.MOUSE_DRAGGED;
    public static final int MOUSE_ENTERED = MouseEvent.MOUSE_ENTERED;
    public static final int MOUSE_EXITED = MouseEvent.MOUSE_EXITED;
    public static final int MOUSE_FIRST = MouseEvent.MOUSE_FIRST;
    public static final int MOUSE_LAST = MouseEvent.MOUSE_LAST;
    public static final int MOUSE_MOVED = MouseEvent.MOUSE_MOVED;
    public static final int MOUSE_PRESSED = MouseEvent.MOUSE_PRESSED;
    public static final int MOUSE_RELEASED = MouseEvent.MOUSE_RELEASED;
    public static final int MOUSE_WHEEL = MouseEvent.MOUSE_WHEEL;

    // modifier masks
    private static final int BUTTON1_DOWN_MASK = InputEvent.getMaskForButton(1);
    private static final int BUTTON2_DOWN_MASK = InputEvent.getMaskForButton(2);
    private static final int BUTTON3_DOWN_MASK = InputEvent.getMaskForButton(3);
    private static final int BUTTON4_DOWN_MASK = InputEvent.getMaskForButton(4);
    private static final int BUTTON5_DOWN_MASK = InputEvent.getMaskForButton(5);
    private static final int CTRL_DOWN_MASK = InputEvent.CTRL_DOWN_MASK;
    private static final int SHIFT_DOWN_MASK = InputEvent.SHIFT_DOWN_MASK;
    private static final int META_DOWN_MASK = InputEvent.META_DOWN_MASK;
    private static final int ALT_DOWN_MASK = InputEvent.ALT_DOWN_MASK;

    /**
     * For internal use. Translates an underlying AWT event to a kilt-graphics event.
     */
    public MouseButtonEvent(MouseEvent sourceEvent) {
        super(sourceEvent);
        button = sourceEvent.getButton();
        eventId = sourceEvent.getID();
        modifiersEx = sourceEvent.getModifiersEx();
        position = new Point(sourceEvent.getPoint());
    }

    /**
     * Returns the location of the mouse pointer when the button event occurred, in CanvasWindow
     * coordinates.
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Return the button pressed.
     *
     * @author Dan Drake
     * @see MouseEvent#BUTTON1 and similar fields.
     * @return int describing the button pressed
     */
    public int getButton() { return button; }

    /**
     * Return the event ID. This is an int between MOUSE_FIRST and MOUSE_LAST.
     *
     * @author Dan Drake
     * @see MouseEvent#MOUSE_CLICKED and similar fields.
     * @return int describing the event
     */
    public int getEventId() { return eventId; }


    /**
     * Returns the extended modifier mask for this event.
     *
     * Extended modifiers are the modifiers that ends with the _DOWN_MASK suffix, such as ALT_DOWN_MASK, BUTTON1_DOWN_MASK, and others.
     *
     * Extended modifiers represent the state of all modal keys, such as ALT, CTRL, META, and the mouse buttons just after the event occurred.
     *
     * @author Dan Drake
     * @see InputEvent#SHIFT_DOWN_MASK and similar fields.
     * @return integer mask describing extended modifiers for this event.
     */
    public int getModifiersEx() { return modifiersEx; }

    /**
     * Returns a String instance describing the modifier keys and mouse buttons that were down during the event, such as "Shift", or "Ctrl+Shift".
     *
     * Note that the InputEvent.ALT_MASK and InputEvent.BUTTON2_MASK have equal values, so the "Alt" string is returned for both modifiers.
     * Likewise, the InputEvent.META_MASK and InputEvent.BUTTON3_MASK have equal values, so the "Meta" string is returned for both modifiers.
     *
     * @author Dan Drake
     * @see MouseEvent#getMouseModifiersText
     * @return string describing modifiers and buttons down during event.
     */
    public String getMouseModifiersText() {
        return MouseEvent.getMouseModifiersText(modifiersEx);
    }

    @Override
    public String toString() {
        return "MouseButtonEvent{"
            + "position=" + position
            + ", button=" + button
            + ", event ID=" + eventId
            + ", modifiers=" + modifiersEx
            + '}';
    }
}
