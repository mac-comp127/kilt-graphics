package cantrell.animations.completed;

/**
 * Wraps another animation, halting it if it is not done by the given time limit. Useful for turning
 * an endless animation (such as WanderingAnimation) into a finite one.
 */
public class AnimationTimeLimit implements Animation {
    private final Animation animation;
    private final double timeLimit;
    private double elapsedTime;

    public AnimationTimeLimit(double timeLimit, Animation animation) {
        this.animation = animation;
        this.timeLimit = timeLimit;
    }

    @Override
    public void animate(double dt) {
        if (animation.isComplete()) {
            return;
        }
        dt = Math.min(dt, timeLimit - elapsedTime);
        elapsedTime += dt;
        animation.animate(dt);
    }

    @Override
    public boolean isComplete() {
        return animation.isComplete() || elapsedTime >= timeLimit;
    }
}
