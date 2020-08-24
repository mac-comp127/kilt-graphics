package comp127graphics.testsupport;

import static org.junit.jupiter.api.Assertions.fail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

/**
 * Renders the GraphicsObject from a GraphicsObjectTestSuite to an image and compares it with a
 * saved image in the project's `test/testImages` directory. The first time a test with a given
 * name runs, this class saves the rendered image as the expected one. After that, when the
 * expected image already exists, this class compared the newly rendered image to the old
 * expected one. If the two images differ at all, this class emits a `(delta)` file
 * highlighting the differing pixels. If the sum of squares difference between the images exceeds
 * the given threshold, the test suite fails.
 */
public class ImageComparison {
    private final double totalDiffFailureThreshold;  // could allow customization in annotation
    private final String groupName, imageName;
    private final BufferedImage actualImage;

    public ImageComparison(
            String groupName,
            String testName,
            BufferedImage actualImage,
            double totalDiffFailureThreshold) {
        this.groupName = groupName;
        this.imageName = testName;
        this.actualImage = actualImage;
        this.totalDiffFailureThreshold = totalDiffFailureThreshold;
    }

    public void compare() throws IOException {
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

        BufferedImage deltaImage = new BufferedImage(actualImage.getWidth(), actualImage.getHeight(), BufferedImage.TYPE_INT_RGB);
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
            fail("Actual " + imageName + " image does not match expected"
                + " (difference factor of " + totalDiff
                + " exceeds threshold of " + totalDiffFailureThreshold + ")"
                + "\nFor visual comparison, see:"
                + "\n    " + expectedFile
                + "\n    " + actualFile
                + "\n    " + deltaFile);
        }
    }

    private File getImageFile(String role) {
        File suiteDir = new File(getTestImagesDir(), groupName);
        if (!suiteDir.exists()) {
            suiteDir.mkdir();
        }
        return new File(suiteDir, imageName + "." + role + ".png");
    }

    private File getTestImagesDir() {
        var testImageDir = Paths.get(".").toAbsolutePath().normalize()
            .resolve("test")
            .resolve("testImages")
            .toFile();
        if (!testImageDir.isDirectory()) {
            throw new RuntimeException("Cannot find test test image directory at " + testImageDir);
        }
        return testImageDir;
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
                    int delta = (expectedPix & 0xFF) - (actualPix & 0xFF);
                    if (Math.abs(maxDiff) < Math.abs(delta)) {
                        maxDiff = delta;
                    }
                    expectedPix >>= 8;
                    actualPix >>= 8;
                }
                totalDiff += Math.pow(maxDiff / 255.0, 2);
                deltaImage.setRGB(x, y, 0xFF000000
                    | (maxDiff > 0 ? diffCurve(maxDiff, 0.2) << 16 : 0)
                    | (maxDiff < 0 ? diffCurve(maxDiff, 0.2) << 8 : 0)
                    | diffCurve(maxDiff, 2));
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
                Math.pow(Math.abs(diff) / 255.0, gamma) * 255.0)));
    }
}
