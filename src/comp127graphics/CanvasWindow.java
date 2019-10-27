package comp127graphics;

import comp127graphics.events.MouseButtonEvent;
import comp127graphics.events.MouseButtonEventHandler;
import comp127graphics.events.MouseMotionEvent;
import comp127graphics.events.MouseMotionEventHandler;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * A window frame that can contain graphical objects.
 * <p>
 * A CanvasWindow will not immediate draw GraphicsObjects you add to it. Instead, it waits until
 * one of the following things happens:
 * <ol>
 *   <li>Your main() method exits.</li>
 *   <li>An event listener completes.</li>
 *   <li>You explicitly call the CanvasWindow's draw() method.</li>
 * </ol>
 * <p>
 * You can use draw() to create animations using loops.
 *
 * @author Bret Jackson
 * @author Paul Cantrell
 */
public class CanvasWindow {

    private final Canvas canvas;
    private final JFrame windowFrame;
    private final GraphicsGroup content = new GraphicsGroup();
    private final Rectangle background;

    private boolean drawingInitiated = false;
    private boolean mainThreadExitCheckScheduled = false;
    private final Object repaintLock = new Object();

    private Point curMousePos, prevMousePos;

    /**
     * Opens a new window for drawing.
     *
     * @param title        The user-visible title of the window
     * @param windowWidth  The width of the window's content area
     * @param windowHeight The height of the window's content area
     */
    public CanvasWindow(String title, int windowWidth, int windowHeight) {
        // We use a Rectangle for the background because canvas.setBackground() triggers spurious
        // repaints, whereas this approach puts background color changes into the same paint cycle
        // as the rest of the graphics.
        background = new Rectangle(0, 0, 0, 0);
        background.setStroked(false);
        content.add(background);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(windowWidth, windowHeight));
        canvas.setBackground(Color.WHITE);

        windowFrame = new JFrame(title);
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFrame.getContentPane().add(canvas);
        windowFrame.pack();
        windowFrame.setVisible(true);

        content.addObserver((g) -> scheduleMainThreadExitCheck());

        setUpMousePositionTracking();

        updateBackgroundSize();
        canvas.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateBackgroundSize();
            }
        });
    }

    /**
     * Returns the width of the window's visible content area, not including any title bar and border.
     */
    public int getWidth() {
        return canvas.getWidth();
    }

    /**
     * Returns the height of the window's visible content area, not including any title bar and border.
     */
    public int getHeight() {
        return canvas.getHeight();
    }

    /**
     * Returns the center of the window’s visible content area.
     */
    public Point getCenter() {
        return new Point(getWidth() / 2, getHeight() / 2);
    }

    /**
     * Changes the background color of the window.
     */
    public void setBackground(Paint color) {
        background.setFillColor(color);
    }

    private void updateBackgroundSize() {
        background.setSize(canvas.getWidth(), canvas.getHeight());
    }

    /**
     * Adds the given graphics object to the objects that will be drawn on the canvas.
     */
    public void add(GraphicsObject gObject) {
        content.add(gObject);
    }

    /**
     * Adds the graphical object to the list of objects drawn on the canvas
     * at the position x, y.
     *
     * @param gObject the graphical object to be drawn
     * @param x       the x position of graphical object
     * @param y       the y position of graphical object
     */
    public void add(GraphicsObject gObject, double x, double y) {
        content.add(gObject, x, y);
    }

    /**
     * Removes the object from being drawn
     *
     * @param gObject
     * @throws NoSuchElementException if gObject has not been added to the canvas
     */
    public void remove(GraphicsObject gObject) {
        content.remove(gObject);
    }

    /**
     * Removes all of the objects currently drawn on the canvas. Does not change the background color.
     */
    public void removeAll() {
        content.removeAll();
        content.add(background);
    }

    /**
     * Immediately draws the currently added graphics objects to the screen. This method is
     * synchronous: it waits for the drawing to complete before proceeding.
     */
    public void draw() {
        try {
            synchronized (repaintLock) {
                drawingInitiated = true;
                if (EventQueue.isDispatchThread()) {  // prevent deadlock when calling draw() in event handler
                    canvas.paintImmediately(canvas.getBounds());
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
    public void pause(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            /* Empty */
        }
    }

    /**
     * Pauses the program for the given time in milliseconds.
     */
    public void pause(double milliseconds) {
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
     */
    public GraphicsObject getElementAt(double x, double y) {
        return content.getElementAt(x, y);
    }

    /**
     * Returns the topmost graphical object underneath position x, y. If no such object exists it returns null.
     */
    public GraphicsObject getElementAt(Point position) {
        return getElementAt(position.getX(), position.getY());
    }

// TODO: Where in the course do we use this method? Can we delete it?
//    /*
//       Checks to see if clicked within a GraphicsGroup object and returns true if so
//     */
//    private boolean isGraphicsGroupAt(GraphicsObject obj, double x, double y) {
//        if (obj instanceof GraphicsGroup) {
//            java.awt.Rectangle rect = obj.getBounds();
//            if (rect.getX() <= x && x<= (rect.getX() + rect.getWidth())) {
//                if (rect.getY() <= y && y <= (rect.getY()+rect.getHeight())) {
//                    return true;
//                }
//            }
//        }
//        return false;
//    }

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

    private void scheduleMainThreadExitCheck() {
        synchronized (repaintLock) {
            if (!mainThreadExitCheckScheduled) {
                mainThreadExitCheckScheduled = true;

                final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

                final long mainThreadID = Thread.currentThread().getId();
                Runnable mainThreadExitCheck = () -> {
                    for (Thread thread : Thread.getAllStackTraces().keySet()) {
                        if (thread.getId() == mainThreadID) {
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

    /**
     * Saves a screenshot of the currently drawn canvas' contents to the filename specified as a parameter
     *
     * @param filename The filename for where you would like to save.
     */
    public void screenShot(String filename) {
        BufferedImage bImg = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D cg = bImg.createGraphics();
        canvas.paintAll(cg);
        try {
            if (ImageIO.write(bImg, "png", new File(filename))) {
                System.out.println("-- saved");
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public JFrame getWindowFrame() {
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

            synchronized (repaintLock) {
                // AWT can create multiple repaint events internally during the creation of a window,
                // but we don't want to actually draw anything until the student has explicitly
                // requested it with a draw(). This prevents partial drawing / flickers of content,
                // as well as a false positives when the student has a loop with no draw() calls.
                if (!drawingInitiated) {
                    return;
                }

                Graphics2D gc = (Graphics2D) page;
                enableAntialiasing(gc);
                content.draw(gc);

                repaintLock.notifyAll();
            }
        }
    }

    // ------------ Event Handling ------------

    private void setUpMousePositionTracking() {
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                curMousePos = new Point(e.getPoint());
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                curMousePos = new Point(e.getPoint());
            }
        });

        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                updatePositions(e);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                updatePositions(e);
            }

            private void updatePositions(MouseEvent e) {
                prevMousePos = curMousePos;
                curMousePos = new Point(e.getPoint());
            }
        });
    }

    /**
     * Adds a listener that will receive an event when the mouse button goes down inside the window.
     */
    public void onMouseDown(MouseButtonEventHandler handler) {
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handler.handleEvent(new MouseButtonEvent(e));
                draw();
            }
        });
    }

    /**
     * Adds a listener that will receive an event when the mouse button goes up inside the window.
     */
    public void onMouseUp(MouseButtonEventHandler handler) {
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                handler.handleEvent(new MouseButtonEvent(e));
                draw();
            }
        });
    }

    /**
     * Adds a listener that will receive an event when the mouse button goes down and then up inside
     * the window without moving significantly (i.e. not a drag).
     */
    public void onClick(MouseButtonEventHandler handler) {
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                handler.handleEvent(new MouseButtonEvent(e));
                draw();
            }
        });
    }

    /**
     * Adds a listener that will receive an event when the mouse moves over the window with the
     * button up.
     */
    public void onMouseMove(MouseMotionEventHandler handler) {
        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                handler.handleEvent(new MouseMotionEvent(e, prevMousePos));
                draw();
            }
        });
    }

    /**
     * Adds a listener that will receive an event when the mouse moves over the window with the
     * button down.
     */
    public void onDrag(MouseMotionEventHandler handler) {
        canvas.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handler.handleEvent(new MouseMotionEvent(e, prevMousePos));
                draw();
            }
        });
    }
}
