package kluver.section05.graphicsGroupExample;

import comp124graphics.CanvasWindow;
import comp124graphics.GraphicsGroup;
import comp124graphics.Triangle;

import java.awt.*;

public class Animation {
    public static void main(String[] args) {
        CanvasWindow canvas = new CanvasWindow("Animation", 800, 600);

        Flower flower = new Flower(0, 100, 400);

        canvas.add(flower);

        while(true) {
            flower.move(1,0); // change what we show
            canvas.pause(10);
            if(flower.getX()>canvas.getWidth()) {
                flower.setPosition(-flower.getWidth(), flower.getY());
            }
        }

    }

}
