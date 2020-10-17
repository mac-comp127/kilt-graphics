package edu.macalester.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.imageio.ImageIO;

/**
 * A bitmap image that can be drawn to the screen.
 *
 * @author Bret Jackson
 */
public class Image extends GraphicsObject {
    private BufferedImage img;
    private double x,y;
    private double maxWidth = Double.POSITIVE_INFINITY, maxHeight = Double.POSITIVE_INFINITY;
    private String path;

    private static final Map<String,BufferedImage> imageCache = new HashMap<>();

    private static BufferedImage loadImage(String path) {
        synchronized (imageCache) {
            BufferedImage image = imageCache.get(path);
            if (image == null) {
                try {
                    System.out.println("Loading image /" + path);
                    InputStream resource = Image.class.getResourceAsStream("/" + path);
                    if (resource == null) {
                        throw new IOException("No resource named /" + path);
                    }
                    image = ImageIO.read(resource);
                    imageCache.put(path, image);
                } catch (IOException e) {
                    System.err.println("Could not load image from " + path + ": " + e);
                    image = createPlaceholderImage(path, 64, 64);
                }
            }
            return image;
        }
    }

    private static BufferedImage createPlaceholderImage(String path, int width, int height) {
        var image = new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
        var gc = image.createGraphics();

        gc.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_OFF);
        gc.setRenderingHint(
            RenderingHints.KEY_RENDERING,
            RenderingHints.VALUE_RENDER_SPEED);
        gc.setRenderingHint(
            RenderingHints.KEY_STROKE_CONTROL,
            RenderingHints.VALUE_STROKE_NORMALIZE);

        gc.setColor(new Color(255, 128, 128, 32));
        gc.fillRect(0, 0, width, height);
        gc.setStroke(new BasicStroke(4));
        gc.setColor(new Color(128, 0, 0));
        gc.drawRect(0, 0, width, height);
        gc.setStroke(new BasicStroke(1));
        gc.drawLine(0, 0, width, height);
        gc.drawLine(0, width, height, 0);
        gc.setColor(Color.BLACK);
        gc.setFont(new Font("Tahoma", Font.PLAIN, 9));
        gc.drawString(path, 4, height - 4);
        return image;
    }

    /**
     * Creates an Image placeholder with no current image.
     */
    public Image(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a bitmap image from the given file, positioned at (0,0).
     * Acceptable file formats include: GIF, PNG, JPEG, BMP, and WBMP.
     *
     * @param path path of image file to load, relative to the res/ directory.
     */
    public Image(String path) {
        this(0, 0, path);
	}

    /**
     * Creates a bitmap image from the given file.
     * Acceptable file formats include: GIF, PNG, JPEG, BMP, and WBMP.
     *
     * @param path path of image file to load, relative to the res/ directory.
     */
    public Image(double x, double y, String path) {
        this.x = x;
        this.y = y;
        setImagePath(path);
    }

	/**
     * Changes the image displayed by this graphics element.
     *
     * @param path path of image file to load, relative to the res/ directory.
     */
    public void setImagePath(String path) {
        this.path = path;
        this.img = loadImage(path);
        changed();
    }

    /**
     * Causes the image to shrink (preserving aspect ratio) if the image width is larger than the
     * given maximum width.
     */
    public void setMaxWidth(double maxWidth) {
        this.maxWidth = maxWidth;
        changed();
    }

    /**
     * Causes the image to shrink (preserving aspect ratio) if the image height is larger than the
     * given maximum height.
     */
    public void setMaxHeight(double maxHeight) {
        this.maxHeight = maxHeight;
        changed();
    }

    @Override
    protected void drawInLocalCoordinates(Graphics2D gc) {
        if (img != null) {
            gc.drawImage(
                img,
                (int) Math.round(x),
                (int) Math.round(y),
                (int) Math.round(getWidth()),
                (int) Math.round(getHeight()),
                null);
        }
    }

    /**
     * Returns the position of the image's left edge.
     */
    @Override
    public double getX() {
        return x;
    }

    /**
     * Returns the position of the image's top edge.
     */
    @Override
    public double getY() {
        return y;
    }

    /**
     * Get the width of the rendered image as it will appear on the screen. Affected by the size of
     * the image as well as setMaxWidth() and setMaxHeight().
     */
    public double getWidth() {
        return getImageWidth() * getScaleToFit();
    }

    /**
     * Get the height of the rendered image as it will appear on the screen. Affected by the size of
     * the image as well as setMaxWidth() and setMaxHeight().
     */
    public double getHeight() {
        return getImageHeight() * getScaleToFit();
    }

    private double getScaleToFit() {
        return Math.min(
            1,
            Math.min(
                maxWidth / getImageWidth(),
                maxHeight / getImageHeight()));
    }

    /**
     * Get the width of the underlying image.
     */
    public double getImageWidth() {
        return img == null ? 0 : img.getWidth();
    }

    /**
     * Get the height of the underlying image.
     */
    public double getImageHeight() {
        return img == null ? 0 : img.getHeight();
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        changed();
    }

    public void setPosition(double x, double y) {
        setPosition((int) Math.round(x), (int) Math.round(y));
    }

    public Point getPosition() {
        return new Point(x, y);
    }

    /**
     * Tests whether the point (x, y) touches the image.
     * Does not take into account image transparency.
     */
    public boolean testHit(double x, double y) {
        return x >= this.x
                && x <= this.x + getWidth()
                && y >= this.y
                && y <= this.y + getHeight();
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Two images are equal if they are the same file and are at the same position.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Image image = (Image) o;
        return x == image.x
                && y == image.y
                && path.equals(image.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, path);
    }

    @Override
    public String toString() {
        return "Image at position (" + x + ", " + y + ") with file " + path;
    }
}
