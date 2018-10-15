package kluver.section05;

import comp124graphics.CanvasWindow;
import comp124graphics.FillColorable;
import comp124graphics.GraphicsObject;
import comp124graphics.Rectangle;

import java.awt.*;

public class IdleLoop extends CanvasWindow {
    private static final Color ON_COLOR = Color.RED;
    private static final Color OFF_COLOR = Color.darkGray;
    private static final int SPEED = 150;

    private Rectangle[] rectangles;


    public IdleLoop() {
        super("waiting", 1270, 100);
        rectangles = new Rectangle[28];

    }

    private void init() {
        for(int i = 0; i<rectangles.length; i++) {
            rectangles[i] = new Rectangle(10+i*45, 10, 40, 40);
            rectangles[i].setFilled(true);
            rectangles[i].setFillColor(OFF_COLOR);
            add(rectangles[i]);
        }
    }

    public void run() {
        while (true) {
            for (int i = 0; i < rectangles.length; i++) {
                rectangles[i].setFillColor(ON_COLOR);
                pause(SPEED);
                rectangles[i].setFillColor(OFF_COLOR);
            }
        }
    }

    public static void main(String[] args) {
        IdleLoop loop = new IdleLoop();
        loop.init();
        loop.run();
    }
}
