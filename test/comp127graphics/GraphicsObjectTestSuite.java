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

    default void assertChanged(Runnable action) {
        var changeCounter = new ChangeCountingObserver();
        getGraphicsObject().addObserver(changeCounter);
        action.run();
        getGraphicsObject().removeObserver(changeCounter);
        assertTrue(changeCounter.getChangeCount() > 0);
    }

    default void assertChangedAtEachStep(Runnable... actions) {
        for (Runnable action : actions) {
            assertChanged(action);
        }
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
