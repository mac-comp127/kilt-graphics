package cantrell.animations.completed;

import comp127graphics.GraphicsObject;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class GravityAnimation implements Animation {
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

    public static Animation hop(GraphicsObject target, double width, double height, double gravity) {
        double k = Math.sqrt(4 * height * gravity);
        double totalTime = k / gravity;
        return new GravityAnimation(target, width / totalTime, -k / 2, gravity, totalTime);
    }

    public static Animation bounce(GraphicsObject target, double width, double height, double gravity, double elasticity, int count) {
        List<Animation> bounces = new ArrayList<>(count);
        for (int n = 0; n < count; n++) {
            bounces.add(hop(target, width, height, gravity));
            width *= elasticity;
            height *= elasticity;
        }
        return new AnimationSequence(bounces);
    }

    public static Animation fall(GraphicsObject target, double gravity) {
        return new GravityAnimation(target, 0, 0, gravity, Double.POSITIVE_INFINITY);
    }

    @Override
    public void animate(double dt) {
        dt = Math.min(dt, timeLeft);  // Don't move beyond the end
        timeLeft -= dt;

        dy += gravity * dt / 2;  // Moving halfway through the timestep preserves parabola symmetry
        target.moveBy(dx * dt, dy * dt);
        dy += gravity * dt / 2;
    }

    @Override
    public boolean isComplete() {
        return timeLeft <= 0;
    }
}
