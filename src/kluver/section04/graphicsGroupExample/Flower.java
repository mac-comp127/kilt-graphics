package kluver.section04.graphicsGroupExample;

import comp124graphics.CanvasWindow;
import comp124graphics.Ellipse;
import comp124graphics.GraphicsGroup;

import java.awt.*;

public class Flower extends GraphicsGroup {

    private static final int PEDALS = 18;
    private static final Color FLOWER_COLOR = new Color(208, 59, 255, 35);

    /**
     * Creates a Flower at position x,y (top,left) with diameter equal to the
     * size parameter.
     * @param x - position of the left edge of the flower shape
     * @param y - position of the top of the flower shape
     * @param size - diameter of the  flower shape
     */
    public Flower (double x, double y, double size) {
        super(x, y);

        for (int i = 0; i<PEDALS; i++) {
            double deg = Math.toRadians(360.0*i/PEDALS);
            double centerX = size/4.0*Math.sin(deg);
            double centerY = size/4.0*Math.cos(deg);
            double leftX = centerX+size/4.0;
            double topY = centerY+size/4.0;
            Ellipse pedal = new Ellipse(leftX, topY, size/2.0, size/2.0);
            pedal.setFilled(true);
            pedal.setFillColor(FLOWER_COLOR);
            pedal.setStroked(false);
            add(pedal);
        }
    }
}
