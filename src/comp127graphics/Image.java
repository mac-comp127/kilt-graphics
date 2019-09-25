package comp127graphics;


import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * A bitmap image that can be drawn to the screen.
 *
 * @author Bret Jackson
 */
public class Image extends GraphicsObject {
    private BufferedImage img;
    private int x;
    private int y;
    private String filePath;

    /**
     * Creates a bitmap image from the given file.
     * Acceptable file formats include: GIF, PNG, JPEG, BMP, and WBMP
     *
     * @param x        position
     * @param y        position
     * @param filePath filepath to image file to load.
     */
    public Image(int x, int y, String filePath) {
        this.x = x;
        this.y = y;
        this.filePath = filePath;

        try {
            Path path = Paths.get(filePath);
            path = path.toAbsolutePath();
            File file = new File(path.toString());
            if (!file.exists()) {
                throw new IOException(path + " does not exist");
            }
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    protected void draw(Graphics2D gc) {
        gc.drawImage(img, x, y, null);
    }

    /**
     * Returns the position of the image's left edge.
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the position of the image's top edge.
     */
    public double getY() {
        return y;
    }

    /**
     * Get the width of the image
     */
    public double getWidth() {
        return img.getWidth();
    }

    /**
     * Get the height of the image
     */
    public double getHeight() {
        return img.getHeight();
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
                && x <= this.x + img.getWidth()
                && y >= this.y
                && y <= this.y + img.getHeight();
    }

    @Override
    Rectangle2D getBounds() {
        return new Rectangle2D.Double(getX(), getY(), getWidth(), getHeight());
    }

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
                && filePath.equals(image.filePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, filePath);
    }

    /**
     * Two images are equal if they are the same file and are at the same position.
     */


    @Override
    public String toString() {
        return "Image at position (" + x + ", " + y + ") with file " + filePath;
    }
}
