package comp127graphics;

import java.util.List;

/**
 * @deprecated Use Path instead
 */
public class Polygon extends Path {
    public Polygon(List<Point> points) {
        super(points);
    }

    public Polygon(Point... points) {
        super(points);
    }
}
