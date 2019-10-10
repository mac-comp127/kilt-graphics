package cantrell.animations.completed;

import java.util.List;

/**
 * Several animations played sequentially. When one animation is complete, the next in the list
 * starts. When all animations in the list and complete, the whole sequence is complete.
 */
public class AnimationSequence implements Animation {
    private final List<Animation> animations;
    private int currentIndex = 0;

    public AnimationSequence(List<Animation> animations) {
        this.animations = animations;
    }

    @Override
    public void animate(double dt) {
        if (isComplete()) {
            return;
        }
        Animation currentAnimation = animations.get(currentIndex);
        currentAnimation.animate(dt);
        if (currentAnimation.isComplete()) {
            currentIndex++;
        }
    }

    @Override
    public boolean isComplete() {
        return currentIndex >= animations.size();
    }
}
