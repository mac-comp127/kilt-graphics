package cantrell.animations.starterCode;

import comp127graphics.GraphicsObject;
import comp127graphics.Point;

import java.util.Random;

public class WanderingAnimation {
    private final GraphicsObject target;
    private final double speed, wiggliness;
    private double direction, turnRate;

    private static final Random rand = new Random();

    public WanderingAnimation(GraphicsObject target, double speed, double wiggliness) {
        this.target = target;
        this.speed = speed;
        this.wiggliness = wiggliness;

        this.direction = rand.nextFloat() * Math.PI * 2;
    }

    public void animate(double dt) {
        target.moveBy(Point.atAngle(direction).scale(dt * speed));

        direction += dt * turnRate;
        turnRate += dt
            * wiggliness
            * ((rand.nextFloat() - 0.5) * 2  // -1...1
                - this.turnRate * 0.02);     // counteract current turnRate to keep near 0
    }

    public boolean isComplete() {
        return false;
    }
}
