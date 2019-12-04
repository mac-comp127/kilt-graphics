package comp127graphics;

import comp127graphics.events.Key;
import comp127graphics.events.KeyboardEvent;
import comp127graphics.events.KeyboardEventHandler;
import comp127graphics.events.MouseButtonEvent;
import comp127graphics.events.MouseButtonEventHandler;
import comp127graphics.events.MouseMotionEvent;
import comp127graphics.events.MouseMotionEventHandler;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Consumer;

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
    // This is static because the first CanvasWindow is presumably created on the main thread, but
    // subsequent windows may be created on the AWT thread, and ThreadExitWatcher waits for
    // whatever thread it was created on. So we wait for the _first_ CanvasWindow’s thread to exit
    // when deferring tasks for _all_ CanvasWindows.
    private static final ThreadExitWatcher mainThreadWatcher = new ThreadExitWatcher();

    private final Canvas canvas;
    private final JFrame windowFrame;
    private final GraphicsGroup content = new GraphicsGroup();
    private Set<JComponent> embeddedComponents = Set.of();
    private final Rectangle background;

    private boolean drawingInitiated = false;
    private final Object repaintLock = new Object();

    private Point curMousePos, prevMousePos;
    private Set<Key> keysPressed = EnumSet.noneOf(Key.class);

    /**
     * Opens a new window for drawing.
     *
     * @param title        The user-visible title of the window
     * @param windowWidth  The width of the window's content area
     * @param windowHeight The height of the window's content area
     */
    public CanvasWindow(String title, int windowWidth, int windowHeight) {
        content.setCanvas(this);  // propagates to descendants

        // We use a Rectangle for the background because canvas.setBackground() triggers spurious
        // repaints, whereas this approach puts background color changes into the same paint cycle
        // as the rest of the graphics.
        background = new Rectangle(0, 0, 0, 0);
        background.setStroked(false);
        content.add(background);

        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(windowWidth, windowHeight));
        canvas.setBackground(Color.WHITE);
        canvas.setFocusable(true);  // enables key events

        windowFrame = new JFrame(title);
        windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        windowFrame.getContentPane().add(canvas);
        windowFrame.pack();
        windowFrame.setVisible(true);

        setUpMousePositionTracking();
        setUpKeyTracking();

        updateBackgroundSize();
        canvas.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateBackgroundSize();
            }
        });

        mainThreadWatcher.afterThreadExits(this::draw);
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
        return new Point(getWidth() / 2.0, getHeight() / 2.0);
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

                updateEmbeddedComponents();

                if (EventQueue.isDispatchThread()) {  // prevent deadlock when calling draw() in event handler
                    canvas.paintImmediately(canvas.getBounds());
                } else {
                    canvas.repaint(canvas.getBounds()); // force redraw ASAP on AWT thread
                    repaintLock.wait();  // wait for drawing to complete (paintComponent notifies)
                }
            }
        } catch (InterruptedException ex) {
            /* Empty */
        }
    }

    private void updateEmbeddedComponents() {
        Set<JComponent> updatedComponents = new LinkedHashSet<>();
        content.forEachDescendant(Point.ORIGIN, (gobj, pos) -> {
            JComponent component = gobj.getEmbeddedComponent();
            if (component != null) {
                component.setLocation(
                    (int) Math.round(pos.getX()),
                    (int) Math.round(pos.getY()));
                updatedComponents.add(component);
            }
        });

        for (JComponent existing : embeddedComponents) {
            if (!updatedComponents.contains(existing)) {
                canvas.remove(existing);
            }
        }
        for (JComponent newComponent : updatedComponents) {
            if (!embeddedComponents.contains(newComponent)) {
                canvas.add(newComponent);
            }
        }
        embeddedComponents = updatedComponents;
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
        GraphicsObject result = content.getElementAt(x, y);
        return (result == background) ? null : result;
    }

    /**
     * Returns the topmost graphical object underneath position x, y. If no such object exists it returns null.
     */
    public GraphicsObject getElementAt(Point position) {
        return getElementAt(position.getX(), position.getY());
    }

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

    /**
     * @deprecated Do not mix Swing and comp127graphics APIs
     */
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
        public void paintComponent(Graphics g) {
            synchronized (repaintLock) {
                super.paintComponent(g);

                // AWT can create multiple repaint events internally during the creation of a window,
                // but we don't want to actually draw anything until the student has explicitly
                // requested it with a draw(). This prevents partial drawing / flickers of content,
                // as well as a false positives when the student has a loop with no draw() calls.
                if (!drawingInitiated) {
                    return;
                }

                Graphics2D gc = (Graphics2D) g;
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
                performEventAction(() ->
                    handler.handleEvent(new MouseButtonEvent(e)));
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
                performEventAction(() ->
                    handler.handleEvent(new MouseButtonEvent(e)));
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
                performEventAction(() ->
                    handler.handleEvent(new MouseButtonEvent(e)));
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
                performEventAction(() ->
                    handler.handleEvent(new MouseMotionEvent(e, prevMousePos)));
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
                performEventAction(() ->
                    handler.handleEvent(new MouseMotionEvent(e, prevMousePos)));
            }
        });
    }

    /**
     * Adds a listener that will receive an event when a key on the keyboard is pressed.
     *
     * Note that this reports actual keys on the keyboard, not which characters they produce.
     * See {@link #onKeyUp(KeyboardEventHandler) onKeyUp()} for more information.
     *
     * If you want to take some continuous action as long as a key is pressed, consider
     * {@link #getKeysPressed()} instead.
     *
     * @see #onKeyUp(KeyboardEventHandler)
     * @see #onCharacterTyped(Consumer)
     */
    public void onKeyDown(KeyboardEventHandler handler) {
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                performEventAction(() ->
                    handler.handleEvent(new KeyboardEvent(e)));
            }
        });
    }

    /**
     * Adds a listener that will receive an event when a key on the keyboard is released.
     *
     * Note that this reports actual keys on the keyboard, not any characters they produce.
     * For example, if the user types a plus sign, you will receive the following events:
     * <p>
     * <table>
     *   <tr><td>1.</td><td><b>keyDown</b></td><td>Key.SHIFT</td><td>modifiers=[ModifierKey.SHIFT]</td></tr>
     *   <tr><td>2.</td><td><b>keyDown</b></td><td>Key.EQUALS</td><td>modifiers=[ModifierKey.SHIFT]</td></tr>
     *   <tr><td>3.</td><td><b>keyUp</b></td><td>Key.EQUALS</td><td>modifiers=[ModifierKey.SHIFT]</td></tr>
     *   <tr><td>4.</td><td><b>characterTyped</b></td><td><code>'+'</code></td></tr>
     *   <tr><td>5.</td><td><b>keyUp</b></td><td>Key.SHIFT</td><td>modifiers=[]</td></tr>
     * </table>
     * </p>
     * If you want to know what character the user typed, taking keyboard layout and modifier keys
     * into account (e.g. 'a' vs 'A'), consider {@link #onCharacterTyped(Consumer) onCharacterTyped()}
     * instead.
     *
     * If you want to take some continuous action as long as a key is pressed, consider
     * {@link #getKeysPressed()} instead.
     *
     * @see #onKeyDown(KeyboardEventHandler)
     * @see #onCharacterTyped(Consumer)
     */
    public void onKeyUp(KeyboardEventHandler handler) {
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                performEventAction(() ->
                    handler.handleEvent(new KeyboardEvent(e)));
            }
        });
    }

    /**
     * Adds a listener that will be notified when the use types a combination of keys that produce
     * a character.
     *
     * Note that this only reports key combinations that produce characters; it does not report
     * special keys such as arrow keys, backspace, modifiers, etc.
     *
     * See {@link #onKeyUp(KeyboardEventHandler) onKeyUp()} for more information.
     *
     * @see #onKeyDown(KeyboardEventHandler)
     * @see #onKeyUp(KeyboardEventHandler)
     */
    public void onCharacterTyped(Consumer<Character> handler) {
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                performEventAction(() -> {
                    // Arrows, modifier keys, tab, escape, etc. come back as either control chars
                    // or \uFFFF. We don't pass them to handler; clients will have to use onKeyUp
                    // for special keys.
                    if (e.getKeyChar() >= ' ' && e.getKeyChar() != '\uFFFF') {
                        handler.accept(e.getKeyChar());
                    }
                });
            }
        });
    }

    private void setUpKeyTracking() {
        onKeyDown(e -> {
            if (e.getKey() != Key.UNKNOWN) {
                keysPressed.add(e.getKey());
            }
        });

        onKeyUp(e -> {
            if (e.getKey() != Key.UNKNOWN) {
                keysPressed.remove(e.getKey());
            }
        });

        // If the user switches app while a key is down, Java _never_ reports the key release.
        // We therefore assume all keys were released (even if they aren't) when the user switches
        // focus; otherwise, the app will report a key is pressed forever.
        //
        // Java still has numerous key event consistency issues around the edges. There is AFAICT no
        // way to detect that a key is already down when we gain focus. Java doesn't detect focus
        // loss caused by key-intercepting apps such as LaunchBar, so summoning one with keys down
        // can cause “sticky key” issues. Still, what's here should be good enough for most projects.

        canvas.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                keysPressed.clear();
            }
        });
    }

    /**
     * Returns all keys currently pressed on the keyboard. You can poll this repeatedly (e.g. in an
     * animate() callback) to continuously take some action as long as a key is held down.
     *
     * If instead you want to do something only at the moment a key goes down or comes back up, use
     * {@link #onKeyDown(KeyboardEventHandler) onKeyDown} and
     * {@link #onKeyUp(KeyboardEventHandler) onKeyUp()} instead.
     */
    public Set<Key> getKeysPressed() {
        return Collections.unmodifiableSet(keysPressed);
    }

    /**
     * Call the given callback repeatedly forever, up to 60 times per second. Automatically draws
     * the canvas after each time the callback runs.
     */
    public void animate(Runnable callback) {
        mainThreadWatcher.afterThreadExits(() -> {
            Timer timer = new Timer(15, e -> {
                callback.run();
                draw();
            });
            timer.setRepeats(true);
            timer.start();
        });
    }

    /**
     * For internal use.
     */
    public void performEventAction(Runnable action) {
        mainThreadWatcher.afterThreadExits(() -> {
            action.run();
            draw();
        });
    }
}
