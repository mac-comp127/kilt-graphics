package cantrell.classExamples.snakeActivity;

import java.awt.Color;
import java.util.Random;

import comp124graphics.CanvasWindow;
import comp124graphics.Ellipse;
import comp124graphics.GraphicsObject;
import comp124graphics.Rectangle;

import static com.sun.tools.internal.xjc.reader.Ring.add;

public class Snake {
    public static final double SEGMENT_SIZE = 50;

    private GraphicsObject[] segments;
    private Random random;

    public Snake(int numberOfSegments) {
        random = new Random();

        segments = new GraphicsObject[numberOfSegments];

        generateSegments();
    }

    /**
     * This method should fill in the array storing the graphical objects of the snake
     * (Note: the array should not be created in this method).
     *
     * For now this should just get random shapes, however, once you get everything working
     * Feel free to customize this to, for example, have a specific snake head and tail.
     */
    private void generateSegments() {
        for(int n = 0; n < segments.length; n++) {
            segments[n] = getRandomShape();
        }
    }

    public void addToCanvas(CanvasWindow canvas) {
        for(GraphicsObject segment : segments) {
            canvas.add(segment);
        }
    }

    /**
     * Create and return a random small graphics object.
     * NOTE: this includes some special shapes (subclasses of graphics group) that are from kluver's section.
     *
     * The new shape will always be in the top left corner.
     *
     */
    private GraphicsObject getRandomShape() {
        int r = random.nextInt(5);
        if (r == 0) {
            Rectangle rectangle = new Rectangle(0, 0, SEGMENT_SIZE, SEGMENT_SIZE);
            rectangle.setFilled(true);
            rectangle.setFillColor(getRandomColor());
            return rectangle;
        } else { // r == 1
            Ellipse ellipse = new Ellipse(0, 0, SEGMENT_SIZE, SEGMENT_SIZE);
            ellipse.setFilled(true);
            ellipse.setFillColor(getRandomColor());
            return ellipse;
        }
    }

    private Color getRandomColor() {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    /**
     * Move the snake. The specified position will become the new position of the first segment.
     * The second segment
     */
    public void move(double x, double y) {

        segments[0].setPosition(x, y);
        for(int i = segments.length - 1; i > 0; i--) {
            segments[i].setPosition(
                segments[i-1].getPosition().getX(),
                segments[i-1].getPosition().getY());
        }

        // Another implementation that works:

//        segments[0].setPosition(x, y);
//
//        double nextX = x, nextY = y;
//
//        for(int i = 1; i < segments.length; i++) {
//            double prevX = segments[i].getPosition().getX();
//            double prevY = segments[i].getPosition().getY();
//
//            segments[i].setPosition(nextX, nextY);
//
//            nextX = prevX;
//            nextY = prevY;
//        }

    }
}
