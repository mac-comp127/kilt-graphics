package turtleGraphics.cs124;

import java.awt.Color;

import turtleGraphics.turtle.Turtle;
import turtleGraphics.turtle.TurtleProgram;

public class TurtleExercise extends TurtleProgram {

    public static void main(String[] args) {
        new TurtleExercise();
    }

    /**
     * For this in-class demo:
     *
     * 1. Set a breakpoint where sally is created and run in debugger.
     *
     *    Watch turtle objects respond to each message in turn.
     *
     * 2. Reorder lines (cmd-opt-up/down) so that fred is instantiated
     *    and added right after sally, and then their lines are
     *    interspersed — but each turtle's lines are still in their
     *    original order.
     *
     *    Discuss: Will it change the result?
     *
     *    Watch in the debugger.
     *
     * 3. After both turtles are added but before any draw commands, add:
     *
     *      fred = sally;
     *
     *    Discuss: What might happen? Brainstorm possible outcomes. (I can
     *    think of at least 5 reasonable-for-a-beginner guesses.) Discuss
     *    what each possible outcome would mean.
     *
     *    Take a class vote!
     *
     *    Moment of truth: watch in the debugger.
     *
     * 4. Bonus exercise: port this lib to Swift, make Turtle a struct
     *    instead of a class, and try #3 again.
     */
    public void run() {
        setTurtleSpeedFactor(1000);
        
        Turtle sally = new Turtle(200, 400, Color.MAGENTA);
        add(sally);
        
        sally.forward(2);
        sally.left(90);
        sally.forward(3);

        sally.left(45);
        sally.setColor(Color.BLUE);
        sally.forward(Math.sqrt(8));
        sally.left(90);
        sally.forward(Math.sqrt(8));
        sally.setColor(Color.MAGENTA);
        sally.left(45);
        sally.forward(3);
        sally.left(90);
        sally.forward(2);


        Turtle fred = new Turtle(200, 500, Color.GREEN);
        add(fred);

        fred.forward(2.5);
        fred.backward(0.5);
        fred.right(120);
        fred.forward(2.5);
        fred.backward(0.5);
        fred.right(120);
        fred.forward(2.5);
        fred.penUp();
        fred.forward(1);
        fred.penDown();
    }
}
