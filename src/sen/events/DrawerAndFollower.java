package sen.events;

import comp124graphics.CanvasWindow;
import comp124graphics.Ellipse;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class DrawerAndFollower extends CanvasWindow implements MouseMotionListener {
    public DrawerAndFollower() {
        super("Drawer and Follower", 800, 800);
        Ellipse ellipse = new Ellipse(400, 400, 200, 200);
        ellipse.setFilled(true);
        ellipse.setFillColor(Color.GREEN);
        add(ellipse);

        this.addMouseMotionListener(this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        Ellipse ellipse = new Ellipse(e.getX(), e.getY(), 3, 3);
        ellipse.setFilled(true);
        ellipse.setFillColor(Color.RED);
        add(ellipse);
    }
}
