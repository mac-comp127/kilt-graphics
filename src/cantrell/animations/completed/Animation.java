package cantrell.animations.completed;

public interface Animation {
    /**
     * Advance the animation by one discrete time step.
     *
     * @param dt Time to advance by, in seconds
     */
    void animate(double dt);

    /**
     * Returns true if the animation is done, and further calls to animate() are unnecessary.
     */
    boolean isComplete();
}
