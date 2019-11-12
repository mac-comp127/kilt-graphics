package comp127graphics;

import comp127graphics.ui.Button;
import comp127graphics.ui.TextField;

import java.awt.Color;
import java.util.List;

public class VisualTest {
    // Graphics should still appear if main() exits without calling draw()
    private static final boolean IMMEDIATE_EXIT = false;

    private static boolean hitTestVisualization = false;
    private static double t = 0;

    public static void main(String[] args) {
        GraphicsGroup group = new GraphicsGroup();

        group.add(
            new Rectangle(
                110, 40,
                70, 21));

        Ellipse bigDot = new Ellipse(30, 20, 47, 53);
        bigDot.setStrokeWidth(10);
        bigDot.setFillColor(Color.CYAN);
        group.add(bigDot);

        GraphicsText text = new GraphicsText("Ahoy", 20, 60);
        text.setFont("Avenir Next", FontStyle.BOLD_ITALIC, 42);
        text.setFillColor(Color.MAGENTA);
        group.add(text);

        Button button = new Button("zongle!");
        group.add(button);
        button.onClick(() -> System.out.println("zongle pressed"));
        button.setCenter(100, 10);

        TextField field = new TextField();
        field.onChange(s -> System.out.println("text field: " + s));
        field.setCenter(100, 60);
        group.add(field);

        group.add(
            new Path(List.of(
                new Point(23, 34),
                new Point(34, 173),
                new Point(243, 23),
                new Point(73, 223))));

        group.add(
            new Line(33, 166, 66, 133));

        group.add(
            new Arc(
                120, 50,
                100, 100,
                73, 259));

        CanvasWindow canvas = new CanvasWindow("comp127graphics visual test", 400, 300);
        canvas.add(group);

        double pause = 1000;

        canvas.onMouseMove((event) -> {
            text.setText(event.getPosition().toString());
        });

        canvas.onClick((event) -> {
            hitTestVisualization = !hitTestVisualization;
        });

        canvas.onMouseMove((event) -> {
            text.moveBy(event.getDelta());
        });

        canvas.onDrag((event) -> {
            bigDot.moveBy(event.getDelta());
        });

        GraphicsGroup dots = new GraphicsGroup();
        canvas.add(dots);

        //noinspection InfiniteLoopStatement
        canvas.animate(() -> {
            group.setPosition(
                (Math.cos(t * Math.E ) / 2 + 0.5) * (canvas.getWidth() / 2.0),
                (Math.sin(t * Math.PI) / 2 + 0.5) * (canvas.getHeight() / 2.0));

            dots.removeAll();
            if(hitTestVisualization) {
                int step = 8;
                for(int x = -step; x < canvas.getWidth() + step; x += step) {
                    for(int y = -step; y < canvas.getHeight() + step; y += step) {
                        double r = 4;
                        Ellipse dot = new Ellipse(x - r/2, y - r/2, r, r);
                        Object elem = canvas.getElementAt(x, y);
                        if(elem != null) {
                            dot.setFillColor(new Color(System.identityHashCode(elem) & 0x00FFFFFF | 0xCC000000, true));
                            dot.setStroked(false);
                        } else {
                            dot.setStrokeColor(new Color(0x33666666, true));
                            dot.setStrokeWidth(0.5f);
                        }
                        dots.add(dot);
                    }
                }
            }
            t += 0.007;
        });
    }
}
