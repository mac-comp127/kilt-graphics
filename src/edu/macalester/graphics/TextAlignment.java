package edu.macalester.graphics;

/**
 * Describes how {@link GraphicsText} horizontally aligns text relative to its x position.
 * 
 * @see GraphicsText#setAlignment(TextAlignment)
 */
public enum TextAlignment {
    /**
     * The x coordinate of the {@link GraphicsText} is the left edge of each line of text.
     */
    LEFT(0),

    /**
     * The x coordinate of the {@link GraphicsText} is the center of each line of text.
     */
    CENTER(0.5),

    /**
     * The x coordinate of the {@link GraphicsText} is the right edge of each line of text.
     * In order words, text extends left of the x position.
     */
    RIGHT(1);

    private final double factor;

    private TextAlignment(double factor) {
        this.factor = factor;
    }

    // Subtract width of text times this factor to align
    double getFactor() {
        return factor;
    }
}
