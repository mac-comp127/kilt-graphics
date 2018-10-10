package kluver.section05.graphicsGroupExample;

import comp124graphics.Ellipse;
import comp124graphics.GraphicsGroup;

import java.awt.*;
import java.util.Random;

public class Flower extends GraphicsGroup {

    private static final int PEDALS = 10;
    private static final Color FLOWER_COLOR = new Color(208, 59, 255, 20);
    private static final double SD = 30;

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
            pedal.setFillColor(makeColor(SD));
            pedal.setStroked(false);
            add(pedal);
        }
    }

    private Color makeColor(double sd) {
        Random rng = new Random();
        int r = colorClamp(FLOWER_COLOR.getRed()+rng.nextGaussian()*sd);
        int g = colorClamp(FLOWER_COLOR.getGreen()+rng.nextGaussian()*sd);
        int b = colorClamp(FLOWER_COLOR.getBlue()+rng.nextGaussian()*sd);
        int a = FLOWER_COLOR.getAlpha();
        return new Color(r,g,b,a);
    }

    private int colorClamp(double v) {
        if (v<0) {
            return 0;
        } else if (v >255) {
            return 255;
        } else {
            return (int) v;
        }
    }
}
