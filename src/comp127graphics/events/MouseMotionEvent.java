package comp127graphics.events;

import comp127graphics.Point;

public class MouseMotionEvent {
    private final Point position, previousPosition, delta;

    public MouseMotionEvent(java.awt.event.MouseEvent sourceEvent, Point previousPosition) {
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
