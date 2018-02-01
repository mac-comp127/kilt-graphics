package activityStarterCode.BasicClasses;

import comp124graphics.CanvasWindow;
import comp124graphics.Line;

import java.awt.*;

/**
 * A program that draws a random ink-blot using RandomWalk objects.
 */
public class InkBlot {
    private CanvasWindow canvas;

    /**
     * Main method for the InkBlot program.
     * Currently it just generates a new inkblot.
     */
    public static void main(String[] args){
        InkBlot ib = new InkBlot();

        ib.generateInkBlot();
    }

    /**
     * Constructor for the inkblot class.
     * This constructor creates a new canvas window that the other methds can
     * draw on.
     */
    public InkBlot() {
        canvas = new CanvasWindow("InkBlot", 300, 300);
    }

    /**
     * Draws a new inkblot on the canvas.
     * This is done with 5 RandomWalk objects, three for the three color
     * components (red, green, blue)
     */
    public void generateInkBlot() {
        // TODO: fill in this method according to the activity instructions.
    }

    /**
     * Turns three random walk objects into a Color.
     * If the random walks do not encode a valid color (the int value is bigger
     * than 255 or small than 0) it will return dark grey.
     * @param red
     * @param green
     * @param blue
     * @return a Color object.
     */
    private Color getColor(RandomWalk red, RandomWalk green, RandomWalk blue) {
        int r = red.advanceValue();
        int g = green.advanceValue();
        int b = blue.advanceValue();
        if(r >= 0 && r < 256 && g>=0 && g<256 && b>=0 && b<256) {
            return new Color(r,g,b);
        } else {
            return Color.darkGray;
        }
    }

    /**
     * Clears the inkblots canvas allowing for a new inkblot to be drawn.
     */
    public void ClearScreen() {
        canvas.removeAll();
    }

    /**
     * Saves the inkblot to an image file.
     *
     * Note, the file type will be a "png" (portable network graphic) image.
     * Therefore it is recommended your filename ends with ".png"
     *
     * Files will be written in your comp124-s18 directory (the same directory
     * that new modules check-out to).
     * @param fileName - the name of the file to write to.
     */
    public void saveInkBlot(String fileName) {
        canvas.screenShot(fileName);
    }

    /**
     * Advance the x and y random walks, getting the old and new positions.
     * Returns a line connecting the old position and new position of the random
     * walk objects.
     * @param xWalk - the RandomWalk indicating the xPosition.
     * @param yWalk - the RandomWalk indicating the yPosition.
     * @return a Line from the old to new position of the random walk.
     */
    private Line advance(RandomWalk xWalk, RandomWalk yWalk) {
        int oldX = xWalk.getValue();
        int oldY = yWalk.getValue();
        int newX = xWalk.advanceValue();
        int newY = yWalk.advanceValue();
        Line l = new Line(oldX, oldY, newX, newY);
        l.setStrokeWidth(3);
        return l;
    }

    /**
     * Mirror a line about the middle line of the canvas.
     * @param l - the line to mirror
     * @return The new line, which will be a mirrored copy of the input line l.
     */
    private Line mirror(Line l) {
        Line l2 = new Line(canvas.getWidth()-l.getX1(), l.getY1(), canvas.getWidth()-l.getX2(), l.getY2());
        l2.setStrokeWidth(l.getStrokeWidth());
        l2.setStrokeColor(l.getStrokeColor());
        return l2;
    }



}
