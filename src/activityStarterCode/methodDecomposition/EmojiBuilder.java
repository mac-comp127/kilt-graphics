package activityStarterCode.methodDecomposition;

import comp124graphics.Arc;
import comp124graphics.CanvasWindow;

import java.awt.*;

public class EmojiBuilder {

    private CanvasWindow canvas;

    public static void main(String[] args){
        EmojiBuilder builder = new EmojiBuilder();

        builder.buildBasicSmiley(0, 0);
    }

    /**
     * Constructor method used to initialize the instance variable
     */
    public EmojiBuilder(){
        canvas = new CanvasWindow("Emoji Builder", 600, 800);
    }

    /**
     * Creates a basic smiley face
     * @param x position of the upper left corner
     * @param y position of the upper left corner
     */
    public void buildBasicSmiley(int x, int y){
        //The keyword "this" refers to the object that is receiving the buildBasicSmiley message.
        //
        this.buildBasicMouth(x+100, y+75, 50, 50);
    }

    /**
     * Draws a simple arc as a smiley mouth
     * @param x position of the arc
     * @param y position of the arc
     * @param width of the ellipse that the arc is a part of
     * @param height of the ellipse that the arc is a part of
     */
    private void buildBasicMouth(int x, int y, int width, int height){
        Arc mouth = new Arc(x, y, width, height, 200.0, 140.0);
        mouth.setStrokeColor(new Color(255, 102, 102));
        mouth.setStrokeWidth(4);
        canvas.add(mouth);
    }
}
