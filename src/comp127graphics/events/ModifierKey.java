package comp127graphics.events;

import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

import static java.util.stream.Collectors.toCollection;

public enum ModifierKey {
    /**
     * The platform-dependent primary modifier key for menu shortcuts:
     * command (cloverleaf-like symbol) on macOS, control on Windows.
     */
    SHORTCUT(Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()),

    /**
     * The command key on macOS.
     */
    COMMAND_OR_META(InputEvent.META_DOWN_MASK),

    /**
     * The option key on macOS, or alt key on Windows.
     */
    OPTION_OR_ALT(InputEvent.ALT_DOWN_MASK),

    /**
     * The control (or ctrl) key.
     */
    CONTROL(InputEvent.CTRL_DOWN_MASK),

    /**
     * The shift key.
     */
    SHIFT(InputEvent.SHIFT_DOWN_MASK);

    private final int awtMask;

    ModifierKey(int awtMask) {
        this.awtMask = awtMask;
    }

    static Set<ModifierKey> fromEvent(InputEvent sourceEvent) {
        return ModifierKey.fromAWTMask(
            sourceEvent.getModifiersEx());
    }

    static Set<ModifierKey> fromAWTMask(int mask) {
        return Collections.unmodifiableSet(
            Arrays.stream(values())
                .filter(key -> (mask & key.awtMask) != 0)
                .collect(toCollection(() ->
                    EnumSet.noneOf(ModifierKey.class))));
    }
}
