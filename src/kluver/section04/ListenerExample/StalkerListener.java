package kluver.section04.ListenerExample;

import kluver.section05.graphicsGroupExample.Flower;

import java.awt.event.*;
import javax.swing.Timer;

public class StalkerListener implements MouseMotionListener, ActionListener {
    private Flower theFlower;
    private int x, y;
    private double velocity=5;

    public StalkerListener(Flower theFlower) {
        this.theFlower = theFlower;

        new Timer(10, this).start();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double cx = theFlower.getX() + theFlower.getWidth()/2;
        double cy = theFlower.getY() + theFlower.getWidth()/2;
        double mx = x;
        double my = y;
        double dx = mx-cx;
        double dy = my-cy;
        double h = Math.hypot(dx,dy);
        if (h>velocity) {
            dx = dx/h*velocity;
            dy = dy/h*velocity;
        } else if (h<=1) {
            theFlower.removeAll();
        }
        theFlower.move(dx,dy);
    }
}
