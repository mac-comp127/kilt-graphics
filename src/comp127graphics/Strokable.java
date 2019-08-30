package comp127graphics;

import java.awt.*;

/**
 * Colorable interface. Classes implementing this interface have the ability to be colored with separate outline and stroke
 * colors.
 *
 * Created by bjackson on 9/12/17.
 * @version 0.1
 */
public interface Strokable {

    /**
     * Returns the color to strokeColor that will be used to stroke this shape if it is
     * stroked. Note that there will still be no stroke if isStroked() is false or
     * the stroke width is zero.
     */
    Paint getStrokeColor();

    /**
     * Sets the stroke color to the given color. Automatically calls setStrokeed(true).
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
    float getStrokeWidth();

    /**
     * Sets how thick the stroke is.
     */
    void setStrokeWidth(float width);
}
