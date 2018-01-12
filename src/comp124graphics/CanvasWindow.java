package comp124graphics;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Creates a window frame and canvas panel that is used to draw graphical objects.
 * Created by bjackson on 9/13/2016.
 * @version 0.5
 */
public class CanvasWindow extends JPanel implements GraphicsObserver{

    /**
     * Window frame
     */
    protected JFrame windowFrame;

    /**
     * Holds the objects to be drawn in calls to paintComponent
     */
    private ConcurrentLinkedDeque<GraphicsObject> gObjects;

    public CanvasWindow(String title, int windowWidth, int windowHeight){
        setPreferredSize (new Dimension(windowWidth, windowHeight));
        setBackground (Color.white);

        gObjects = new ConcurrentLinkedDeque<GraphicsObject>();

        windowFrame = new JFrame (title);
        windowFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        windowFrame.getContentPane().add(this);
        windowFrame.pack();
        windowFrame.setVisible(true);
    }

    /**
     * Called automatically by java to draw graphical objects on the page
     * @param page
     */
    public void paintComponent (Graphics page) {
        super.paintComponent (page);

        Graphics2D gc = (Graphics2D)page;

        enableAntialiasing(gc);

        // Iterate over all of the graphical objects and draw them.
        Iterator<GraphicsObject> it = gObjects.iterator();
        while (it.hasNext()){
            it.next().draw(gc);
        }
    }

    /**
     * Adds the graphical object to the list of objects drawn on the canvas
     * @param gObject
     */
    public void add(GraphicsObject gObject){
        gObject.addObserver(this);
        gObjects.add(gObject);
        repaint();
    }

    /**
     * Adds the graphical object to the list of objects drawn on the canvas
     * at the position x, y.
     *
     * @param gObject  the graphical object to be drawn
     * @param x        the x position of graphical object
     * @param y        the y position of graphical object
     */
    public void add(GraphicsObject gObject, double x, double y){
        gObject.setPosition(x, y);
        this.add(gObject);
//        gObject.addObserver(this);
//        gObjects.add(gObject);
//        repaint();
    }

    /**
     * Removes the object from being drawn
     * @param gObject
     * @throws NoSuchElementException if gObject has not been added to the canvas
     */
    public void remove(GraphicsObject gObject){
        gObject.removeObserver(this);
        boolean success = gObjects.remove(gObject);
        if (!success){
            throw new NoSuchElementException("The object you want to remove has not been added to the canvaswindow. Perhaps it was already removed or was added to a GraphicsGroup instead of the canvas.");
        }
        repaint();
    }

    /**
     * Removes all of the objects currently drawn on the canvas
     */
    public void removeAll(){
        Iterator<GraphicsObject> it = gObjects.iterator();
        while(it.hasNext()){
            GraphicsObject obj = it.next();
            obj.removeObserver(this);
            it.remove();
        }
        repaint();
    }

    /**
     * Pauses the program for milliseconds
     * @param milliseconds
     */
    public void pause(long milliseconds){
        try{
            Thread.sleep(milliseconds);
        }
        catch(InterruptedException e) {
            // Empty
        }
    }

    /**
     * Pauses the program for milliseconds
     * @param milliseconds
     */
    public void pause(double milliseconds){
        try {
            int millis = (int) milliseconds;
            int nanos = (int) Math.round((milliseconds - millis) * 1000000);
            Thread.sleep(millis, nanos);
        } catch (InterruptedException ex) {
			/* Empty */
        }
    }

    /**
     * Returns the topmost graphical object underneath position x, y. If no such object exists it returns null.
     * @param x position
     * @param y position
     * @return object at (x,y) or null if it does not exist.
     */
    public GraphicsObject getElementAt(double x, double y){
        Graphics2D gc = (Graphics2D)this.getGraphics();
        Iterator<GraphicsObject> it = gObjects.descendingIterator();
        while(it.hasNext()){
            GraphicsObject obj = it.next();
            //if (obj.testHit(x, y, gc)){
            if (isGGat(obj, x, y) || obj.testHit(x, y, gc)){
                return obj;
            }
        }
        return null;
    }

    /*
       Checks to see if clicked within a GraphicsGroup object and returns true if so
     */
    private boolean isGGat(GraphicsObject obj, double x, double y) {
        if (obj instanceof GraphicsGroup) {
            java.awt.Rectangle rect = obj.getBounds();
            if (rect.getX() <= x && x<= (rect.getX() + rect.getWidth())) {
                if (rect.getY() <= y && y <= (rect.getY()+rect.getHeight())) {
                    return true;
                }
            }

        }
        return false;
    }

    /**
     * Enables antialiasing on the drawn shapes.
     * @param gc
     */
    private void enableAntialiasing(Graphics2D gc) {
        gc.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        gc.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        gc.setRenderingHint(
                RenderingHints.KEY_STROKE_CONTROL,
                RenderingHints.VALUE_STROKE_PURE);
    }

    /**
     * Implementation of GraphicsObserver method. Notifies Java to repaint the image if any of the objects drawn on the canvas
     * have changed.
     * @param changedObject
     */
    public void graphicChanged(GraphicsObject changedObject){
        repaint();
    }

    /**
     * Saves a screenshot of the currently drawn canvas' contents to the filename specified as a parameter
     * @param filename The filename for where you would like to save.
     */
    public void screenShot(String filename)
    {
        BufferedImage bImg = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
        this.paintAll(cg);
        try {
            if (ImageIO.write(bImg, "png", new File(filename)))
            {
                System.out.println("-- saved");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public JFrame getWindowFrame(){
        return windowFrame;
    }

}


