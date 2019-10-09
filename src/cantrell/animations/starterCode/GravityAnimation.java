package cantrell.animations.starterCode;

import comp127graphics.GraphicsObject;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class GravityAnimation {
    private final GraphicsObject target;
    private final double gravity;
    private double dx, dy;
    private double timeLeft;

    public GravityAnimation(GraphicsObject target, double initialdx, double initialdy, double gravity, double totalTime) {
        this.target = target;
        this.gravity = gravity;

        dx = initialdx;
        dy = initialdy;
        timeLeft = Math.max(0, totalTime);
    }

    public static GravityAnimation hop(GraphicsObject target, double width, double height, double gravity) {
        double k = Math.sqrt(4 * height * gravity);
        double totalTime = k / gravity;
        return new GravityAnimation(target, width / totalTime, -k / 2, gravity, totalTime);
    }

    public static GravityAnimation fall(GraphicsObject target, double gravity) {
        return new GravityAnimation(target, 0, 0, gravity, Double.POSITIVE_INFINITY);
    }

    public void animate(double dt) {
        dt = Math.min(dt, timeLeft);  // Don't move beyond the end
        timeLeft -= dt;

        dy += gravity * dt / 2;  // Moving halfway through the timestep preserves parabola symmetry
        target.moveBy(dx * dt, dy * dt);
        dy += gravity * dt / 2;
    }

    public boolean isComplete() {
        return timeLeft <= 0;
    }
}
