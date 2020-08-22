package comp127graphics;

import static org.junit.jupiter.api.Assertions.fail;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.extension.ExtensionContext;

public class ImageComparison {
    private final ExtensionContext context;
    private final double totalDiffFailureThreshold = 0;  // could allow customization in annotation

    public ImageComparison(ExtensionContext context) {
        this.context = context;
    }

    public void compare() throws IOException {
        var suite = getGraphicsObjectTestSuite(context);
        var gobj = suite.getGraphicsObject();

        var actualImage = createImage(suite);
        Graphics2D g = actualImage.createGraphics();
        gobj.draw(g);
        visualizeBounds(g, gobj);
        File actualFile = getImageFile(context, "actual");
        writeImage(actualImage, actualFile);

        File expectedFile = getImageFile(context, "expected");
        if (!expectedFile.exists()) {
            System.err.println("WARNING: Using generated image from new RenderingTest as the expected image for future runs: " + expectedFile);
            writeImage(actualImage, expectedFile);
            return;
        }
        
        BufferedImage expectedImage = ImageIO.read(expectedFile);
        if (expectedImage.getWidth() != actualImage.getWidth() || expectedImage.getHeight() != actualImage.getHeight()) {
            fail("Image size mismatch: expected (" + expectedImage.getWidth() + "," + expectedImage.getHeight()
                + "), but got (" + actualImage.getWidth() + "," + actualImage.getHeight() + ")");
        }

        BufferedImage deltaImage = createImage(suite);
        double totalDiff = compareImages(expectedImage, actualImage, deltaImage);

        File deltaFile = getImageFile(context, "(delta)");
        if (totalDiff > 0) {
            writeImage(deltaImage, deltaFile);
        } else if (deltaFile.exists()) {
            if (!deltaFile.delete()) {
                throw new IOException("can't delete delta image file: " + deltaFile);
            }
        }

        if (totalDiff > totalDiffFailureThreshold) {
            fail("image does not match expected (difference factor of " + totalDiff
                + " exceeds threshold of " + totalDiffFailureThreshold + ")"
                + "\nFor visual comparison, see:"
                + "\n    " + expectedFile
                + "\n    " + actualFile
                + "\n    " + deltaFile);
        }
    }

    private static GraphicsObjectTestSuite getGraphicsObjectTestSuite(ExtensionContext context) {
        var testInstance = context.getRequiredTestInstance();
        if (!(testInstance instanceof GraphicsObjectTestSuite)) {
            fail(context.getRequiredTestMethod().getName()
                + " cannot be a @RenderingTest, because "
                + context.getRequiredTestClass().getSimpleName()
                + " does not implement "
                + GraphicsObjectTestSuite.class.getSimpleName());
        }
        return (GraphicsObjectTestSuite) testInstance;
    }

    private File getImageFile(ExtensionContext context, String role) {
        File suiteDir = new File(getFixturesDir(), context.getRequiredTestClass().getSimpleName());
        if (!suiteDir.exists()) {
            suiteDir.mkdir();
        }
        return new File(suiteDir, context.getRequiredTestMethod().getName() + "-" + role + ".png");
    }

    private File getFixturesDir() {
        var fixtureDir = Paths.get(".").toAbsolutePath().normalize()
            .resolve("test")
            .resolve("fixtures")
            .toFile();
        if (!fixtureDir.isDirectory()) {
            throw new RuntimeException("Cannot find test fixture directory at " + fixtureDir);
        }
        return fixtureDir;
    }

    private BufferedImage createImage(GraphicsObjectTestSuite suite) {
        return new BufferedImage(
                (int) Math.round(suite.getCanvasSize().getX()),
                (int) Math.round(suite.getCanvasSize().getY()),
                BufferedImage.TYPE_INT_ARGB);
    }

    private void writeImage(BufferedImage image, File file) throws IOException {
        if (!ImageIO.write(image, "png", file)) {
            throw new IOException("Cannot write image to " + file);
        }
    }

    private void visualizeBounds(Graphics2D g, GraphicsObject gobj) {
        var bounds = gobj.getBounds();
        var cropMarks = new Path2D.Double(GeneralPath.WIND_EVEN_ODD);
        for(int side = 0; side < 2; side++) {
            for (double y : List.of(bounds.getMinY(), bounds.getMaxY() - 1)) {
                double x = bounds.getMinX() + bounds.getWidth() * side;
                cropMarks.moveTo(x + (side * 2 - 1) * 16, y);
                cropMarks.lineTo(x + (side * 2 - 1) * 4, y);
            }
            for (double x : List.of(bounds.getMinX(), bounds.getMaxX() - 1)) {
                double y = bounds.getMinY() + bounds.getHeight() * side;
                cropMarks.moveTo(x, y + (side * 2 - 1) * 16);
                cropMarks.lineTo(x, y + (side * 2 - 1) * 4);
            }
        }
        g.setStroke(new BasicStroke(1f));
        g.setPaint(new Color(0, 0, 0, 64));
        g.draw(cropMarks);
    }

    private double compareImages(BufferedImage expectedImage, BufferedImage actualImage, BufferedImage deltaImage) {
        double totalDiff = 0;
        for (int y = 0; y < deltaImage.getHeight(); y++) {
            for (int x = 0; x < deltaImage.getWidth(); x++) {
                int expectedPix = expectedImage.getRGB(x, y);
                int actualPix = actualImage.getRGB(x, y);
                int maxDiff = 0;
                for (int channel = 0; channel < 4; channel++) {
                    maxDiff = Math.max(maxDiff,
                        Math.abs((expectedPix & 0xFF) - (actualPix & 0xFF)));
                    expectedPix >>= 8;
                    actualPix >>= 8;
                }
                totalDiff += Math.pow(maxDiff / 255.0, 2);
                deltaImage.setRGB(x, y, 0xFF000000
                    | diffCurve(maxDiff, 0.03) << 16
                    | diffCurve(maxDiff, 1) << 8
                    | diffCurve(maxDiff, 4));
            }
        }
        return totalDiff;
    }

    /**
     * Helps create color curves in different color channels to highlight both small and large differences.
     */
    private int diffCurve(int diff, double gamma) {
        return Math.min(255, Math.max(0,
            (int) Math.ceil(
                Math.pow(diff / 255.0, gamma) * 255.0)));
    }
}