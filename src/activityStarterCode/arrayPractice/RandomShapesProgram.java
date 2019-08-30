package activityStarterCode.arrayPractice;

import comp127graphics.CanvasWindow;
import comp127graphics.GraphicsObject;
import comp127graphics.Rectangle;

import java.awt.*;
import java.util.Random;

/**
 * A program to draw random shapes in the window, keeping them in an array.
 * When a button is pressed, the shapes all appear to move.
 *
 * Created by shoop on 2/22/16.
 */
public class RandomShapesProgram extends CanvasWindow {
    public static final int NUM_SHAPES = 16;
    private Random randGenColor;
    private Random randGenWidth;
    private Random randGenX;
    private Random randGenY;

    // TODO:
    //    Declare an array of GraphicsObject references that will be of length NUM_SHAPES.
    //    Call the variable name of the array 'shapes'.
    //    You do not make all the new shapes here, just declare the array



    public RandomShapesProgram() {
        super("Random Shapes", 1000, 800);
        randGenColor = new Random();
        randGenWidth = new Random();
        randGenX = new Random();
        randGenY = new Random();

        // TODO: inntialize the array called shapes of length NUM_SHAPES here.
        // You still do not make all the new shapes yet


        onClick((event) -> reDraw());

        run();
    }

    public void run() {
        createShapes();
        //reDraw();  // can do this only when button is pushed.
    }


    /**
     * Fills the shapes array with randomly generated shapes.
     * Add each one to the canvas.
     */
    private void createShapes() {
        // TODO:
        //    Fill the array called shapes with a GraphicsObject of your choice,
        //    using the makeOneShape method. Add each to this CanvasWindow.


    }

    /**
     * Redraws all of the objects in the shapes array on the screen.
     */
    private void reDraw() {
        removeAll();
        // TODO:
        //  redraw each GraphicsObject in the array
        //

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
        comp127graphics.Rectangle rect = (Rectangle) shape;
        rect.setFillColor(color);
        rect.setSize(width, width);
        rect.setPosition(x, y);
        add(rect);
    }

    public static void main(String[] args){
        RandomShapesProgram prog = new RandomShapesProgram();
    }
}
