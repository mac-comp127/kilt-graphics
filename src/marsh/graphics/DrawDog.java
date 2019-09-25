package marsh.graphics;

import comp127graphics.CanvasWindow;
import comp127graphics.Ellipse;
import comp127graphics.GraphicsText;
import comp127graphics.Line;

import java.awt.*;

public class DrawDog {

    public static void main(String[] args) {
        CanvasWindow cw = new CanvasWindow("Dog", 500, 500);

        Ellipse ear1 = new Ellipse(140, 80, 20, 30);
        ear1.setFillColor(Color.lightGray);
        cw.add(ear1);

        Ellipse head = new Ellipse(100, 100, 70, 50);
        head.setFillColor(Color.lightGray);
        cw.add(head);

        Ellipse muzzle = new Ellipse(90, 110, 30, 30);
        muzzle.setFillColor(Color.lightGray);
        cw.add(muzzle);

        Ellipse body = new Ellipse(150, 150, 170, 80);
        body.setFillColor(Color.lightGray);
        cw.add(body);

        Ellipse ear2 = new Ellipse(150, 85, 20, 30);
        ear2.setFillColor(Color.lightGray);
        cw.add(ear2);

        Ellipse nose = new Ellipse(87, 115, 10, 10);
        nose.setFillColor(Color.black);
        cw.add(nose);

        Line tail = new Line(320, 190, 360, 150);
        tail.setStrokeWidth(5);
        tail.setStrokeColor(Color.GRAY);
        cw.add(tail);

        Line fleg1 = new Line(170, 220, 150, 270);
        fleg1.setStrokeWidth(5);
        fleg1.setStrokeColor(Color.GRAY);
        cw.add(fleg1);

        Line fleg2 = new Line(180, 230, 165, 270);
        fleg2.setStrokeWidth(5);
        fleg2.setStrokeColor(Color.GRAY);
        cw.add(fleg2);

        Line bleg1 = new Line(290, 225, 305, 270);
        bleg1.setStrokeWidth(5);
        bleg1.setStrokeColor(Color.GRAY);
        cw.add(bleg1);

        Line bleg2 = new Line(305, 215, 325, 270);
        bleg2.setStrokeWidth(5);
        bleg2.setStrokeColor(Color.GRAY);
        cw.add(bleg2);

        GraphicsText bark = new GraphicsText("bark", 250, 300);
        cw.add(bark);

        cw.draw();
    }
}
