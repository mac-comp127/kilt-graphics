package comp127graphics.events;

import java.awt.event.InputEvent;
import java.util.Set;

public class AbstractEvent {

    private final Set<ModifierKey> modifiers;

    AbstractEvent(InputEvent sourceEvent) {
        modifiers = ModifierKey.fromEvent(sourceEvent);
    }

    /**
     * Returns the set of modifier keys that were pressed when this event occurred.
     */
    public Set<ModifierKey> getModifiers() {
        return modifiers;
    }
}
