package cantrell.classExamples.snakeActivity;

import comp124graphics.CanvasWindow;

/**
 * A class for a simple animation of a "snake".
 * The snake will be created by having a series of shapes that follow each other.
 * You will implement the code related to tracking and moving this snake.
 */
public class SnakeWindow extends CanvasWindow {

    private Snake snake;

    /**
     * Create a new snake window
     * @param width - the width of the window
     * @param height - the height of the window
     * @param numberOfSnakeSegments - the number of segments for snake.
     */
    public SnakeWindow(int width, int height, int numberOfSnakeSegments) {
        super("Snake Window", width, height);

        snake = new Snake(numberOfSnakeSegments);
        snake.addToCanvas(this);
    }

    /**
     * This method makes the snake animate.
     * Once you finish making the snake work you can override this method to make the
     * snake do more interesting things.
     * Right now it just kinda slithers around in a circle.
     */
    public void run() {
        int i = 0;
        double step = 0.15;
        double degree = 0;
        double r = Math.min(this.getWidth()/2, this.getHeight()/2);
        r = r - Snake.SEGMENT_SIZE;

        while(true) {
            // infinite animation loop!
            // incerase the angle for the movement
            degree += step;

            // make the radius change as the snake moves
            double thisR = r+Math.sin(8*degree)*Snake.SEGMENT_SIZE;

            // compute x and y values
            double x = Math.sin(degree)*thisR - Snake.SEGMENT_SIZE/2 + getWidth()/2;
            double y = Math.cos(degree)*thisR - Snake.SEGMENT_SIZE/2 + getHeight()/2;

            // move the head of the snake to this position, and have each segment follow.
            snake.move(x, y);
            pause(100);

        }

    }
}
