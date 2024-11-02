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
 * @author Bret Jackson, Paul Cantrell
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
     * Creates a new image using raw pixel data from the given array. The range for sample values
     * is [0...1], and values outside that range are pinned to it when generating the image (i.e.
     * any value ≥ 1 is full intensity, and any value ≤ 0 is zero intensity).
     * There is one array element per color channel, with channels interleaved
     * (see {@link PixelFormat}.)
     * <p>
     * For example, the array <code>{ 1, 0.5f, 0, 0, 0, 0.5f }</code> with the RGB pixel format
     * specifies one orange pixel (R=1, G=0.5, B=0), then one dark blue pixel (R=0, G=0, B=0.5).
     *
     * @param width Image width in pixels
     * @param height Image height in pixels
     * @param pixels Raw pixel data. Length must exactly match the number of required samples.
     * @param format Color space and format of channels in the pixels array
     */
    public Image(int width, int height, float[] pixels, PixelFormat format) {
        this(format.makeBufferedImage(pixels, width, height));
    }

    /**
     * Creates a new image using raw pixel data from the given array. This method interprets bytes
     * as unsigned: zero intensity is 0, and full intensity is 255 (but Java represents this as -1,
     * because the language does not have unsigned primitive types).
     * There is one array element per color channel, with channels interleaved
     * (see {@link PixelFormat}.)
     * <p>
     * For example, the array <code>{ -1, 127, 0, 0, 0, 127 }</code> with the RGB pixel format
     * specifies one orange pixel (R=1, G=0.5, B=0), then one dark blue pixel (R=0, G=0, B=0.5).
     *
     * @param width Image width in pixels
     * @param height Image height in pixels
     * @param pixels Raw pixel data. Length must exactly match the number of required samples.
     * @param format Color space and format of channels in the pixels array
     */
    public Image(int width, int height, byte[] pixels, PixelFormat format) {
        this(format.makeBufferedImage(pixels, width, height));
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
     * Get the width of the underlying image in pixels.
     */
    public int getImageWidth() {
        return img == null ? 0 : img.getWidth();
    }

    /**
     * Get the height of the underlying image in pixels.
     */
    public int getImageHeight() {
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

    public byte[] toByteArray(PixelFormat format) {
        return format.makeByteArray(img);
    }

    public float[] toFloatArray(PixelFormat format) {
        var bytes = toByteArray(format);
        var floats = new float[bytes.length];
        for(int i = 0; i < bytes.length; i++) {
            floats[i] = (bytes[i] & 0xFF) / 255.0f;
        }
        return floats;
    }

    @Override
    protected Object getEqualityAttributes() {
        return List.of(path, maxWidth, maxHeight);  // Not ideally performant, but life goes on
    }

    @Override
    public String toString() {
        return "Image at position " + getPosition() + " with file " + path;
    }

    /**
     * Describes the presence and order of color channels in an array of pixels.
     * Used by the various methods for converting Images to and from arrays.
     *
     * @see Image#Image(int,int,float[],PixelFormat)
     * @see Image#Image(int,int,byte[],PixelFormat)
     * @see Image#toFloatArray(PixelFormat)
     * @see Image#toByteArray(PixelFormat)
     */
    public enum PixelFormat {
        /**
         * One array element per pixel, mapping to shades of gray.
         */
        // NB: We use TYPE_INT_RGB internally instead of TYPE_BYTE_GRAY because the latter uses a
        // linear gray color space -- no gamma! -- which causes two problems: (1) excessively dark
        // images, and (2) severe precision loss in the conversion to / from array. Storing
        // grayscale images as RBG internally is not memory-efficient, but does solve both of those
        // colorspace issues.
        //
        GRAYSCALE(BufferedImage.TYPE_INT_RGB, 1, 3),

        /**
         * Three array elements per pixel: [red, green, blue, red, green, blue…].
         */
        RGB(BufferedImage.TYPE_INT_RGB, 3, 3),

        /**
         * Four array elements per pixel: [alpha, red, green, blue, alpha, red, green, blue…].
         */
        ARGB(BufferedImage.TYPE_INT_ARGB, 4, 4);

        private final int bufferedImageType;
        private final int externalChans;  // Number of channels in the arrays API clients see
        private final int internalChans;  // Number of packed channels we pass to Java APIs

        PixelFormat(int bufferedImageType, int externalChans, int internalChans) {
            this.bufferedImageType = bufferedImageType;
            this.externalChans = externalChans;
            this.internalChans = internalChans;
        }

        private BufferedImage makeBufferedImage(byte[] pixels, int width, int height) {
            return makeBufferedImage(
                width, height, pixels.length,
                i -> 0xFF & pixels[i]);
        }

        private BufferedImage makeBufferedImage(float[] pixels, int width, int height) {
            return makeBufferedImage(
                width, height, pixels.length,
                i -> (int) (255 * Math.min(1, Math.max(0, pixels[i]))));
        }

        private BufferedImage makeBufferedImage(
            int width,
            int height,
            int pixelArrayLen,
            PixelLookup pixelLookup
        ) {
            int expectedArrayLen = width * height * externalChans;
            if (pixelArrayLen != expectedArrayLen) {
                throw new IllegalArgumentException(
                    "Invalid input array length for " + this.name() + ": expected "
                    + width + " w * " + height + " h * " + externalChans + " channels = "
                    + expectedArrayLen + ", but got " + pixelArrayLen);
            }

            // This conversation approach is not especially performant or memory-efficient, but it
            // keeps the Kilt Graphics source code relative simple and uniform across pixel formats,
            // and should be fast enough for the purposes of any project using this library. Clients
            // who need more optimized performance can use the BufferedImage constructor to pass
            // a WritableRaster they manage themselves.

            int[] rawData = new int[width * height];
            for (int i = 0; i < rawData.length; i++) {
                int pix = 0;
                for(int c = 0; c < internalChans; c++) {
                    pix = pix << 8
                        | pixelLookup.pixelAtIndex(i * externalChans + c % externalChans);
                }
                rawData[i] = pix;
            }

            BufferedImage buf = new BufferedImage(width, height, bufferedImageType);
            buf.setRGB(0, 0, width, height, rawData, 0, width);
            return buf;
        }

        private byte[] makeByteArray(BufferedImage buf) {
            int width = buf.getWidth(), height = buf.getHeight();
            int[] rawData = buf.getRGB(0, 0, width, height, null, 0, width);

            byte[] pixels = new byte[width * height * externalChans];
            int i = 0;
            for(int pix : rawData) {
                for(int c = 0; c < externalChans; c++) {
                    // Need to handle both color → grayscale and color → color:
                    // Average channels if internalChans > externalChans, and extract channels
                    // cyclically if internalChans < externalChans.
                    int sum = 0, count = 0;
                    for(int d = c; d < internalChans; d += externalChans) {
                        sum += (pix >> (8 * (internalChans - d - 1))) & 0xFF;
                        count++;
                    }
                    pixels[i + c] = (byte) (sum / count);
                }
                i += externalChans;
            }
            return pixels;
        }

        private interface PixelLookup {
            int pixelAtIndex(int index);
        }
    }
}
