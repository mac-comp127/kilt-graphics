package cantrell.animations.completed;

import comp127graphics.GraphicsObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Animates a GraphicsObject moving along a parabolic trajectory, as if under the influence of
 * a downward-pointing gravitational force.
 */
@SuppressWarnings("WeakerAccess")
public class GravityAnimation implements Animation {
    private final GraphicsObject target;
    private final double gravity;
    private double dx, dy;
    private double timeLeft;

    /**
     * Creates a gravity animation along an arbitrary parabolic segment.
     *
     * @param target    The object to animate
     * @param initialdx Initial velocity (px/s)
     * @param initialdy Initial velocity (px/s)
     * @param gravity   Force of gravity (px/s²)
     * @param totalTime Time the animation should last
     */
    public GravityAnimation(GraphicsObject target, double initialdx, double initialdy, double gravity, double totalTime) {
        this.target = target;
        this.gravity = gravity;

        dx = initialdx;
        dy = initialdy;
        timeLeft = Math.max(0, totalTime);
    }

    /**
     * Creates an animation of an object leaping into the air and falling back down. The animation
     * stops when the object reaches its original y position.
     *
     * @param target  The object to animate
     * @param width   Total horizontal distance travelled by the hop (px)
     * @param height  Maximum height of the hop (px)
     * @param gravity Force of gravity (px/s²)
     */
    public static Animation hop(GraphicsObject target, double width, double height, double gravity) {
        double k = Math.sqrt(4 * height * gravity);
        double totalTime = k / gravity;
        return new GravityAnimation(target, width / totalTime, -k / 2, gravity, totalTime);
    }

    /**
     * Creates an animation of an object hopping up, then bouncing repeatedly, like an elastic ball
     * bouncing on the ground.
     *
     * @param target     The object to animate
     * @param width      Width of the first hop (px)
     * @param height     Height of the first hop (px)
     * @param gravity    Force of gravity (px/s²)
     * @param elasticity Ratio of subsequent hop sizes to the first one (1 = no decay, <1 = smaller)
     * @param count      Number of hops
     */
    public static Animation bounce(GraphicsObject target, double width, double height, double gravity, double elasticity, int count) {
        List<Animation> bounces = new ArrayList<>(count);
        for (int n = 0; n < count; n++) {
            bounces.add(hop(target, width, height, gravity));
            width *= elasticity;
            height *= elasticity;
        }
        return new AnimationSequence(bounces);
    }

    /**
     * Creates an animation of an object initially at rest falling straight down.
     *
     * @param target  Object to animate
     * @param gravity Force of gravity (px/s²)
     */
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
