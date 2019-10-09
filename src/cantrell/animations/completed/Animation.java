package cantrell.animations.completed;

import comp127graphics.GraphicsObject;

public interface Animation {
    void animate(double dt);

    boolean isComplete();
}
