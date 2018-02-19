package jackson.section2.eventExamples;

import comp124graphics.CanvasWindow;
import comp124graphics.Line;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class RubberLines extends CanvasWindow {

    private Line line;

    //-----------------------------------------------------------------
    //  Constructor: Sets up this panel to listen for mouse events.
    //-----------------------------------------------------------------
    public RubberLines() {
        super("Rubber Lines", 800, 800);
        LineListener listener = new LineListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);

        setBackground(Color.black);

        line = new Line(0, 0, 0, 0);
        line.setStrokeWidth(2);
        line.setStrokeColor(Color.yellow);
        add(line);

    }

    //*****************************************************************
    //  Represents the listener for all mouse events.
    //*****************************************************************
    private class LineListener implements MouseListener, MouseMotionListener {
        //--------------------------------------------------------------
        //  Captures the initial position at which the mouse button is
        //  pressed.
        //--------------------------------------------------------------
        public void mousePressed(MouseEvent event) {
            Point pt = event.getPoint();
            line.setStartPosition(pt.x, pt.y);
            line.setEndPosition(pt.x, pt.y);
        }

        //--------------------------------------------------------------
        //  Gets the current position of the mouse as it is dragged and
        //  redraws the line to create the rubberband effect.
        //--------------------------------------------------------------
        public void mouseDragged(MouseEvent event) {
            Point pt = event.getPoint();
            line.setEndPosition(pt.x, pt.y);
        }

        //--------------------------------------------------------------
        //  Provide empty definitions for unused event methods.
        //--------------------------------------------------------------
        public void mouseClicked(MouseEvent event) {
        }

        public void mouseReleased(MouseEvent event) {
        }

        public void mouseEntered(MouseEvent event) {
        }

        public void mouseExited(MouseEvent event) {
        }

        public void mouseMoved(MouseEvent event) {
        }
    }

    public static void main(String[] args){
        RubberLines window = new RubberLines();
    }

}
