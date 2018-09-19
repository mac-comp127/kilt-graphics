package cantrell.classExamples;

import java.awt.Color;

import comp124graphics.CanvasWindow;
import comp124graphics.Ellipse;
import comp124graphics.Line;

public class CollatzGraph {

    private int maxSeed;
    private float horizontalScale, verticalScale;
    private CanvasWindow canvas = new CanvasWindow("Collatz", 600, 800);

    public static void main(String[] args) {
        CollatzGraph graph0 = new CollatzGraph(100);
        graph0.plotDots();
        graph0.plotConnectingLine();

        CollatzGraph graph1 = new CollatzGraph(4000);
        graph1.plotDots();
        graph1.plotConnectingLine();
    }

    public CollatzGraph(int maxSeed) {
        this.maxSeed = maxSeed;
        this.horizontalScale = (float) canvas.getWidth() / maxSeed;
        this.verticalScale =
            (float) canvas.getHeight() /
                CollatzIterator.collatzSequenceLength(
                    CollatzIterator.recordSettingSeed(maxSeed));
    }

    private void plotDots() {
        for(int seed = 1; seed < maxSeed; seed++) {
            int sequenceLength = CollatzIterator.collatzSequenceLength(seed);
            Ellipse dot = new Ellipse(seed * horizontalScale - 2.5, sequenceLength * verticalScale - 2.5, 5, 5);
            canvas.add(dot);
        }
    }

    private void plotConnectingLine() {
        int n = CollatzIterator.recordSettingSeed(maxSeed);
        int y = CollatzIterator.collatzSequenceLength(n);
        while(n > 1) {
            int nextN = CollatzIterator.nextCollatz(n);
            Line line = new Line(n * horizontalScale, y * verticalScale, nextN * horizontalScale, (y - 1) * verticalScale);
            line.setStrokeColor(Color.BLUE);
            canvas.add(line);
            n = nextN;
            y--;
        }
    }
}













