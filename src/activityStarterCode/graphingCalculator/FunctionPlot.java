package activityStarterCode.graphingCalculator;

import comp127graphics.GraphicsObject;
import comp127graphics.Path;
import comp127graphics.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FunctionPlot {
    private final ParametricFunction function;
    private final List<Point> points;
    private final Path path;

    public FunctionPlot(ParametricFunction function) {
        this.function = function;
        path = new Path();
        points = new ArrayList<>();
        path.setStrokeWidth(3);
    }

    public GraphicsObject getGraphics() {
        return path;
    }

    public void setColor(int index, int pathCount) {
        path.setStrokeColor(
            Color.getHSBColor((index + 0.5f) / pathCount, 1, 0.8f));
    }

    public void recalculate(double n, double xmin, double xmax, double step, Function<Point, Point> coordinateConversion) {
        points.clear();
        for (double x = xmin; x <= xmax; x += step) {
            points.add(
                coordinateConversion.apply(
                    new Point(x, function.evaluate(x, n))));
        }
        path.setVertices(points, false);
    }
}
