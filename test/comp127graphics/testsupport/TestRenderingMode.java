package comp127graphics.testsupport;

import static comp127graphics.testsupport.GraphicsObjectTestSuite.assertChanged;
import static comp127graphics.testsupport.GraphicsObjectTestSuite.assertChangedAtEachStep;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.util.List;

import comp127graphics.Fillable;
import comp127graphics.GraphicsObject;
import comp127graphics.Point;
import comp127graphics.Strokable;

/**
 * Specifies how a @RenderingTest should render its graphics object to an image.
 */
public enum TestRenderingMode implements ImageComparison.Renderer {
    /**
     * Renders the graphics object as is, with no modification.
     */
    PLAIN {
        @Override
        public void render(BufferedImage image, GraphicsObject gobj) {
            renderWithBounds(image, gobj);
        }
    },

    /**
     * Adds a stroke to the graphics object, and disables the fill if it is Fillable.
     * 
     * This suite's GraphicsObject must be Strokable.
     */
    STROKED {
        @Override
        public void render(BufferedImage image, GraphicsObject gobj) {
            if (gobj instanceof Fillable) {
                assertChanged(gobj, () -> 
                    ((Fillable) gobj).setFilled(false));
            }

            var strokable = (Strokable) gobj;
            assertChangedAtEachStep(gobj, () -> {
                strokable.setStroked(false);
                assertFalse(strokable.isStroked());
            }, () -> {
                strokable.setStrokeColor(Color.MAGENTA);
                assertTrue(strokable.isStroked());
            }, () -> {
                strokable.setStrokeWidth(4);
            });

            renderWithBounds(image, gobj);
        }
    },

    /**
     * Adds a fill color to the graphics object, and disables the stroke if it is Strokable.
     * 
     * This suite's GraphicsObject must be Fillable.
     */
    FILLED {
        @Override
        public void render(BufferedImage image, GraphicsObject gobj) {
            if (gobj instanceof Strokable) {
                assertChanged(gobj, () -> 
                    ((Strokable) gobj).setStroked(false));
            }

            var fillable = (Fillable) gobj;
            assertChangedAtEachStep(gobj, () -> {
                fillable.setFilled(false);
                assertFalse(fillable.isFilled());
            }, () -> {
                fillable.setFillColor(Color.CYAN);
                assertTrue(fillable.isFilled());
            });
            renderWithBounds(image, gobj);
        }
    },

    /**
     * Adds both a fill and a stroke to the graphics object.
     * 
     * This suite's GraphicsObject must be Fillable and Strokable.
     */
    FILLED_AND_STROKED {
        @Override
        public void render(BufferedImage image, GraphicsObject gobj) {
            var fillAndStroke = (Fillable & Strokable) gobj;
            assertChangedAtEachStep(gobj, () -> fillAndStroke.setStrokeColor(Color.BLUE),
                    () -> fillAndStroke.setStrokeWidth(3), () -> fillAndStroke.setFillColor(Color.YELLOW));
            assertTrue(fillAndStroke.isStroked());
            assertTrue(fillAndStroke.isFilled());
            renderWithBounds(image, gobj);
        }
    },

    /**
     * Colors each pixel of the image according to the return values of testHit() and isInBounds().
     */
    HIT_TEST {
        @Override
        public void render(BufferedImage image, GraphicsObject gobj) {
            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    image.setRGB(x, y, 0xFF000000 | (gobj.isInBounds(new Point(x, y)) ? 0xFF006789 : 0)
                            | (gobj.testHit(x, y) ? 0xFFFF0000 : 0));
                }
            }
        }
    };

    private static void renderWithBounds(BufferedImage image, GraphicsObject gobj) {
        var gc = image.createGraphics();
        gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gc.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        gc.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        // This messy reflection code is secretly just `gobj.draw(gc)`. We could make
        // GraphicsObject.draw() a public method, but it would confuse students by showing up in
        // autocomplete. We could move this class into the same package as GraphicsObject, but
        // that makes the tests harder to navigate. The mess thus lives here.
        try {
            var drawMethod = gobj.getClass().getDeclaredMethod("draw", new Class[] { Graphics2D.class });
            drawMethod.setAccessible(true);
            drawMethod.invoke(gobj, gc);
        } catch (Exception e) {
            throw new RuntimeException("Unable to draw " + gobj, e);
        }
        visualizeBounds(gc, gobj);
    }

    /// Draws printer-style crop marks around the bounding box of the graphics object.
    private static void visualizeBounds(Graphics2D g, GraphicsObject gobj) {
        var bounds = gobj.getBounds();
        var cropMarks = new Path2D.Double(GeneralPath.WIND_EVEN_ODD);
        for(int side = 0; side < 2; side++) {
            for (double y : List.of(bounds.getMinY() + 0.25, bounds.getMaxY() - 0.25)) {
                double x = bounds.getMinX() + bounds.getWidth() * side;
                cropMarks.moveTo(x + (side * 2 - 1) * 16, y);
                cropMarks.lineTo(x + (side * 2 - 1) * 4, y);
            }
            for (double x : List.of(bounds.getMinX() + 0.25, bounds.getMaxX() - 0.25)) {
                double y = bounds.getMinY() + bounds.getHeight() * side;
                cropMarks.moveTo(x, y + (side * 2 - 1) * 16);
                cropMarks.lineTo(x, y + (side * 2 - 1) * 4);
            }
        }
        g.setStroke(new BasicStroke(0.5f));
        g.setPaint(new Color(0, 0, 0, 128));
        g.draw(cropMarks);
    }
}
