package cantrell.animations.starterCode;

import comp127graphics.CanvasWindow;

import java.util.ArrayList;
import java.util.List;

public class Animator {
    private static final double FRAME_RATE = 60;

    private CanvasWindow canvas;
    private List<LinearAnimation> animations = new ArrayList<>();

    public Animator(CanvasWindow canvas) {
        this.canvas = canvas;
    }

    public void add(LinearAnimation animation) {
        animations.add(animation);
    }

    public void run() {
        canvas.pause(300);  // Leave some time for the window to appear & IntelliJ to calm down
        boolean stillAnimating;
        do {
            long nextFrameTime = System.currentTimeMillis() + Math.round(1000 / FRAME_RATE);

            stillAnimating = false;
            for (LinearAnimation animation : animations) {
                if (!animation.isComplete()) {
                    animation.animate(1 / FRAME_RATE);
                    stillAnimating = true;
                }
            }
            canvas.draw();
            canvas.pause(Math.max(0, nextFrameTime - System.currentTimeMillis()));
        } while (stillAnimating);
    }
}
