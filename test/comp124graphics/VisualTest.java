package comp124graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.List;

public class VisualTest {
    public static void main(String[] args) {
        GraphicsGroup group = new GraphicsGroup();

        group.add(
            new Rectangle(
                110, 40,
                70, 21));

        Ellipse bigDot = new Ellipse(30, 20, 47, 53);
        bigDot.setStrokeWidth(10);
        bigDot.setFillColor(Color.CYAN);
        bigDot.setFilled(true);
        group.add(bigDot);

        GraphicsText text = new GraphicsText("Ahoy", 20, 60);
        text.setFont(new Font("Avenir Next", 0, 42));
        text.setStrokeColor(Color.MAGENTA);
        group.add(text);

        group.add(
            new Polygon(List.of(
                new Point2D.Double(23, 34),
                new Point2D.Double(34, 173),
                new Point2D.Double(243, 23),
                new Point2D.Double(73, 223))));

        group.add(
            new Line(33, 166, 66, 133));

        CanvasWindow canvas = new CanvasWindow("comp124graphics visual test", 400, 300);
        canvas.add(group);
        group.setPosition(170, 20);

        canvas.pause(100);

        for(int x = 0; x < canvas.getWidth(); x += 4) {
            for(int y = 0; y < canvas.getHeight(); y += 4) {
                double r = 3;
                Ellipse dot = new Ellipse(x - r/2, y - r/2, r, r);
                Object elem = group.getElementAt(x, y);
                if(elem != null) {
                    dot.setFillColor(new Color(System.identityHashCode(elem) & 0x00FFFFFF | 0xCC000000, true));
                    dot.setFilled(true);
                    dot.setStroked(false);
                } else {
                    dot.setStrokeColor(new Color(0x33666666, true));
                    dot.setStrokeWidth(0.5f);
                }
                canvas.add(dot);
            }
        }
    }
}
