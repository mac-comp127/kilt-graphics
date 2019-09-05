package activityStarterCode.train;

import comp127graphics.CanvasWindow;
import comp127graphics.Ellipse;
import comp127graphics.Line;
import comp127graphics.Rectangle;

import java.awt.*;


/*
 * File: TrainDrawer.java
 * --------------------
 * This program draws a three-car train consisting on an engine,
 * a boxcar, and a caboose.  This implementation of the program,
 * however, is incomplete, and contains only the methods given
 * in the text.
 */

public class TrainDrawer {

    public static final int SCALE = 1;
    
	/* Private constants */

    /* Dimensions of the frame of a train car */
    public static final double CAR_WIDTH = 75*SCALE;
    public static final double CAR_HEIGHT = 36*SCALE;

    /* Distance from the bottom of a train car to the track below it */
    public static final double CAR_BASELINE = 10*SCALE;

    /* Width of the connector, which overlaps between successive cars */
    public static final double CONNECTOR = 6*SCALE;

    /* Radius of the wheels on each car */
    public static final double WHEEL_RADIUS = 8*SCALE;

    /* Distance from the edge of the frame to the center of the wheel */
    public static final double WHEEL_INSET = 16*SCALE;

    /* Dimensions of the cab on the engine */
    public static final double CAB_WIDTH = 35*SCALE;
    public static final double CAB_HEIGHT = 8*SCALE;

    /* Dimensions of the smokestack and its distance from the front */
    public static final double SMOKESTACK_WIDTH = 8*SCALE;
    public static final double SMOKESTACK_HEIGHT = 8*SCALE;
    public static final double SMOKESTACK_INSET = 8*SCALE;

    /* Dimensions of the door panels on the boxcar */
    public static final double DOOR_WIDTH = 18*SCALE;
    public static final double DOOR_HEIGHT = 32*SCALE;

    /* Dimensions of the cupola (the rectangular piece on the top of the caboose) */
    public static final double CUPOLA_WIDTH = 35*SCALE;
    public static final double CUPOLA_HEIGHT = 8*SCALE;

    // Instance variable for the canvas that you can add shapes to in order to have them be drawn.
    private CanvasWindow canvas;


    public static void main(String[] args) {
        TrainDrawer drawer = new TrainDrawer();
    }

    public TrainDrawer(){
        //Create a window and canvas to draw on
        canvas = new CanvasWindow("Train", 800, 500);

        double trainWidth = 3 * CAR_WIDTH + 4 * CONNECTOR;
        double x = (canvas.getWidth() - trainWidth) / 2;
        double y = canvas.getHeight();
        double dx = CAR_WIDTH + CONNECTOR;
        drawEngine(x, y);
        drawBoxcar(x + dx, y, Color.GREEN);
        drawCaboose(x + 2 * dx, y);
    }

    /**
     * Draws an engine whose origin is at (x, y)
     * @param x The x origin
     * @param y The y origin
     */
    private void drawEngine(double x, double y) {
        drawCarFrame(x, y, Color.BLACK);
        drawSmokestack(x, y);
        drawEngineCab(x, y);
        drawCowcatcher(x, y);
    }

    /* Draws the smokestack */
    private void drawSmokestack(double x, double y) {
        // You fill this in //
        double xLeft = x + SMOKESTACK_INSET + CONNECTOR;
        double yTop = y-CAR_BASELINE-CAR_HEIGHT-SMOKESTACK_HEIGHT;
        Rectangle smokestack = new Rectangle(xLeft, yTop , SMOKESTACK_WIDTH, SMOKESTACK_HEIGHT);
        smokestack.setFillColor(Color.BLACK);
        smokestack.setFilled(true);
        canvas.add(smokestack);

    }

    /* Draws the engine cab */
    private void drawEngineCab(double x, double y) {

        double xLeft = x + CONNECTOR+CAR_WIDTH-CAB_WIDTH;
        double yTop = y-CAR_BASELINE-CAR_HEIGHT-CAB_HEIGHT;
        Rectangle cab = new Rectangle(xLeft, yTop , CAB_WIDTH, CAB_HEIGHT);
        cab.setFillColor(Color.BLACK);
        cab.setFilled(true);
        canvas.add(cab);
    }

    /* Draws the cowcatcher in the front of the engine */
    private void drawCowcatcher(double x, double y) {
        // You fill this in //
        double x1 = x;
        double x2 = x+CONNECTOR/2;
        double x3 = x+CONNECTOR;
        double y1 = y-CAR_BASELINE;
        double y2 = y-CAR_BASELINE-CAR_HEIGHT/2;

        Line l1 = new Line(x1,y1,x3,y2);
        Line l2 = new Line(x2,y1,x3,y2);
        canvas.add(l1);
        canvas.add(l2);
    }

    /* Draws a boxcar in the specified color */
    private void drawBoxcar(double x, double y, Color color) {
        drawCarFrame(x, y, color);
        double xRight = x + CONNECTOR + CAR_WIDTH / 2;
        double xLeft = xRight - DOOR_WIDTH;
        double yDoor = y - CAR_BASELINE - DOOR_HEIGHT;
        canvas.add(new Rectangle(xLeft, yDoor, DOOR_WIDTH, DOOR_HEIGHT));
        canvas.add(new Rectangle(xRight, yDoor, DOOR_WIDTH, DOOR_HEIGHT));
    }

    /* Draws a red caboose */
    private void drawCaboose(double x, double y) {
        drawCarFrame(x,y, Color.RED);
        drawCupola(x,y);
    }

    private void drawCupola(double x, double y) {
        double xLeft = x + CONNECTOR+CAR_WIDTH/2-CUPOLA_WIDTH/2;
        double yTop = y - CAR_BASELINE - CAR_HEIGHT - CUPOLA_HEIGHT;
        Rectangle cupola = new Rectangle(xLeft, yTop, CUPOLA_WIDTH, CUPOLA_HEIGHT);
        cupola.setFillColor(Color.RED);
        cupola.setFilled(true);
        canvas.add(cupola);
    }

    /* Draws the common part of a train car */
    private void drawCarFrame(double x, double y, Color color) {
        double x0 = x + CONNECTOR;
        double y0 = y - CAR_BASELINE;
        double top = y0 - CAR_HEIGHT;
        canvas.add(new Line(x, y0, x + CAR_WIDTH + 2 * CONNECTOR, y0));
        drawWheel(x0 + WHEEL_INSET, y - WHEEL_RADIUS);
        drawWheel(x0 + CAR_WIDTH - WHEEL_INSET, y - WHEEL_RADIUS);
        Rectangle r = new Rectangle(x0, top, CAR_WIDTH, CAR_HEIGHT);
        r.setFilled(true);
        r.setFillColor(color);
        canvas.add(r);
    }

    /* Draws a wheel centered at (x, y) */
    private void drawWheel(double x, double y) {
        double r = WHEEL_RADIUS;
        Ellipse wheel = new Ellipse(x - r, y - r, 2 * r, 2 * r);
        wheel.setFilled(true);
        wheel.setFillColor(Color.GRAY);
        canvas.add(wheel);
    }



}