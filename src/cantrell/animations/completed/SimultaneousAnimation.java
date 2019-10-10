package cantrell.animations.completed;

import java.util.List;

/**
 * Runs several animations at once. The animation completes when all the sub-animations are complete.
 */
public class SimultaneousAnimation implements Animation {
    private final List<Animation> animations;

    public SimultaneousAnimation(List<Animation> animations) {
        this.animations = animations;
    }

    @Override
    public void animate(double dt) {
        for (Animation animation : animations) {
            if (!animation.isComplete()) {
                animation.animate(dt);
            }
        }
    }

    @Override
    public boolean isComplete() {
        for (Animation animation : animations) {
            if (!animation.isComplete()) {
                return false;
            }
        }
        return true;
    }
}
