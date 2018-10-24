package cantrell.classExamples.snakeActivity;

import java.awt.Color;
import java.util.Random;

import comp124graphics.GraphicsObject;

public interface ShapeSource {

    GraphicsObject makeSegment();

    static Color getRandomColor(Random random) {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }
}
