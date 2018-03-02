package kluver.examples;

import comp124graphics.CanvasWindow;
import comp124graphics.Ellipse;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerPractice extends CanvasWindow implements KeyListener {

    public static void main(String[] args) {
        new KeyListenerPractice();
    }

    Ellipse sonic;

    public KeyListenerPractice() {
        super("Sonic Run!", 1000, 1000);

        sonic = new Ellipse(50, 50, 50, 50);
        sonic.setFilled(true);
        sonic.setFillColor(Color.BLUE);
        add(sonic);
        addKeyListener(this);

        setFocusable(true);

    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println(e+"!");
        if(e.getKeyChar() == 'w') {
            sonic.move(0, -10);
        } else if (e.getKeyChar() == 's') {
            sonic.move(0, 10);
        } else if (e.getKeyChar() == 'a') {
            sonic.move(-10, 0);
        } else if (e.getKeyChar() == 'd') {
            sonic.move(10, 0);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
