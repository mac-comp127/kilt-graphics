package cantrell.animations.completed;

import comp127graphics.*;

import java.awt.Color;
import java.util.List;

public class AnimationsTest {
    private static final double GRAVITY = 2000;

    public static void main(String[] args) {
        CanvasWindow canvas = new CanvasWindow("Animations", 600, 400);

        Ellipse pinkCircle = new Ellipse(50, 300, 60, 60);
        pinkCircle.setStrokeColor(new Color(0x571B7A));
        pinkCircle.setFillColor(new Color(0x98DF609E, true));
        pinkCircle.setStrokeWidth(4);
        canvas.add(pinkCircle);

        Polygon blueTriangle = new Polygon(
            new Point(450, 360),
            new Point(500, 290),
            new Point(550, 360));
        blueTriangle.setStrokeColor(new Color(0x335E7A));
        blueTriangle.setFillColor(new Color(0x834139DF, true));
        blueTriangle.setStrokeWidth(3);
        canvas.add(blueTriangle);

        GraphicsGroup swarm = new GraphicsGroup();
        swarm.setPosition(290, 190);
        canvas.add(swarm);

        Animator animator = new Animator(canvas);

        for (int n = 0; n < 150; n++) {
            Rectangle yellowBox = new Rectangle(-1.5, -1.5, 3, 3);
            yellowBox.setStrokeColor(new Color(0xE39B24));
            yellowBox.setFillColor(new Color(0x83FFFF00, true));
            yellowBox.setStrokeWidth(0.5f);
            swarm.add(yellowBox);

            animator.add(new WanderingAnimation(yellowBox, 8, 200));
        }

        animator.add(
            new AnimationSequence(List.of(

                new SimultaneousAnimation(List.of(
                    new LinearAnimation(pinkCircle, 10, -100, 0.5),
                    new LinearAnimation(blueTriangle, -10, -100, 0.5))),

                GravityAnimation.bounce(pinkCircle, 50, 200, GRAVITY, 0.7, 12),
                GravityAnimation.bounce(blueTriangle, -50, 240, GRAVITY, 0.7, 12),

                new SimultaneousAnimation(List.of(
                    new AnimationSequence(List.of(
                        new AnimationTimeLimit(2.1,
                            new WanderingAnimation(pinkCircle, 30, 100)),
                        GravityAnimation.fall(pinkCircle, GRAVITY))),
                    new AnimationSequence(List.of(
                        new AnimationTimeLimit(2.3,
                            new WanderingAnimation(blueTriangle, 30, 100)),
                        GravityAnimation.fall(blueTriangle, GRAVITY))),
                    new AnimationSequence(List.of(
                        new AnimationTimeLimit(2.5,
                            new WanderingAnimation(swarm, 100, 200)),
                        GravityAnimation.fall(swarm, GRAVITY))))))));

        animator.run();
    }
}
