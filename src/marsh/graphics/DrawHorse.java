package marsh.graphics;

import comp127graphics.CanvasWindow;
import comp127graphics.GraphicsText;
import comp127graphics.Line;
import comp127graphics.Rectangle;
import org.w3c.dom.css.Rect;

public class DrawHorse {

    public static void main(String[] args) {

        CanvasWindow cw = new CanvasWindow("Horse", 500, 500);

        Rectangle head = new Rectangle(400, 100, 80, 35);
        cw.add(head);

        Rectangle ear1 = new Rectangle(400, 90, 7, 15);
        cw.add(ear1);

        Rectangle ear2 = new Rectangle(410, 90, 7, 15);
        cw.add(ear2);

        Rectangle body = new Rectangle(200, 200, 200, 80);
        cw.add(body);

        Line leg1 = new Line(395, 280, 395, 370);
        cw.add(leg1);

        Line leg2 = new Line(380, 280, 380, 370);
        cw.add(leg2);

        Line leg3 = new Line(200, 280, 200, 370);
        cw.add(leg3);

        Line leg4 = new Line(220, 280, 220, 370);
        cw.add(leg4);

        Line tail = new Line(200, 200, 100, 200);
        cw.add(tail);
        Line tail2 = new Line(200, 200, 100, 195);
        cw.add(tail2);
        Line tail3 = new Line(200, 200, 100, 210);
        cw.add(tail3);
        Line tail4 = new Line(200, 200, 100, 205);
        cw.add(tail4);

        Line neckFront = new Line(400, 240, 420, 135);
        cw.add(neckFront);

        Line neckBack = new Line(360, 200, 400, 110);
        cw.add(neckBack);

        GraphicsText neigh = new GraphicsText("neigh", 100, 100);
        cw.add(neigh);

        cw.draw();
    }
}
