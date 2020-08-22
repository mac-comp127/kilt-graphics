package comp127graphics;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public interface GraphicsObjectTestSuite {
    GraphicsObject getGraphicsObject();

    default Point getCanvasSize() {
        return new Point(100, 100);
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
