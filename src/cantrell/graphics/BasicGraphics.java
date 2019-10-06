package cantrell.graphics;

import comp127graphics.CanvasWindow;
import comp127graphics.Rectangle;

import java.awt.Color;
import java.util.List;

public class BasicGraphics {
    public static void main(String[] args) {
        CanvasWindow window = new CanvasWindow("Comp 127 🐦🐩🐝", 400, 300);
        Rectangle box = new Rectangle(30, 20, 200, 120);
        box.setFillColor(new Color(247, 51, 117));
        box.setStrokeColor(new Color(20, 0, 152));
        box.setStrokeWidth(30);
        window.add(box);
    }
}
