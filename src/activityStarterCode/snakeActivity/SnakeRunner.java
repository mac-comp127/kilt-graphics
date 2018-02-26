package activityStarterCode.snakeActivity;

/**
 * A class with a main method for running snakes.
 * Feel free to modify this code to run other subclasses of snakeWindow
 * as you have time to create subclasses.
 */
public class SnakeRunner {
    public static void main(String[] args) {
        SnakeWindow sw = new SnakeWindow(800,600, 10);
        sw.run();
    }
}
