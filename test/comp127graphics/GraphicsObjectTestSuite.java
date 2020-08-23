package comp127graphics;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;

public interface GraphicsObjectTestSuite {
    GraphicsObject getGraphicsObject();

    default Point getCanvasSize() {
        return new Point(100, 100);
    }

    @AfterEach
    default void performEqualitySmokeTest() {
        GraphicsObject gobj = getGraphicsObject();
        assertTrue(gobj.equals(gobj));
        assertFalse(gobj.equals(null));
        assertFalse(gobj.equals((Object) "fish"));
        assertFalse(gobj.equals(new Rectangle(1, 2, 3, 4)));
    }

    static void assertChanged(GraphicsObject gobj, Runnable action) {
        var changeCounter = new ChangeCountingObserver();
        gobj.addObserver(changeCounter);
        action.run();
        gobj.removeObserver(changeCounter);
        assertTrue(
            changeCounter.getChangeCount() > 0,
            "Expected action to cause graphics object to broadcast a changed() notification (" + gobj + ")");
    }

    default void assertChanged(Runnable action) {
        assertChanged(getGraphicsObject(), action);
    }

    static void assertChangedAtEachStep(GraphicsObject gobj, Runnable... actions) {
        for (Runnable action : actions) {
            assertChanged(gobj, action);
        }
    }

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
