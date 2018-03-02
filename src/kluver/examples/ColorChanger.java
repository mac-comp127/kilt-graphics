package kluver.examples;

import comp124graphics.*;
import comp124graphics.Rectangle;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ColorChanger extends CanvasWindow implements MouseListener {
    public ColorChanger() {
        super("color changer", 1000, 1000);

        Ellipse e = new Ellipse(100,100,100,100);
        Rectangle r = new Rectangle(200,200,200,200);
        e.setFilled(true);
        r.setFilled(true);
        this.add(e);
        this.add(r);

        this.addMouseListener(this);
    }

    public static void main(String[] args) {
        new ColorChanger();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        GraphicsObject clicked = this.getElementAt(e.getX(), e.getY());
        if(clicked != null) {
            if(clicked instanceof FillColorable) {
                FillColorable fillColorable = (FillColorable) clicked;
                ((FillColorable) clicked).setFillColor(Color.red);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
