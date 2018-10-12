package kluver.section05;

import comp124graphics.CanvasWindow;
import comp124graphics.Rectangle;

import java.awt.*;

public class IdleLoop extends CanvasWindow {
    private static final Color ON_COLOR = Color.CYAN;
    private static final Color OFF_COLOR = Color.darkGray;
    private static final int SPEED = 150;

    private Rectangle r0;
    private Rectangle r1;
    private Rectangle r2;
    private Rectangle r3;
    private Rectangle r4;
    private Rectangle r5;
    private Rectangle r6;
    private Rectangle r7;

    public IdleLoop() {
        super("waiting", 820, 100);
    }

    private void init() {
        r0 = new Rectangle(10, 10, 80, 80);
        r1 = new Rectangle(100, 10, 80, 80);
        r2 = new Rectangle(190, 10, 80, 80);
        r3 = new Rectangle(280, 10, 80, 80);
        r4 = new Rectangle(370, 10, 80, 80);
        r5 = new Rectangle(460, 10, 80, 80);
        r6 = new Rectangle(550, 10, 80, 80);
        r7 = new Rectangle(640, 10, 80, 80);

        r0.setFilled(true);
        r1.setFilled(true);
        r2.setFilled(true);
        r3.setFilled(true);
        r4.setFilled(true);
        r5.setFilled(true);
        r6.setFilled(true);
        r7.setFilled(true);

        r0.setFillColor(OFF_COLOR);
        r1.setFillColor(OFF_COLOR);
        r2.setFillColor(OFF_COLOR);
        r3.setFillColor(OFF_COLOR);
        r4.setFillColor(OFF_COLOR);
        r5.setFillColor(OFF_COLOR);
        r6.setFillColor(OFF_COLOR);
        r7.setFillColor(OFF_COLOR);

        add(r0);
        add(r1);
        add(r2);
        add(r3);
        add(r4);
        add(r5);
        add(r6);
        add(r7);
    }

    public void run() {
        while (true) {
            r0.setFillColor(ON_COLOR);
            pause(SPEED);
            r0.setFillColor(OFF_COLOR);

            r1.setFillColor(ON_COLOR);
            pause(SPEED);
            r1.setFillColor(OFF_COLOR);

            r2.setFillColor(ON_COLOR);
            pause(SPEED);
            r2.setFillColor(OFF_COLOR);

            r3.setFillColor(ON_COLOR);
            pause(SPEED);
            r3.setFillColor(OFF_COLOR);

            r4.setFillColor(ON_COLOR);
            pause(SPEED);
            r4.setFillColor(OFF_COLOR);

            r5.setFillColor(ON_COLOR);
            pause(SPEED);
            r5.setFillColor(OFF_COLOR);

            r6.setFillColor(ON_COLOR);
            pause(SPEED);
            r6.setFillColor(OFF_COLOR);

            r7.setFillColor(ON_COLOR);
            pause(SPEED);
            r7.setFillColor(OFF_COLOR);

        }
    }

    public static void main(String[] args) {
        IdleLoop loop = new IdleLoop();
        loop.init();
        loop.run();
    }
}
