package jackson.section1.graphicsExample;

import comp124graphics.CanvasWindow;
import comp124graphics.Rectangle;

import java.awt.*;

public class BasicShapes {

    public static void main(String[] args){
        CanvasWindow canvas = new CanvasWindow("Graphics Example", 600, 800);

        Rectangle rect = new Rectangle(100, 100, 200, 200);
        rect.setFilled(true);
        rect.setFillColor(new Color(255, 0,0));
        canvas.add(rect);
    }
}
