package comp124graphics;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 * Represents a string of text that is drawn in a canvas window
 * Created by bjackson on 10/10/2016.
 * @version 0.5
 */
public class GraphicsText extends GraphicsObject implements Colorable {

    private String text;
    private float x, y;
    private Font font;
    private Paint textColor;
    private Shape textShape;

    /**
     * Constructs the graphical text at position x,y
     * @param text
     * @param x
     * @param y
     */
    public GraphicsText(String text, float x, float y){
        this.x = x;
        this.y = y;
        this.text = text;
        textColor = Color.BLACK;
        font = new Font("SanSerif", Font.PLAIN, 14);
    }

    /**
     * Draws the graphicsobject on the screen
     * @param gc
     */
    public void draw(Graphics2D gc){

        Font curFont = gc.getFont();
        gc.setFont(font);
        Paint curColor = gc.getPaint();
        gc.setPaint(textColor);
        //gc.drawString(text, x, y); // This would look better but doesn't seem to work with hit testing.

        FontRenderContext frc = gc.getFontRenderContext();
        TextLayout textLayout = new TextLayout(text, font, frc);
        AffineTransform moveTo = AffineTransform.getTranslateInstance(x, y);
        textShape = textLayout.getOutline(moveTo);
        gc.fill(textShape);
        gc.setFont(curFont);
        gc.setPaint(curColor);
    }

    /**
     * Move the shape from its current x, y position by dx and dy.
     * @param dx
     * @param dy
     */
    public void move(double dx, double dy){
        x+= dx;
        y+= dy;
        changed();
    }

    /**
     * Sets the position of the graphical object
     * @param x position
     * @param y position
     */
    public void setPosition(double x, double y){
        this.x = (float)x;
        this.y = (float)y;
        changed();
    }

    /**
     * Gets the position of the object on the canvas.
     * @return
     */
    public Point.Double getPosition(){
        return new Point.Double(x, y);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        changed();
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        changed();
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        changed();
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
        changed();
    }

    @Override
    public Paint getStrokeColor() {
        return textColor;
    }

    @Override
    public void setStrokeColor(Paint textColor) {
        this.textColor = textColor;
        changed();
    }

    public double getWidth(){
        FontMetrics metrics = getFontMetrics();
        return metrics.stringWidth(text);
    }

    public double getHeight(){
        FontMetrics metrics = getFontMetrics();
        return metrics.getHeight();
    }

    protected FontMetrics getFontMetrics(){
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = Graphics2D.class.cast(img.getGraphics());
        g.setFont(font);
        return g.getFontMetrics();
    }

    public boolean testHit(double x, double y){
        return textShape != null && textShape.contains(x, y);
    }

    /**
     * Returns an axis aligned bounding rectangle for the graphical object.
     * @return
     */
    public java.awt.Rectangle getBounds(){
        return new java.awt.Rectangle((int)Math.round(x), (int)Math.round(y), (int)Math.round(getWidth()), (int)Math.round(getHeight()));
    }
}
