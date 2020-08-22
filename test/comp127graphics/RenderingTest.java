package comp127graphics;

import static org.junit.jupiter.api.Assertions.fail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(RenderingTestHandler.class)
@Test
public @interface RenderingTest {
}

class RenderingTestHandler implements AfterTestExecutionCallback {
    private final double totalDiffFailureThreshold = 0;

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        var suite = getGraphicsObjectTestSuite(context);
        var gobj = suite.getGraphicsObject();
        var actualImage = createImage(suite);
        gobj.draw(actualImage.createGraphics());
        File actualFile = getImageFile(context, "actual");
        ImageIO.write(actualImage, "png", actualFile);

        File expectedFile = getImageFile(context, "expected");
        if (!expectedFile.exists()) {
            System.err.println("WARNING: Using generated image from new RenderingTest as the expected image for future runs: " + expectedFile);
            ImageIO.write(actualImage, "png", expectedFile);
            return;
        }
        
        BufferedImage expectedImage = ImageIO.read(expectedFile);
        if (expectedImage.getWidth() != actualImage.getWidth() || expectedImage.getHeight() != actualImage.getHeight()) {
            fail("Image size mismatch: expected (" + expectedImage.getWidth() + "," + expectedImage.getHeight()
                + "), but got (" + actualImage.getWidth() + "," + actualImage.getHeight() + ")");
        }

        BufferedImage deltaImage = createImage(suite);
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

        File deltaFile = getImageFile(context, "(delta)");
        if (totalDiff > 0) {
            ImageIO.write(deltaImage, "png", deltaFile);
        } else {
            deltaFile.delete();            
        }

        if (totalDiff > totalDiffFailureThreshold) {
            fail("image does not match expected (difference factor of " + totalDiff + " exceeds threshold of " + totalDiffFailureThreshold
                + ")\nFor visual comparison, see:"
                + "\n    " + expectedFile
                + "\n    " + actualFile
                + "\n    " + deltaFile);
        }
    }

    private int diffCurve(int diff, double gamma) {
        return Math.min(255, Math.max(0,
            (int) Math.ceil(
                Math.pow(diff / 255.0, gamma) * 255.0)));
    }

    private GraphicsObjectTestSuite getGraphicsObjectTestSuite(ExtensionContext context) {
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

    private File getImageFile(ExtensionContext context, String variant) {
        File suiteDir = new File(getFixturesDir(), context.getRequiredTestClass().getSimpleName());
        if (!suiteDir.exists()) {
            suiteDir.mkdir();
        }
        return new File(suiteDir, context.getRequiredTestMethod().getName() + "-" + variant + ".png");
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
}
