package comp127graphics;

import java.awt.Font;

/**
 * A typography style. See GraphicsText.setFont().
 */
public enum FontStyle {
    PLAIN(Font.PLAIN),
    BOLD(Font.BOLD),
    ITALIC(Font.ITALIC),
    BOLD_ITALIC(Font.BOLD | Font.ITALIC);

    private int awtCode;

    FontStyle(int awtCode) {
        this.awtCode = awtCode;
    }

    int getAwtCode() {
        return awtCode;
    }
}
