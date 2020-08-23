package comp127graphics.testsupport;

import static org.junit.jupiter.api.Assertions.fail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.extension.ExtensionContext;

import comp127graphics.GraphicsObject;
import comp127graphics.Point;

public class ImageComparison {
    private final ExtensionContext context;
    private final double totalDiffFailureThreshold;  // could allow customization in annotation
    private final String variant;
    private final Renderer renderer;

    public ImageComparison(ExtensionContext context, String variant, Renderer renderer, double totalDiffFailureThreshold) {
        this.context = context;
        this.variant = variant;
        this.renderer = renderer;
        this.totalDiffFailureThreshold = totalDiffFailureThreshold;
    }

    public void compare() throws IOException {
        var actualImage = createTestImage();
        renderer.render(actualImage, getGraphicsObjectTestSuite().getGraphicsObject());
        File actualFile = getImageFile("actual");
        writeImage(actualImage, actualFile);

        File expectedFile = getImageFile("expected");
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

        BufferedImage deltaImage = createTestImage();
        double totalDiff = compareImages(expectedImage, actualImage, deltaImage);

        File deltaFile = getImageFile("(delta)");
        if (totalDiff > 0) {
            writeImage(deltaImage, deltaFile);
        } else if (deltaFile.exists()) {
            if (!deltaFile.delete()) {
                throw new IOException("can't delete delta image file: " + deltaFile);
            }
        }

        if (totalDiff > totalDiffFailureThreshold) {
            fail(variant + " image does not match expected (difference factor of " + totalDiff
                + " exceeds threshold of " + totalDiffFailureThreshold + ")"
                + "\nFor visual comparison, see:"
                + "\n    " + expectedFile
                + "\n    " + actualFile
                + "\n    " + deltaFile);
        }
    }

    private GraphicsObjectTestSuite getGraphicsObjectTestSuite() {
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

    private File getImageFile(String role) {
        File suiteDir = new File(getFixturesDir(), context.getRequiredTestClass().getSimpleName());
        if (!suiteDir.exists()) {
            suiteDir.mkdir();
        }
        String baseName = context.getRequiredTestMethod().getName();
        if (variant != null) {
            baseName += "-" + variant;
        }
        return new File(suiteDir, baseName + "." + role + ".png");
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

    private BufferedImage createTestImage() {
        Point imageSize = getGraphicsObjectTestSuite().getCanvasSize();
        return new BufferedImage(
            (int) Math.round(imageSize.getX()),
            (int) Math.round(imageSize.getY()),
            BufferedImage.TYPE_INT_ARGB);
    }

    private void writeImage(BufferedImage image, File file) throws IOException {
        if (!ImageIO.write(image, "png", file)) {
            throw new IOException("Cannot write image to " + file);
        }
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

    public interface Renderer {
        /**
         * Renders an image of the given graphics objects for the purpose of comparing against previous runs.
         * 
         * @return True if the method rendered the graphics, or false if this renderer does not apply and the
        *          test system should skip this test case.
         */
        void render(BufferedImage image, GraphicsObject gobjToRender);
    }
}
