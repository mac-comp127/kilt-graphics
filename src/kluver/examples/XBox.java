package kluver.examples;

import comp124graphics.GraphicsGroup;
import comp124graphics.Line;
import comp124graphics.Rectangle;

public class XBox extends GraphicsGroup {
    public XBox(int x, int y, int width, int height) {
        super(x, y);
        Rectangle r = new Rectangle(0, 0, width, height);
        this.add(r);
        Line l1 = new Line(0, 0, width, height);
        this.add(l1);
        Line l2 = new Line(width, 0, 0, height);
        this.add(l2);
    }
}
