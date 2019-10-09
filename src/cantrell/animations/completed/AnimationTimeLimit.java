package cantrell.animations.completed;

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
        dt = Math.min(dt, timeLimit - elapsedTime);
        elapsedTime += dt;
        animation.animate(dt);
    }

    @Override
    public boolean isComplete() {
        return elapsedTime >= timeLimit;
    }
}
