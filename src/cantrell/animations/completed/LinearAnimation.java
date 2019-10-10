package cantrell.animations.completed;

import comp127graphics.GraphicsObject;

/**
 * Animates an object along a straight line segment at a constant speed.
 */
@SuppressWarnings("NonAsciiCharacters")
public class LinearAnimation implements Animation {
    private final GraphicsObject target;
    private final double dx, dy;
    private double timeLeft;

    public LinearAnimation(GraphicsObject target, double Δx, double Δy, double totalTime) {
        this.target = target;
        this.dx = Δx / totalTime;
        this.dy = Δy / totalTime;
        timeLeft = totalTime;
    }

    @Override
    public void animate(double dt) {
        dt = Math.min(dt, timeLeft);
        timeLeft -= dt;

        target.moveBy(dx * dt, dy * dt);
    }

    @Override
    public boolean isComplete() {
        return timeLeft <= 0;
    }
}
