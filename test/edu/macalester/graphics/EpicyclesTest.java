package edu.macalester.graphics;

import java.awt.Color;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EpicyclesTest {
    private static List<Integer> polyStepSizes = List.of(1, 2, 3, 5, 7);
    private CanvasWindow canvas;
    private Random rand = new Random();

    public EpicyclesTest() {
        canvas = new CanvasWindow("Epicycles", 800, 800);
        GraphicsObject spinner = createSpinner(800, 5);
        spinner.setCenter(400, 400);
        canvas.add(spinner);
    }

    private GraphicsObject createSpinner(double size, int levels) {
        GraphicsObject result;
        if (levels <= 0) {
            result = sample(atomMakers).createAtom(size);
            canvas.animate(dt -> result.setScale(result.getScale().rotate(dt)));
        } else {
            GraphicsGroup group = new GraphicsGroup();
            Ellipse background = new Ellipse(0, 0, size, size);
            group.setAnchor(new Point(size / 2, size / 2));
            background.setFillColor(Color.getHSBColor(rand.nextFloat(), rand.nextFloat() * 0.1f, 1));
            background.setStrokeWidth(0.5);
            group.add(background);
            int sides = rand.nextInt(3) + 2;
            Point axis = new Point(size / 2, size / 2);
            double innerRadius = size / (2 * Math.sin(Math.PI / sides) + 2);
            for (int i = 0; i < sides; i++) {
                GraphicsObject subspinner = createSpinner(2 * innerRadius * Math.sin(Math.PI / sides), levels - rand.nextInt(2) - 1);
                subspinner.setCenter(
                    Point.atAngle(2 * Math.PI * i / sides)
                        .scale(innerRadius)
                        .add(axis));
                group.add(subspinner);
            }
            result = group;
        }
        double rotationSpeed = (rand.nextInt(2) * 2 - 1) * 30;
        canvas.animate(dt -> result.rotateBy(rotationSpeed * dt));
        return result;
    }

    private final List<AtomMaker> atomMakers = List.of(
        size -> {
            Image image = new Image("res/foxflower.png");
            image.setMaxWidth(size);
            image.setMaxHeight(size);
            return image;
        },
        size -> {
            Ellipse ellipse = new Ellipse(0, 0, size, size);
            ellipse.setScale(rand.nextDouble(), 1);
            ellipse.setFillColor(Color.getHSBColor(rand.nextFloat(), 1, rand.nextFloat()));
            return ellipse;
        },
        size -> {
            int sides = rand.nextInt(12) + 3, step = sample(polyStepSizes);
            Path path = new Path(
                IntStream.range(0, sides)
                    .mapToDouble(i -> 2 * Math.PI * i * step / sides)
                    .mapToObj(θ -> Point.atAngle(θ).scale(size / 2))
                    .collect(Collectors.toList())
            );
            path.setFillColor(Color.getHSBColor(rand.nextFloat(), rand.nextFloat(), 1));
            return path;
        }
    );

    private <T> T sample(List<T> items) {
        return items.get(rand.nextInt(items.size()));
    }

    public static void main(String[] args) {
        new EpicyclesTest();
        FrameRateReporter.enabled = true;
    }
}

interface AtomMaker {
    GraphicsObject createAtom(double size);
}
