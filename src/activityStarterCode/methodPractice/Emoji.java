package activityStarterCode.methodPractice;

import comp127graphics.Arc;
import comp127graphics.CanvasWindow;

import java.awt.*;

public class Emoji {

    public static void main(String[] args){

        CanvasWindow canvas = new CanvasWindow("Emoji", 200, 200);

        Arc mouth = new Arc(100.0, 75.0, 50, 50, 200.0, 140.0);
        mouth.setStrokeColor(new Color(255, 102, 102));
        mouth.setStrokeWidth(4);
        canvas.add(mouth);
    }
}
