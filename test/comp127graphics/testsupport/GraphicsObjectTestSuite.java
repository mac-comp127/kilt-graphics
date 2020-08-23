package comp127graphics.testsupport;

import static comp127graphics.PointTest.assertClose;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;

import comp127graphics.GraphicsObject;
import comp127graphics.GraphicsObserver;
import comp127graphics.Point;
import comp127graphics.Rectangle;

/**
 * Shared support for suites that test a GraphicsObject. Adds standard across-the-board testing,
 * supports the @RenderingTest annotation, and provides assertion helpers.
 */
public interface GraphicsObjectTestSuite {
    /**
     * The graphics object whose image @RenderingTest methods should render and compare.
     */
    GraphicsObject getGraphicsObject();

    /**
     * The size of image to create in @RenderingTest methods.
     */
    default Point getCanvasSize() {
        return new Point(100, 100);
    }

    /**
     * Checks for simple, generic errors in the GraphicsObject's equals() method.
     */
    @AfterEach
    default void performEqualitySmokeTest() {
        GraphicsObject gobj = getGraphicsObject();
        assertTrue(gobj.equals(gobj));
        assertFalse(gobj.equals(null));
        assertFalse(gobj.equals((Object) "fish"));
        assertFalse(gobj.equals(new Rectangle(1, 2, 3, 4)));
    }

    /**
     * Ensures that general positioning behavior works.
     */
    @AfterEach
    default void performRepositioningSmokeTest() {
        GraphicsObject gobj = getGraphicsObject();
        var p = new Point(73.2, 19.7);
        gobj.setPosition(p);
        assertClose(p, gobj.getPosition(), 0.5);
        gobj.setCenter(p);
        assertClose(p, gobj.getCenter(), 0.5);
    }

    /**
     * Asserts that running the specified action should cause the given graphics object to emit
     * a change notification (typically by calling its changed() method internally).
     */
    static void assertChanged(GraphicsObject gobj, Runnable action) {
        var changeCounter = new ChangeCountingObserver();
        gobj.addObserver(changeCounter);
        action.run();
        gobj.removeObserver(changeCounter);
        assertTrue(
            changeCounter.getChangeCount() > 0,
            "Expected action to cause graphics object to broadcast a changed() notification (" + gobj + ")");
    }

    /**
     * Asserts that running the specified action should cause this test suite's graphics object
     * (the one getGraphicsObject() returns) to emit a change notification.
     */
    default void assertChanged(Runnable action) {
        assertChanged(getGraphicsObject(), action);
    }

    /**
     * Asserts that each of the specified actions should cause the given graphics object to emit
     * a separate change notification.
     */
    static void assertChangedAtEachStep(GraphicsObject gobj, Runnable... actions) {
        for (Runnable action : actions) {
            assertChanged(gobj, action);
        }
    }

    /**
     * Asserts that each of the specified actions should cause this test suite's graphics object
     * (the one getGraphicsObject() returns) to emit a change notification.
     */
    default void assertChangedAtEachStep(Runnable... actions) {
        assertChangedAtEachStep(getGraphicsObject(), actions);
    }
}

class ChangeCountingObserver implements GraphicsObserver {
    private int changeCount = 0;

    public int getChangeCount() {
        return changeCount;
    }

    @Override
    public void graphicChanged(GraphicsObject changedObject) {
        changeCount++;
    }
}
