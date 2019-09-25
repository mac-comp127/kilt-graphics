package sen.graphics;

import comp127graphics.*;
import comp127graphics.Rectangle;

import java.awt.*;

public class CatExample {
    public static void main(String[] args) {
        CanvasWindow cw = new CanvasWindow("Cat", 500, 500);

        GraphicsGroup cat = new GraphicsGroup(0, 0);

        Rectangle face = new Rectangle(0, 0, 200, 300);
        face.setStrokeColor(Color.BLACK);
        face.setStrokeWidth(4);
        cat.add(face);

        Ellipse leye = new Ellipse(25, 100, 50, 50);
        leye.setStrokeColor(Color.BLACK);
        leye.setStrokeWidth(1);
        cat.add(leye);

        Ellipse reye = new Ellipse(100, 100, 50, 50);
        reye.setStrokeColor(Color.BLACK);
        reye.setStrokeWidth(1);
        cat.add(reye);

        Ellipse mouth = new Ellipse(25, 250, 120, 30);
        mouth.setStrokeColor(Color.BLACK);
        mouth.setStrokeWidth(1);
        cat.add(mouth);

        Line whisker1 = new Line(25, 150, 50, 175);
        whisker1.setStrokeColor(Color.BLACK);
        whisker1.setStrokeWidth(1);
        cat.add(whisker1);

        cw.add(cat);

        for (int i = 0; i < 500; i++) {
            cat.setPosition(i, i);
            cw.pause(100);
            cw.draw();
        }
        cw.draw();
    }
}
