package kluver.section04.RandomShapeSolutionExample;

import comp124graphics.CanvasWindow;
import comp124graphics.FillColorable;
import comp124graphics.GraphicsObject;
import comp124graphics.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

/**
 * A program to draw random shapes in the window, keeping them in an array.
 * When a button is pressed, the shapes all appear to move.
 *
 * Created by shoop on 2/22/16.
 */
public class RandomShapesProgram extends CanvasWindow implements ActionListener {
    public static final int NUM_SHAPES = 16;
    private Random randGenColor;
    private Random randGenWidth;
    private Random randGenX;
    private Random randGenY;

    GraphicsObject[] graphicsObjects;


    public RandomShapesProgram() {
        super("Random Shapes", 1000, 800);
        randGenColor = new Random();
        randGenWidth = new Random();
        randGenX = new Random();
        randGenY = new Random();

        graphicsObjects = new GraphicsObject[NUM_SHAPES];

        JButton drawButton = new JButton("Redraw");
        drawButton.setSize(80, 40);

        add(drawButton);
        drawButton.addActionListener(this);

        run();
    }

    public void run() {
        createShapes();
        reDraw();
    }


    /**
     * Fills the shapes array with randomly generated shapes.
     * Add each one to the canvas.
     */
    private void createShapes() {
        for (int i = 0; i < graphicsObjects.length; i++) {
            graphicsObjects[i] = makeOneShape();
        }


    }

    /**
     * Redraws all of the objects in the shapes array on the screen.
     */
    private void reDraw() {
        removeAll();
        for (int i = 0; i < graphicsObjects.length; i++) {
            reDrawOne(graphicsObjects[i]);
        }

    }

    /**
     * Creates a square with a random width and color at a random position on the screen.
     * @return shape
     */
    private GraphicsObject makeOneShape() {
        Color color = new Color(randGenColor.nextFloat(), randGenColor.nextFloat(), randGenColor.nextFloat());
        double width = randGenWidth.nextDouble()*180+20;
        double x = randGenX.nextDouble()*925 + 10;
        double y = randGenY.nextDouble()*690 + 10;
        Rectangle rect = new Rectangle(x, y, width, width);
        rect.setFillColor(color);
        rect.setFilled(true);
        return rect;
    }

    /**
     * Randomly changes the size, position, and color of shape on the screen.
     * @param shape to modify
     */
    private void reDrawOne(GraphicsObject shape) {
        Color color = new Color(randGenColor.nextFloat(), randGenColor.nextFloat(), randGenColor.nextFloat());
        double width = randGenWidth.nextDouble()*180+20;
        double x = randGenX.nextDouble()*925 + 10;
        double y = randGenY.nextDouble()*690 + 10;
        Rectangle rect = (Rectangle) shape;
        rect.setFillColor(color);
        rect.setWidthAndHeight(width, width);
        rect.setPosition(x, y);
        add(rect);
    }

    /**
     * Respond to button clicks
     * @param e event that contains data about which button was clicked.
     */
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("Redraw")) {
            reDraw();
        }
    }

    public static void main(String[] args){
        RandomShapesProgram prog = new RandomShapesProgram();
    }

}
