package comp124graphics;

public class MouseMotionEvent {
    private final Point position, previousPosition, delta;

    MouseMotionEvent(java.awt.event.MouseEvent sourceEvent, Point previousPosition) {
        position = new Point(sourceEvent.getPoint());
        this.previousPosition = previousPosition;
        delta = position.subtract(previousPosition);
    }

    public Point getPosition() {
        return position;
    }

    public Point getPreviousPosition() {
        return previousPosition;
    }

    public Point getDelta() {
        return delta;
    }
}
