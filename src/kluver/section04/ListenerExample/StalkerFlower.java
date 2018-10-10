package kluver.section04.ListenerExample;

import comp124graphics.CanvasWindow;
import kluver.section05.graphicsGroupExample.Flower;

public class StalkerFlower extends CanvasWindow {
    public StalkerFlower() {
        super("get this flower away from me!", 800, 600);

        Flower flower = new Flower(50, 50, 150);
        add(flower);

        StalkerListener sl = new StalkerListener(flower);
        super.addMouseMotionListener(sl);
    }

    public static void main(String[] args) {
        new StalkerFlower();
    }
}
