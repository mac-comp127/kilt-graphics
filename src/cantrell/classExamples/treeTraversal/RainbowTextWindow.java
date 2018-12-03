package cantrell.classExamples.treeTraversal;

import java.awt.Color;

import comp124graphics.CanvasWindow;
import comp124graphics.GraphicsText;

public class RainbowTextWindow extends CanvasWindow implements Processor<String> {

    private int nextY = 50;

    public RainbowTextWindow(String title) {
        super(title, 800, 600);
    }

    @Override
    public void process(String value) {
        GraphicsText text = new GraphicsText(
            value,
            (float) (50 + Math.cos(nextY / 30.0f) * 30),
            nextY);
        text.setStrokeColor(Color.getHSBColor(nextY / 173.2f, 1, 1));
        add(text);
        nextY += 20;
    }
}
