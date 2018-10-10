package kluver.section05.ListenerExample;

import kluver.section05.graphicsGroupExample.Flower;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class StalkerListener implements MouseMotionListener {
    private Flower theFlower;

    public StalkerListener(Flower theFlower) {
        this.theFlower = theFlower;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        theFlower.setPosition(x-theFlower.getWidth()/2,y-theFlower.getHeight()/2);
    }
}
