package cantrell.animations.starterCode;

import comp127graphics.GraphicsObject;

@SuppressWarnings("NonAsciiCharacters")
public class LinearAnimation {
    private final GraphicsObject target;
    private final double dx, dy;
    private double timeLeft;

    public LinearAnimation(GraphicsObject target, double Δx, double Δy, double totalTime) {
        this.target = target;
        this.dx = Δx / totalTime;
        this.dy = Δy / totalTime;
        timeLeft = totalTime;
    }

    public void animate(double dt) {
        dt = Math.min(dt, timeLeft);
        timeLeft -= dt;

        target.moveBy(dx * dt, dy * dt);
    }

    public boolean isComplete() {
        return timeLeft <= 0;
    }
}
