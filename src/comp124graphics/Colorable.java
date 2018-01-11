package comp124graphics;

import java.awt.*;

/**
 * Colorable interface. Classes implementing this interface have the ability to be colored with separate outline and fill
 * colors.
 *
 * Created by bjackson on 9/12/17.
 * @version 0.1
 */
public interface Colorable {

    /**
     * Set the stroke outline color for the shape
     * @param strokeColor the outline color.
     */
    void setStrokeColor(Paint strokeColor);

    /**
     * Getter for the stroke color
     * @return the outline color of the shape
     */
    Paint getStrokeColor();
}
