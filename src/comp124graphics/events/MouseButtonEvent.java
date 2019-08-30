package comp124graphics.events;

import comp124graphics.Point;

public final class MouseButtonEvent {
    private final Point position;

    public MouseButtonEvent(java.awt.event.MouseEvent sourceEvent) {
        position = new Point(sourceEvent.getPoint());
    }

    public Point getPosition() {
        return position;
    }
}
