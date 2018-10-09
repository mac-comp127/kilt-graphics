package kluver.section04.graphicsGroupExample;

import comp124graphics.CanvasWindow;
import comp124graphics.GraphicsGroup;
import comp124graphics.Triangle;
import kluver.section05.graphicsGroupExample.Flower;

import java.awt.*;

public class Animation {
    public static void main(String[] args) {
        CanvasWindow canvas = new CanvasWindow("Animation", 800, 633);

        int dx = 1;
        int dy = 1;

        Flower flower = new Flower(0,0,400);
        canvas.add(flower);
        while(true) {
            if (flower.getX()+flower.getWidth()>canvas.getWidth() || flower.getX()<0) {
                dx = -dx;
            }
            if (flower.getY()+flower.getHeight()>canvas.getHeight() || flower.getY()<0) {
                dy = -dy;
            }
            flower.move(dx, dy);
            canvas.pause(10);
        }

    }

}












