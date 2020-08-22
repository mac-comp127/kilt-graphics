package comp127graphics;

public interface GraphicsObjectTestSuite {
    GraphicsObject getGraphicsObject();

    default Point getCanvasSize() {
        return new Point(100, 100);
    }
}
