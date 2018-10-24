package cantrell.classExamples.snakeActivity;

import java.awt.Color;
import java.util.Random;

import comp124graphics.CanvasWindow;
import comp124graphics.Ellipse;
import comp124graphics.GraphicsObject;
import comp124graphics.Rectangle;

public class CircleShapeMaker implements ShapeSource {

    private Random random = new Random();

    /**
     * Create and return a random small graphics object.
     * NOTE: this includes some special shapes (subclasses of graphics group) that are from kluver's section.
     *
     * The new shape will always be in the top left corner.
     *
     */
    public GraphicsObject makeSegment() {
        Ellipse ellipse = new Ellipse(0, 0, Snake.SEGMENT_SIZE, Snake.SEGMENT_SIZE);
        ellipse.setFilled(true);
        ellipse.setFillColor(ShapeSource.getRandomColor(random));
        return ellipse;
    }
}
