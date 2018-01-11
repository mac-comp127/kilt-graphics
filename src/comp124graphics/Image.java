package comp124graphics;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Used to draw an image on the screen.
 * Created by bjackson on 9/15/2016.
 * @version 0.5
 */
public class Image extends GraphicsObject{
    private BufferedImage img;
    private int x;
    private int y;
    private String filePath;

    /**
     * Constructor to create the Image object and initialize its instance variables.
     * Acceptable file formats include: GIF, PNG, JPEG, BMP, and WBMP
     * @param x position
     * @param y position
     * @param filePath filepath to image file to load.
     */
    public Image(int x, int y, String filePath){
        this.x = x;
        this.y = y;
        this.filePath = filePath;

        try {
            Path path = Paths.get(filePath);
            path = path.toAbsolutePath();
            File file = new File(path.toString());
            if (!file.exists()){
                throw new IOException(path + " does not exist");
            }
            img = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Draws the image on the screen
     * @param gc
     */
    public void draw(Graphics2D gc){
        gc.drawImage(img, x, y, null);
    }

    /**
     * Get the shape's x position
     * @return x position
     */
    public double getX(){
        return x;
    }

    /**
     * Get the shape's y position
     * @return y position
     */
    public double getY(){
        return y;
    }

    /**
     * Get the width of the rectangle
     * @return rectangle width
     */
    public double getWidth(){
        return img.getWidth();
    }

    /**
     * Get the height of the rectangle
     * @return rectangle height
     */
    public double getHeight(){
        return img.getHeight();
    }

    /**
     * Sets the shape's position to x, y
     * @param x
     * @param y
     */
    public void setPosition(int x, int y){
        this.x = x;
        this.y =y;
        changed();
    }

    public void setPosition(double x, double y){
        setPosition((int)Math.round(x), (int)Math.round(y));
    }

    /**
     * Gets the position of the graphical object
     * @return position
     */
    public Point.Double getPosition(){
        return new Point.Double(x, y);
    }

    /**
     * Move the shape from its current x, y position by dx and dy.
     * @param dx
     * @param dy
     */
    public void move(double dx, double dy){
        x += dx;
        y += dy;
        changed();
    }

    /**
     * Tests whether the point (x, y) hits the shape on the graphics window
     * @return true if this shape is the topmost object at point (x, y)
     */
    public boolean testHit(double x, double y, Graphics2D gc){
        if (x >= this.x && x <= this.x+img.getWidth() && y >= this.y && y <= this.y+img.getHeight()){
            return true;
        }
        return false;
    }

    /**
     * Two images are equal if they are the same file and same position.
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other){
        if (other != null && other instanceof Image){
            Image otherImg = (Image)other;
            if (this.img.equals(otherImg.img) && this.x==otherImg.x && this.y == otherImg.y){
                return true;
            }
        }
        return false;
    }

    /**
     * Returns a string representation of the Image object
     * @return
     */
    @Override
    public String toString(){
        return "An image at position ("+x+", "+y+") with file "+filePath;
    }

    /**
     * Returns an axis aligned bounding rectangle for the graphical object.
     * @return
     */
    public java.awt.Rectangle getBounds(){
        return new java.awt.Rectangle((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
    }
}
