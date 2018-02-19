package kluver.examples;

import comp124graphics.CanvasWindow;
import comp124graphics.Ellipse;
import comp124graphics.GraphicsGroup;
import comp124graphics.Rectangle;

public class XBoxMain {

    public  static void main(String[] args) {
        CanvasWindow canvas = new CanvasWindow("Test", 500,500);

        GraphicsGroup g = new GraphicsGroup(400,0);
        Ellipse e = new Ellipse(0,400, 100, 100);
        g.add(e);
        canvas.add(g);
    }
}
