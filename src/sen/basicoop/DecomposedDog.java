package sen.basicoop;

import comp124graphics.CanvasWindow;
import comp124graphics.Ellipse;
import comp124graphics.Line;
import comp124graphics.Rectangle;

import java.awt.*;

public class DecomposedDog {
    private Color lineColor;
    private CanvasWindow canvas;

    public DecomposedDog() {
        System.out.println("Java is breathing life into a dog");
        this.canvas = new CanvasWindow("Dogs", 600, 400);
        this.lineColor = new Color(0, 0, 0);
    }

    public void buildDog() {
        this.drawBody();
        this.drawHead();
        this.drawLeg(170, 215);
        this.drawLeg(190, 225);
        this.drawLeg(300, 227);
        this.drawLeg(320, 217);
    }

    public void drawBody() {
        Ellipse body = new Ellipse(150, 150, 200, 80);
        this.canvas.add(body);
    }

    public void drawHead() {
        Rectangle head = new Rectangle(300, 100, 50, 50);
        this.canvas.add(head);
        Rectangle snout = new Rectangle(350, 130, 15, 15);
        this.canvas.add(snout);
    }

    public void drawLeg(int x, int y) {
        Line leg = new Line(x, y, x, y + 20);
        this.canvas.add(leg);
    }

    public static void main(String [] main) {
        DecomposedDog dog = new DecomposedDog();
        dog.buildDog();
    }
}
