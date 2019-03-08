package sen.events;

import comp124graphics.CanvasWindow;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseFollower implements MouseMotionListener {
    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("mouse was moved to " + e.getPoint());
    }
}
