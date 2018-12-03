package cantrell.classExamples.treeTraversal;

import comp124graphics.CanvasWindow;
import comp124graphics.GraphicsText;

public class NamesWindow extends CanvasWindow implements Visitor<String> {
    private int y = 10;
    public NamesWindow() {
        super("Names!", 800, 600);
    }

    @Override
    public void visit(String value) {
        add(new GraphicsText(value, 50, y));
        y += 20;
    }
}
