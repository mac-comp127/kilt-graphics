package comp127graphics;

import javax.swing.JFrame;
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
    private double x, y;
    private Font font;
    private Paint textColor;
    private Shape textShape;  // lazily initialized, updated when painted
    private boolean filled = true;
    private FontMetrics metrics;

    /**
     * Creates drawable text at position (x,y)
     */
    public GraphicsText(String text, double x, double y) {
        this.x = x;
        this.y = y;
        this.text = text;
        textColor = Color.BLACK;
        setFont(Font.SANS_SERIF, FontStyle.PLAIN, 14);
    }

    /**
     * Creates an instance with the given text at (0,0).
     */
    public GraphicsText(String text) {
        this(text, 0, 0);
    }

    /**
     * Creates an instance with null text at (0,0)
     */
    public GraphicsText() {
        this(null);
    }

    protected void draw(Graphics2D gc) {

        Font curFont = gc.getFont();
        gc.setFont(font);
        Paint curColor = gc.getPaint();
        gc.setPaint(textColor);
        //gc.drawString(text, x, y); // This would look better but doesn't seem to work with hit testing.

        gc.fill(recomputeTextShape(gc));
        gc.setFont(curFont);
        gc.setPaint(curColor);
    }

    private Shape recomputeTextShape(Graphics2D gc) {
        if (text == null || text.isEmpty()) {  // textLayout doesn't like empty strings
            return textShape = new Rectangle2D.Double(0, 0, 0, 0);
        }
        FontRenderContext frc = gc.getFontRenderContext();
        TextLayout textLayout = new TextLayout(text, font, frc);
        AffineTransform moveTo = AffineTransform.getTranslateInstance(x, y);
        textShape = textLayout.getOutline(moveTo);  // memoize
        return textShape;
    }

    private Shape getTextShape() {
        // Getting a text shape requires a graphics context. We normally can't get one until we're
        // painted, but we may want to measure text before it's drawn. This hack creates an almost-
        // invisible dummy window and uses its graphics. We recompute the text's shape when it's
        // actually drawn in a real window, in case the real graphics context is different.
        if (textShape == null) {
            BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
            recomputeTextShape((Graphics2D) img.getGraphics());
        }
        return textShape;
    }

    @Override
    protected void changed() {
        textShape = null;
        metrics = null;
        super.changed();
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
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

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
        changed();
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
        changed();
    }

    /**
     * Changes the size of text displayed, preserving the font family and style.
     */
    public void setFontSize(double size) {
        this.font = font.deriveFont((float) size);
        changed();
    }

    /**
     * Changes the font style of text displayed, preserving the font family and size.
     */
    public void setFontStyle(FontStyle style) {
        setFont(font.getFamily(), style, font.getSize());
    }

    /**
     * Changes the size and style of the font while preserving the font family.
     */
    public void setFont(FontStyle style, double size) {
        setFont(font.getFamily(), style, size);
    }

    /**
     * Changes the font in which the text is rendered.
     *
     * @param fontFamily A font family name, such as "Helvetica"
     */
    public void setFont(String fontFamily, FontStyle style, double size) {
        //noinspection MagicConstant
        this.font = new Font(fontFamily, style.getAwtCode(), 0).deriveFont((float) size);
        changed();
    }

    /**
     * Changes the font in which the text is rendered.
     *
     * @deprecated Use setFont(family, style, size) instead
     */
    @Deprecated
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
        if (text == null) {
            return 0;
        }
        return getFontMetrics().stringWidth(text);
    }

    public double getHeight() {
        return getFontMetrics().getHeight();
    }

    private FontMetrics getFontMetrics() {
        if (metrics == null) {
            BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = (Graphics2D) img.getGraphics();
            g.setFont(font);
            metrics = g.getFontMetrics();
        }
        return metrics;
    }

    public boolean testHit(double x, double y) {
        return getTextShape().contains(x, y);
    }

    @Override
    public Rectangle2D getBounds() {
        return getTextShape().getBounds2D();
    }
}
