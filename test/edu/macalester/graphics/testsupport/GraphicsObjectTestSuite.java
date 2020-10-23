package edu.macalester.graphics.testsupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;

import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.GraphicsObserver;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.PointTest;

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
     * Checks for simple, generic errors in the GraphicsObject's equals() method.
     */
    @AfterEach
    default void performEqualitySmokeTest() {
        GraphicsObject gobj = getGraphicsObject();
        if (gobj == null) {
            return;
        }
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
        if (gobj == null) {
            return;
        }

        var p = new Point(73.2, 19.7);
        gobj.setPosition(p);
        PointTest.assertClose(p, gobj.getPosition(), 0.5);
        assertEquals(73.2, gobj.getX(), 0.5);
        assertEquals(19.7, gobj.getY(), 0.5);

        gobj.setX(85.4);
        assertEquals(85.4, gobj.getX(), 0.5);
        assertEquals(19.7, gobj.getY(), 0.5);

        gobj.setY(13.1);
        assertEquals(85.4, gobj.getX(), 0.5);
        assertEquals(13.1, gobj.getY(), 0.5);

        gobj.setCenter(p);
        PointTest.assertClose(p, gobj.getCenter(), 0.5);
    }

    /**
     * Asserts that running the specified action should cause the given graphics object to emit
     * a change notification (typically by calling its changed() method internally).
     */
    static void assertChanged(GraphicsObject gobj, Runnable action) {
        int changeCount = applyActionAndCountChanges(gobj, action);
        assertTrue(
            changeCount > 0,
            "Expected action to cause graphics object to broadcast a changed() notification (" + gobj + ")");
    }

    /**
     * Asserts that running the specified action should NOT cause the given graphics object to emit
     * a change notification.
     */
    static void assertNotChanged(GraphicsObject gobj, Runnable action) {
        int changeCount = applyActionAndCountChanges(gobj, action);
        assertTrue(
            changeCount == 0,
            "Expected action NOT to cause graphics object to broadcast a changed() notification (" + gobj + ")");
    }

    static int applyActionAndCountChanges(GraphicsObject gobj, Runnable action) {
        var changeCounter = new ChangeCountingObserver();
        gobj.addObserver(changeCounter);
        action.run();
        gobj.removeObserver(changeCounter);
        return changeCounter.getChangeCount();
    }
    
    /**
     * Asserts that running the specified action should cause this test suite's graphics object
     * (the one getGraphicsObject() returns) to emit a change notification.
     */
    default void assertChanged(Runnable action) {
        assertChanged(getGraphicsObject(), action);
    }

    /**
     * Asserts that running the specified action should NOT cause this test suite's graphics object
     * to emit a change notification.
     */
    default void assertNotChanged(Runnable action) {
        assertNotChanged(getGraphicsObject(), action);
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
