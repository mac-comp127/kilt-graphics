package comp127graphics.events;

import java.awt.event.KeyEvent;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A key on the keyboard. Note that this represents the <b>key itself</b>, not the character the
 * user typed. For example, if the user types an exclamation point, the KeyEvent the app receives
 * will be NUM_1 with a SHIFT modifier.
 *
 * @see KeyboardEvent
 */
public enum Key {
    NUM_0(KeyEvent.VK_0, KeyEvent.VK_NUMPAD0),
    NUM_1(KeyEvent.VK_1, KeyEvent.VK_NUMPAD1),
    NUM_2(KeyEvent.VK_2, KeyEvent.VK_NUMPAD2),
    NUM_3(KeyEvent.VK_3, KeyEvent.VK_NUMPAD3),
    NUM_4(KeyEvent.VK_4, KeyEvent.VK_NUMPAD4),
    NUM_5(KeyEvent.VK_5, KeyEvent.VK_NUMPAD5),
    NUM_6(KeyEvent.VK_6, KeyEvent.VK_NUMPAD6),
    NUM_7(KeyEvent.VK_7, KeyEvent.VK_NUMPAD7),
    NUM_8(KeyEvent.VK_8, KeyEvent.VK_NUMPAD8),
    NUM_9(KeyEvent.VK_9, KeyEvent.VK_NUMPAD9),

    A(KeyEvent.VK_A),
    B(KeyEvent.VK_B),
    C(KeyEvent.VK_C),
    D(KeyEvent.VK_D),
    E(KeyEvent.VK_E),
    F(KeyEvent.VK_F),
    G(KeyEvent.VK_G),
    H(KeyEvent.VK_H),
    I(KeyEvent.VK_I),
    J(KeyEvent.VK_J),
    K(KeyEvent.VK_K),
    L(KeyEvent.VK_L),
    M(KeyEvent.VK_M),
    N(KeyEvent.VK_N),
    O(KeyEvent.VK_O),
    P(KeyEvent.VK_P),
    Q(KeyEvent.VK_Q),
    R(KeyEvent.VK_R),
    S(KeyEvent.VK_S),
    T(KeyEvent.VK_T),
    U(KeyEvent.VK_U),
    V(KeyEvent.VK_V),
    W(KeyEvent.VK_W),
    X(KeyEvent.VK_X),
    Y(KeyEvent.VK_Y),
    Z(KeyEvent.VK_Z),

    SHIFT(KeyEvent.VK_SHIFT),
    CONTROL(KeyEvent.VK_CONTROL),
    ALT(KeyEvent.VK_ALT),
    CAPS_LOCK(KeyEvent.VK_CAPS_LOCK),
    NUM_LOCK(KeyEvent.VK_NUM_LOCK),
    SCROLL_LOCK(KeyEvent.VK_SCROLL_LOCK),
    WINDOWS(KeyEvent.VK_WINDOWS),
    CONTEXT_MENU(KeyEvent.VK_CONTEXT_MENU),
    COMMAND_OR_META(KeyEvent.VK_META),
    PRINTSCREEN(KeyEvent.VK_PRINTSCREEN),
    INSERT(KeyEvent.VK_INSERT),
    HELP(KeyEvent.VK_HELP),

    LEFT_ARROW(KeyEvent.VK_LEFT, KeyEvent.VK_KP_LEFT),
    UP_ARROW(KeyEvent.VK_UP, KeyEvent.VK_KP_UP),
    RIGHT_ARROW(KeyEvent.VK_RIGHT, KeyEvent.VK_KP_RIGHT),
    DOWN_ARROW(KeyEvent.VK_DOWN, KeyEvent.VK_KP_DOWN),

    PAGE_UP(KeyEvent.VK_PAGE_UP),
    PAGE_DOWN(KeyEvent.VK_PAGE_DOWN),
    END(KeyEvent.VK_END),
    HOME(KeyEvent.VK_HOME),

    DELETE_OR_BACKSPACE(KeyEvent.VK_BACK_SPACE),
    FORWARD_DELETE(KeyEvent.VK_DELETE),
    ESCAPE(KeyEvent.VK_ESCAPE),
    CANCEL(KeyEvent.VK_CANCEL),
    CLEAR(KeyEvent.VK_CLEAR),
    PAUSE(KeyEvent.VK_PAUSE),

    SPACE(KeyEvent.VK_SPACE),
    TAB(KeyEvent.VK_TAB),
    RETURN_OR_ENTER(KeyEvent.VK_ENTER),

    APOSTROPHE(KeyEvent.VK_QUOTE),
    BACKSLASH(KeyEvent.VK_BACK_SLASH),
    BACKTICK(KeyEvent.VK_BACK_QUOTE),
    COMMA(KeyEvent.VK_COMMA),
    PERIOD(KeyEvent.VK_PERIOD, KeyEvent.VK_DECIMAL),
    MINUS(KeyEvent.VK_MINUS, KeyEvent.VK_SUBTRACT),
    EQUALS(KeyEvent.VK_EQUALS),
    SEMICOLON(KeyEvent.VK_SEMICOLON),
    SLASH(KeyEvent.VK_SLASH, KeyEvent.VK_DIVIDE),
    LEFT_SQUARE_BRACKET(KeyEvent.VK_OPEN_BRACKET),
    RIGHT_SQUARE_BRACKET(KeyEvent.VK_CLOSE_BRACKET),

    // Java supports the following keys, but most keyboards do not actually have a key for them.
    // We therefore leave the unavailable to prevent confusion, so that e.g. students do not expect
    // to receive an event with AMPERSAND instead of NUM_7 with a SHIFT modifier.
    //
    // If you do in fact have a keyboad that has one of these keys and need to support it, file
    // an issue on comp127graphics.
    //
    //    AMPERSAND(KeyEvent.VK_AMPERSAND),
    //    ASTERISK(KeyEvent.VK_ASTERISK),
    //    AT_SIGN(KeyEvent.VK_AT),
    //    CIRCUMFLEX(KeyEvent.VK_CIRCUMFLEX),
    //    COLON(KeyEvent.VK_COLON),
    //    DOLLAR(KeyEvent.VK_DOLLAR),
    //    EURO_SIGN(KeyEvent.VK_EURO_SIGN),
    //    EXCLAMATION_MARK(KeyEvent.VK_EXCLAMATION_MARK),
    //    LESS_THAN(KeyEvent.VK_LESS),
    //    GREATER_THAN(KeyEvent.VK_GREATER),
    //    INVERTED_EXCLAMATION_MARK(KeyEvent.VK_INVERTED_EXCLAMATION_MARK),
    //    MULTIPLY(KeyEvent.VK_MULTIPLY),
    //    NUMBER_SIGN(KeyEvent.VK_NUMBER_SIGN),
    //    PLUS(KeyEvent.VK_PLUS, KeyEvent.VK_ADD),
    //    QUOTATION_MARK(KeyEvent.VK_QUOTEDBL),
    //    SEPARATOR(KeyEvent.VK_SEPARATOR),
    //    UNDERSCORE(KeyEvent.VK_UNDERSCORE),
    //    LEFT_CURLY_BRACKET(KeyEvent.VK_BRACELEFT),
    //    RIGHT_CURLY_BRACKET(KeyEvent.VK_BRACERIGHT),
    //    LEFT_PARENTHESIS(KeyEvent.VK_LEFT_PARENTHESIS),
    //    RIGHT_PARENTHESIS(KeyEvent.VK_RIGHT_PARENTHESIS),

    F1(KeyEvent.VK_F1),
    F2(KeyEvent.VK_F2),
    F3(KeyEvent.VK_F3),
    F4(KeyEvent.VK_F4),
    F5(KeyEvent.VK_F5),
    F6(KeyEvent.VK_F6),
    F7(KeyEvent.VK_F7),
    F8(KeyEvent.VK_F8),
    F9(KeyEvent.VK_F9),
    F10(KeyEvent.VK_F10),
    F11(KeyEvent.VK_F11),
    F12(KeyEvent.VK_F12),
    F13(KeyEvent.VK_F13),
    F14(KeyEvent.VK_F14),
    F15(KeyEvent.VK_F15),
    F16(KeyEvent.VK_F16),
    F17(KeyEvent.VK_F17),
    F18(KeyEvent.VK_F18),
    F19(KeyEvent.VK_F19),
    F20(KeyEvent.VK_F20),
    F21(KeyEvent.VK_F21),
    F22(KeyEvent.VK_F22),
    F23(KeyEvent.VK_F23),
    F24(KeyEvent.VK_F24),

    /**
     * A key that Java or comp127graphics does not recognize.
     */
    UNKNOWN();

    private final int[] awtKeyCodes;

    Key(int... awtKeyCodes) {
        this.awtKeyCodes = awtKeyCodes;
    }

    static Key fromAwtKeyCode(int awtKeyCode) {
        Key key = keysByAwtCode.get(awtKeyCode);
        return key != null ? key : UNKNOWN;
    }

    private static Map<Integer,Key> keysByAwtCode;
    static {
        Map<Integer,Key> keysByAwtCode = new HashMap<>();
        for (Key key : values()) {
            for (int awtKeyCode : key.awtKeyCodes) {
                if (keysByAwtCode.containsKey(awtKeyCode)) {
                    throw new AssertionError(
                        "Both " + key + " and " + keysByAwtCode.get(awtKeyCode)
                            + " claim the key code" + awtKeyCode);
                }
                keysByAwtCode.put(awtKeyCode, key);
            }
        }
        Key.keysByAwtCode = Collections.unmodifiableMap(keysByAwtCode);
    }
}
