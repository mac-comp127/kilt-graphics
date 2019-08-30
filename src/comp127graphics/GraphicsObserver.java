package comp127graphics;

/**
 * Observer pattern interface used to notify a canvas to repaint if a graphical object has changed.
 *
 * @author Bret Jackson
 */
public interface GraphicsObserver {

    /**
     * Called after <i>any</i> graphic state change.
     * This includes, for example, a change in color, position, width, height, etc.
     */
    void graphicChanged(GraphicsObject changedObject);
}
