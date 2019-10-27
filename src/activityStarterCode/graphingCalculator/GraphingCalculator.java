package activityStarterCode.graphingCalculator;

import comp127graphics.CanvasWindow;
import comp127graphics.Line;
import comp127graphics.Point;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class GraphingCalculator {
    private final CanvasWindow canvas;
    private final List<FunctionPlot> plots;
    private Point origin;
    private double scale;
    private double xmin, xmax, step;
    private double parameter;
    private Line xaxis, yaxis;

    public GraphingCalculator(int width, int height) {
        canvas = new CanvasWindow("Graphing Calculator", width, height);
        plots = new ArrayList<>();

        origin = canvas.getCenter();
        scale = (canvas.getWidth() + canvas.getHeight()) / 8.0;

        xaxis = createAxisLine();
        yaxis = createAxisLine();

        recalculateGrid();
    }

    public void show(SimpleFunction function) {
        show((x, n) -> function.evaluate(x));
    }

    public void show(ParametricFunction function) {
        FunctionPlot plot = new FunctionPlot(function);
        plots.add(plot);
        canvas.add(plot.getGraphics());

        recolorPlots();

        recalculateAll(plot);
    }

    public double getParameter() {
        return parameter;
    }

    public void setParameter(double parameter) {
        this.parameter = parameter;
        recalculateAll();
        canvas.draw();
    }

    private Line createAxisLine() {
        Line axis = new Line(0, 0, 0, 0);
        axis.setStrokeWidth(0.25);
        axis.setStrokeColor(new Color(0xA1A1A1));
        canvas.add(axis);
        return axis;
    }

    private void recalculateGrid() {
        xaxis.setStartPosition(0, origin.getY());
        xaxis.setEndPosition(canvas.getWidth(), origin.getY());
        yaxis.setStartPosition(origin.getX(), 0);
        yaxis.setEndPosition(origin.getX(), canvas.getHeight());

        xmin = convertToEquationCoordinates(Point.ORIGIN).getX();
        xmax = convertToEquationCoordinates(new Point(canvas.getWidth(), 0)).getX();
        step = 2 / scale;
    }

    private void recalculateAll() {
        plots.forEach(this::recalculateAll);
    }

    private void recalculateAll(FunctionPlot plot) {
        plot.recalculate(parameter, xmin, xmax, step, this::convertToScreenCoordinates);
    }

    private Point convertToScreenCoordinates(Point equationPoint) {
        return equationPoint.scale(scale, -scale).add(origin);
    }

    private Point convertToEquationCoordinates(Point screenPoint) {
        return screenPoint.subtract(origin).scale(1 / scale, -1 / scale);
    }

    private void recolorPlots() {
        int index = 0;
        for (FunctionPlot plot : plots) {
            plot.setColor(index, plots.size());
            index++;
        }
    }
}
