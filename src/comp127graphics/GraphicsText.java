package comp127graphics;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

/**
 * A string of text that can be drawn to the screen
 *
 * @author Bret Jackson
 */
public class GraphicsText extends GraphicsObject implements Fillable {

    private String text;
    private float x, y;
    private Font font;
    private Paint textColor;
    private Shape textShape;
    private boolean filled = true;

    /**
     * Creates drawable text at position (x,y)
     */
    public GraphicsText(String text, float x, float y) {
        this.x = x;
        this.y = y;
        this.text = text;
        textColor = Color.BLACK;
        font = new Font("SanSerif", Font.PLAIN, 14);
    }

    public void draw(Graphics2D gc) {

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

    public void setPosition(double x, double y) {
        this.x = (float) x;
        this.y = (float) y;
        changed();
    }

    public Point getPosition() {
        return new Point(x, y);
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
    public Paint getFillColor() {
        return textColor;
    }

    @Override
    public void setFillColor(Paint textColor) {
        this.textColor = textColor;
        setFilled(true);
    }

    @Override
    public boolean isFilled() {
        return filled;
    }

    @Override
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public double getWidth() {
        FontMetrics metrics = getFontMetrics();
        return metrics.stringWidth(text);
    }

    public double getHeight() {
        FontMetrics metrics = getFontMetrics();
        return metrics.getHeight();
    }

    private FontMetrics getFontMetrics() {
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.setFont(font);
        return g.getFontMetrics();
    }

    public boolean testHit(double x, double y) {
        return textShape != null && textShape.contains(x, y);
    }

    @Override
    Rectangle2D getBounds() {
        return textShape.getBounds2D();
    }
}
