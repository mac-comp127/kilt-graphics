package cantrell.animations.starterCode;

import comp127graphics.*;

import java.awt.Color;
import java.util.List;

public class AnimationsTest {
    private static final double GRAVITY = 2000;

    public static void main(String[] args) {
        CanvasWindow canvas = new CanvasWindow("Animations", 600, 400);

        Ellipse obj0 = new Ellipse(50, 300, 60, 60);
        obj0.setStrokeColor(new Color(0x571B7A));
        obj0.setFillColor(new Color(0x98DF609E, true));
        obj0.setStrokeWidth(4);
        canvas.add(obj0);

        Polygon obj1 = new Polygon(
            new Point(450, 360),
            new Point(500, 290),
            new Point(550, 360));
        obj1.setStrokeColor(new Color(0x335E7A));
        obj1.setFillColor(new Color(0x834139DF, true));
        obj1.setStrokeWidth(3);
        canvas.add(obj1);

        Rectangle obj2 = new Rectangle(290, 190, 20, 20);
        obj2.setStrokeColor(new Color(0xE39B24));
        obj2.setFillColor(new Color(0x83FFFF00, true));
        obj2.setStrokeWidth(2);
        canvas.add(obj2);

        Animator animator = new Animator(canvas);

        animator.add(new LinearAnimation(obj0, 10, -100, 2.5));
        animator.add(new LinearAnimation(obj1, -10, -100, 3.5));

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