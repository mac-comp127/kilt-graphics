package comp127graphics;

import java.awt.Paint;

/**
 * A graphical object with an interior than can be filled with a color or pattern.
 *
 * @author Bret Jackson
 */
public interface Fillable {

    /**
     * Returns the color to fillColor that will be used to fill this shape if it is
     * filled. Note that there will still be no fill if isFilled() is false.
     */
    Paint getFillColor();

    /**
     * Sets the fill color to the given color. Automatically calls setFilled(true).
     */
    void setFillColor(Paint fillColor);

    /**
     * Returns true if the interior of this shape will be filled with the fill color.
     */
    boolean isFilled();

    /**
     * Causes the shape to be filled with the fill color when it is drawn.
     */
    void setFilled(boolean filled);
}
