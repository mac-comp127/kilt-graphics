package kluver.section04.graphicsGroupExample;

import comp124graphics.CanvasWindow;
import comp124graphics.Triangle;

import java.awt.*;
import java.awt.event.WindowEvent;

public class Animation {
    public static void main(String[] args) {
        CanvasWindow canvas = new CanvasWindow("Animation", 800, 600);
        Triangle thingToGlide = new Triangle(50,100, 0,150,100,200);
        thingToGlide.setFillColor(Color.RED);
        thingToGlide.setFilled(true);

        canvas.add(thingToGlide);

        while(thingToGlide.getX()<=canvas.getWidth()) {
            thingToGlide.move(1,0);
            canvas.pause(1);
        }
        canvas.closeWindow();

    }

}
