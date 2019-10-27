package comp127graphics;

import java.awt.Paint;

/**
 * A graphical object that has an outline, which can have a color and thickness.
 *
 * @author Bret Jackson
 */
public interface Strokable {

    /**
     * Returns the color to strokeColor that will be used to stroke this shape if it is
     * stroked. Note that there will still be no stroke if isStroked() is false or
     * the stroke width is zero.
     */
    Paint getStrokeColor();

    /**
     * Sets the stroke color to the given color. Automatically calls setStroked(true).
     */
    void setStrokeColor(Paint strokeColor);

    /**
     * Returns true if the interior of this shape will be stroked with the stroke color.
     */
    boolean isStroked();

    /**
     * Causes the shape to be stroked with the stroke color when it is drawn.
     */
    void setStroked(boolean stroked);

    /**
     * Returns the thickness of the stroke in pixels.
     */
    double getStrokeWidth();

    /**
     * Sets how thick the stroke is. Automatically calls setStroked(true).
     */
    void setStrokeWidth(double width);
}
