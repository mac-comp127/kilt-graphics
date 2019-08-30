package comp124graphics;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Creates a window frame and canvas panel that is used to draw graphical objects.
 * Created by bjackson on 9/13/2016.
 * @version 0.5
 */
public class CanvasWindow implements GraphicsObserver {

    private final Canvas canvas;
    private final JFrame windowFrame;
    private final GraphicsGroup content = new GraphicsGroup();

    private boolean drawingInitiated = false;
    private boolean mainThreadExitCheckScheduled = false;
    private final Object repaintLock = new Object();

    public CanvasWindow(String title, int windowWidth, int windowHeight){
        content.addObserver(this);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(windowWidth, windowHeight));
        canvas.setBackground(Color.white);

        windowFrame = new JFrame (title);
        windowFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        windowFrame.getContentPane().add(canvas);
        windowFrame.pack();
        windowFrame.setVisible(true);
    }

    public int getWidth() {
        return canvas.getWidth();
    }

    public int getHeight() {
        return canvas.getHeight();
    }

    /**
     * Adds the graphics object to the list of objects drawn on the canvas
     */
    public void add(GraphicsObject gObject){
        content.add(gObject);
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
        content.add(gObject, x, y);
    }

    /**
     * Removes the object from being drawn
     * @param gObject
     * @throws NoSuchElementException if gObject has not been added to the canvas
     */
    public void remove(GraphicsObject gObject){
        content.remove(gObject);
    }

    /**
     * Removes all of the objects currently drawn on the canvas
     */
    public void removeAll(){
        content.removeAll();
    }

    public void draw() {
        try {
            synchronized(repaintLock) {
                drawingInitiated = true;
                if(EventQueue.isDispatchThread()) {  // prevent deadlock when calling draw() in event handler
                    canvas.update(canvas.getGraphics());
                } else {
                    canvas.repaint(); // force redraw ASAP on AWT thread
                    repaintLock.wait();  // wait for drawing to complete (paintComponent notifies)
                }
            }
        } catch (InterruptedException ex) {
			/* Empty */
        }
    }

    /**
     * Pauses the program for the given time in milliseconds.
     */
    public void pause(long milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
			/* Empty */
        }
    }

    /**
     * Pauses the program for the given time in milliseconds.
     */
    public void pause(double milliseconds){
        try {
            long millisPart = (long) milliseconds;
            int nanosPart = (int) Math.round((milliseconds - millisPart) * 1000000);
            Thread.sleep(millisPart, nanosPart);
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
        return content.getElementAt(x, y);
    }

    /*
       Checks to see if clicked within a GraphicsGroup object and returns true if so
     */
    private boolean isGraphicsGroupAt(GraphicsObject obj, double x, double y) {  // TODO: Where in the course do we use this method?
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
     */
    public void graphicChanged(GraphicsObject changedObject) {
        synchronized(repaintLock) {
            if(!mainThreadExitCheckScheduled) {
                mainThreadExitCheckScheduled = true;

                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final long mainThreadID = Thread.currentThread().getId();
                Runnable mainThreadExitCheck = () -> {
                    for(Thread thread : Thread.getAllStackTraces().keySet()) {
                        if(thread.getId() == mainThreadID) {
                            return;
                        }
                    }
                    System.out.println("main() method completed; drawing CanvasWindow");
                    draw();
                    scheduler.shutdownNow();
                };

                scheduler.scheduleAtFixedRate(mainThreadExitCheck, 50, 100, TimeUnit.MILLISECONDS);
            }
        }
    }

    private void changed() {
        graphicChanged(null);
    }

    /**
     * Saves a screenshot of the currently drawn canvas' contents to the filename specified as a parameter
     * @param filename The filename for where you would like to save.
     */
    public void screenShot(String filename)
    {
        BufferedImage bImg = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
        canvas.paintAll(cg);
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

    /**
     * Closes the canvas window.
     */
    public void closeWindow() {
        windowFrame.dispatchEvent(new WindowEvent(windowFrame, WindowEvent.WINDOW_CLOSING));
    }

    class Canvas extends JPanel {
       /**
        * Called automatically by Java to redraw the graphics
        */
       public void paintComponent(Graphics page) {
           super.paintComponent(page);

           synchronized(repaintLock) {
               // AWT can create multiple repaint events internally during the creation of a window,
               // but we don't want to actually draw anything until the student has explicitly
               // requested it with a draw(). This prevents partial drawing / flickers of content,
               // as well as a false positives when the student has a loop with no draw() calls.
               if(!drawingInitiated) {
                   return;
               }

               Graphics2D gc = (Graphics2D) page;
               enableAntialiasing(gc);
               content.draw(gc);

               repaintLock.notifyAll();
           }
       }
   }
}