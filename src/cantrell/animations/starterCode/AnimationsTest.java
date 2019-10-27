package cantrell.animations.starterCode;

import comp127graphics.*;

import java.awt.Color;

public class AnimationsTest {
    private static final double GRAVITY = 2000;

    public static void main(String[] args) {
        CanvasWindow canvas = new CanvasWindow("Animations", 600, 400);

        Ellipse pinkCircle = new Ellipse(50, 300, 60, 60);
        pinkCircle.setStrokeColor(new Color(0x571B7A));
        pinkCircle.setFillColor(new Color(0x98DF609E, true));
        pinkCircle.setStrokeWidth(4);
        canvas.add(pinkCircle);

        Path blueTriangle = new Path(
            new Point(450, 360),
            new Point(500, 290),
            new Point(550, 360));
        blueTriangle.setStrokeColor(new Color(0x335E7A));
        blueTriangle.setFillColor(new Color(0x834139DF, true));
        blueTriangle.setStrokeWidth(3);
        canvas.add(blueTriangle);

        Rectangle yellowSquare = new Rectangle(290, 190, 20, 20);
        yellowSquare.setStrokeColor(new Color(0xE39B24));
        yellowSquare.setFillColor(new Color(0x83FFFF00, true));
        yellowSquare.setStrokeWidth(2);
        canvas.add(yellowSquare);

        Animator animator = new Animator(canvas);

        animator.add(new LinearAnimation(pinkCircle, 70, -100, 1.0));
        animator.add(new LinearAnimation(blueTriangle, -40, -100, 2.5));
        animator.add(new LinearAnimation(yellowSquare, 0, 150, 0.5));

        animator.run();
    }
}




/*

        animator.add(new WanderingAnimation(obj0, 30, 100));
        animator.add(new WanderingAnimation(obj1, 30, 100));
        animator.add(new WanderingAnimation(obj2, 50, 400));




        animator.add(GravityAnimation.hop(obj0, 50, 200, GRAVITY));
        animator.add(GravityAnimation.hop(obj1, -50, 240, GRAVITY));
        animator.add(GravityAnimation.fall(obj2, GRAVITY));

 */