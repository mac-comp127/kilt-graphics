package edu.macalester.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.text.AttributedString;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * A string of text that can be drawn to the screen.
 * <p>
 * A GraphicsText’s {@link getPosition() position} is the beginning of the first character at the
 * <a href="https://en.wikipedia.org/wiki/Baseline_(typography)">baseline</a>. This means that text
 * largely extends <i>above</i> the position you specify with {@link setPosition(Point)}. To
 * position text relative to its top edge, use {@link getHeight()}. To format many GraphicsText
 * objects as a larger body of text, use the {@link getAdvance()} and {@link getLineHeight()} methods.
 *
 * @author Bret Jackson
 */
public class GraphicsText extends GraphicsObject implements Fillable {
    private static final Pattern LINE_BREAK_PATTERN = Pattern.compile("\r\n|\r|\n");

    private String text;
    private Font font;
    private Paint textColor;
    private boolean filled = true;
    private double wrappingWidth = Double.POSITIVE_INFINITY;
    private FontMetrics metrics;

    // These are both expensive to compute, so we compute them lazily.
    // Both are in text-local coordinates (not account for this object's position)
    // so that merely moving text does not force a recomputation.
    private Shape textShape;
    private Rectangle2D rawBounds;

    /**
     * Creates drawable text at position (x,y)
     */
    public GraphicsText(String text, double x, double y) {
        setPosition(x, y);
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

    @Override
    protected void drawInLocalCoordinates(Graphics2D gc) {
        Font curFont = gc.getFont();
        Paint curColor = gc.getPaint();
        gc.setFont(font);
        gc.setPaint(textColor);

        gc.fill(getTextShape());

        gc.setFont(curFont);
        gc.setPaint(curColor);
    }

    private Shape recomputeTextShape(Graphics2D gc) {
        if (text == null || text.isEmpty()) {  // textLayout doesn't like empty strings
            return new Rectangle2D.Double(0, 0, 0, 0);
        }

        // Create a stream of lines of text, separated by either soft wraps (at wrappingWidth) or hard line breaks ("\n")
        Stream<TextLayout> lineLayouts =
            LINE_BREAK_PATTERN.splitAsStream(text)  // LineBreakMeasurer doesn't understand hard breaks, so we find them ourselves
                .flatMap(paragraph -> {
                    if (paragraph.isEmpty()) {
                        paragraph = "\u200B";  // AttributedString can't format empty strings, so replace with a zero-width space
                    }
                    var measurer = new LineBreakMeasurer(
                        new AttributedString(paragraph, Map.of(TextAttribute.FONT, font)).getIterator(),
                        gc.getFontRenderContext());
                    return Stream.generate(() -> measurer.nextLayout((float) wrappingWidth))
                        .takeWhile(Objects::nonNull);
                });

        Area result = new Area();
        AffineTransform transform = new AffineTransform();  // tracks vertical position
        lineLayouts.forEach(lineLayout -> {
            result.add(new Area(
                lineLayout.getOutline(transform)));
            transform.translate(0, getLineHeight());
        });
        return result;
    }

    private Shape getTextShape() {
        // Getting a text shape requires a graphics context. We normally can't get one until we're
        // painted, but we may want to measure text before it's drawn. This hack creates an almost-
        // invisible dummy window and uses its graphics. We recompute the text's shape when it's
        // actually drawn in a real window, in case the real graphics context is different.
        if (textShape == null) {
            BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
            textShape = recomputeTextShape((Graphics2D) img.getGraphics());
            rawBounds = textShape.getBounds2D();
        }
        return textShape;
    }

    @Override
    protected void changed() {
        metrics = null;
        super.changed();
    }

    private void textShapeChanged() {
        textShape = null;
        rawBounds = null;
        changed();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        textShapeChanged();
    }

    /**
     * Changes the size of text displayed, preserving the font family and style.
     */
    public void setFontSize(double size) {
        this.font = font.deriveFont((float) size);
        textShapeChanged();
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
        // noinspection MagicConstant
        this.font = new Font(fontFamily, style.getAwtCode(), 0).deriveFont((float) size);
        textShapeChanged();
    }

    /**
     * Changes the font in which the text is rendered.
     *
     * @deprecated Use setFont(family, style, size) instead
     */
    @Deprecated
    public void setFont(Font font) {
        this.font = font;
        textShapeChanged();
    }

    public double getWrappingWidth() {
        return wrappingWidth;
    }

    public void setWrappingWidth(double wrappingWidth) {
        this.wrappingWidth = wrappingWidth;
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
        changed();
    }

    @Override
    public boolean testHitInLocalCoordinates(double x, double y) {
        return getTextShape().contains(x, y);
    }

    /**
     * Returns true if this text visually overlaps the given other text. This method assumes both are in
     * the some coordinate system; it does not account for them belonging to different GraphicsGroups.
     */
    public boolean intersects(GraphicsText other) {
        Area area = getArea();
        area.intersect(other.getArea());
        return !area.isEmpty();
    }

    private Area getArea() {
        Area area = new Area(getTextShape());
        area.transform(getTransform());
        return area;
    }

    /**
     * Returns how far after this text any subsequent text should appear. This is distinct from getWidth()
     * because some glyphs may overhang on either the left or the right, overlapping into the neighboring
     * glyphs’ areas. While getWidth() returns the size of the entire bounding box, including such overhang,
     * getAdvance() does not include the overhang.
     */
    public double getAdvance() {
        return getFontMetrics().stringWidth(text);
    }

    /**
     * Returns the standard spacing between lines of text in the current font, regardless of the height of
     * the actual characters present.
     */
    public double getLineHeight() {
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

    @Override
    public Rectangle2D getBounds() {
        getTextShape();
        return new Rectangle2D.Double(
            rawBounds.getX(),
            rawBounds.getY(),
            rawBounds.getWidth(),
            rawBounds.getHeight());
    }

    @Override
    protected Object getEqualityAttributes() {
        return List.of(font, text);
    }
}
