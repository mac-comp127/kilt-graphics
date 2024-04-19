package edu.macalester.graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.RenderingHints;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * A bitmap image that can be drawn to the screen.
 * <p>
 * An image’s {@link getPosition() position} is the upper left corner of its bounding box.
 * Its size is the size of the underying image file by default, but you can shrink it using
 * {@link setMaxWidth(double) setMaxWidth()} and {@link setMaxHeight(double) setMaxHeight()}.
 *
 * @author Bret Jackson
 */
public class Image extends GraphicsObject {
    private BufferedImage img;
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
        if (System.getProperty("os.name").toLowerCase().contains("mac os")) {
            // Unantialiased fonts look awful on Big Sur, and Tahoma looks nasty when
            // antialiased at small sizes, so we use a Mac-specific alternative
            gc.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
            gc.setFont(new Font("Helvetica", Font.PLAIN, 9));
        } else {
            gc.setFont(new Font("Tahoma", Font.PLAIN, 9));
        }
        gc.drawString(path, 4, height - 4);
        return image;
    }

    /**
     * Creates an Image placeholder with no current image.
     */
    public Image(double x, double y) {
        setPosition(x, y);
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
        setPosition(x, y);
        setImagePath(path);
    }

    /**
     * Creates a bitmap image from the given BufferedImage, positioned at (0, 0).
     * Note that changing the BufferedImage externally does not automatically 
     * force it to redraw. You will need to call {@link CanvasWindow#draw()}
     * to see the changes.
     * 
     * @param image
     */
    public Image(BufferedImage image){
        this(0, 0, image);
    }

    /**
     * Creates a bitmap image from the given BufferedImage. Note that changing
     * the BufferedImage externally does not automatically force it to redraw.
     * You will need to call {@link CanvasWindow#draw()} to see the changes.
     * 
     * @param image
     */
    public Image(double x, double y, BufferedImage image){
        setPosition(x, y);
        this.path = "In memory BufferedImage@"+Integer.toHexString(image.hashCode());
        this.img = image;
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
            AffineTransform oldTransform = gc.getTransform();
            AffineTransform pixelAligned = new AffineTransform(oldTransform);
            pixelAligned.translate(
                Math.round(oldTransform.getTranslateX()) - oldTransform.getTranslateX(),
                Math.round(oldTransform.getTranslateY()) - oldTransform.getTranslateY());
            gc.setTransform(pixelAligned);

            gc.drawImage(
                img, 0, 0,
                (int) Math.round(getWidth()),
                (int) Math.round(getHeight()),
                null);

            gc.setTransform(oldTransform);
        }
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

    /**
     * Tests whether the point (x, y) touches the image.
     * Does not take into account image transparency.
     */
    @Override
    public boolean testHitInLocalCoordinates(double x, double y) {
        return x >= 0
            && x <= getWidth()
            && y >= 0
            && y <= getHeight();
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(
            0, 0,
            getImageWidth() * getScaleToFit(),
            getImageHeight() * getScaleToFit());
    }

    private double getScaleToFit() {
        return Math.min(
            1,
            Math.min(
                maxWidth / getImageWidth(),
                maxHeight / getImageHeight()));
    }


    @Override
    protected Object getEqualityAttributes() {
        return List.of(path, maxWidth, maxHeight);  // Not ideally performant, but life goes on
    }

    @Override
    public String toString() {
        return "Image at position " + getPosition() + " with file " + path;
    }
}
