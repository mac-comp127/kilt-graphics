package comp124graphics;

import java.awt.*;

public interface FillColorable {

    /**
     * Set the fill color to fillColor
     * @param fillColor Color to fill the shape
     */
    void setFillColor(Paint fillColor);

    /**
     * Gets the fill color to fillColor
     * @return  Color used to fill the shape
     */
    Paint getFillColor();
}
