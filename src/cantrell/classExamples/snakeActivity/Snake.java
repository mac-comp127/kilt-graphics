package cantrell.classExamples.snakeActivity;

import comp124graphics.CanvasWindow;
import comp124graphics.GraphicsObject;

public class Snake {
    public static final double SEGMENT_SIZE = 30;

    private GraphicsObject[] segments;
    private ShapeSource segmentShapes;

    public Snake(int numberOfSegments, ShapeSource segmentShapes) {
        this.segmentShapes = segmentShapes;

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
            segments[n] = segmentShapes.makeSegment();
        }
    }

    public void addToCanvas(CanvasWindow canvas) {
        for(GraphicsObject segment : segments) {
            canvas.add(segment);
        }
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
    }
}
