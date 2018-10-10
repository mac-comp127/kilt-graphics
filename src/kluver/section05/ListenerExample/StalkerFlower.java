package kluver.section05.ListenerExample;

import comp124graphics.CanvasWindow;
import kluver.section05.graphicsGroupExample.Flower;

import java.awt.event.MouseMotionListener;

public class StalkerFlower extends CanvasWindow {
    public StalkerFlower() {
        super("Hey get this flower away from me dood", 800, 600);

        Flower flower = new Flower(50, 50, 150);
        add(flower);

        StalkerListener sl = new StalkerListener(flower);
        addMouseMotionListener(sl);
    }

    public static void main(String[] args) {
        new StalkerFlower();
    }
}
