package comp127graphics;

import static comp127graphics.GraphicsObjectTestSuite.assertChangedAtEachStep;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.util.List;

public enum TestRenderingMode implements ImageComparison.Renderer {
    PLAIN {
        @Override
        public void render(BufferedImage image, GraphicsObject gobj) {
            renderWithBounds(image, gobj);
        }
    },

    STROKED {
        @Override
        public void render(BufferedImage image, GraphicsObject gobj) {
            if (gobj instanceof Fillable) {
                ((Fillable) gobj).setFilled(false);
            }

            var strokable = (Strokable) gobj;
            assertChangedAtEachStep(gobj,
                () -> {
                    strokable.setStroked(false);
                    assertFalse(strokable.isStroked());
                },
                () -> {
                    strokable.setStrokeColor(Color.MAGENTA);
                    assertTrue(strokable.isStroked());
                },
                () -> {
                    strokable.setStrokeWidth(4);
                }
            );

            renderWithBounds(image, gobj);
        }
    },

    FILLED {
        @Override
        public void render(BufferedImage image, GraphicsObject gobj) {
            if (gobj instanceof Strokable) {
                ((Strokable) gobj).setStroked(false);
            }

            var fillable = (Fillable) gobj;
            assertChangedAtEachStep(gobj,
                () -> {
                    fillable.setFilled(false);
                    assertFalse(fillable.isFilled());
                },
                () -> {
                    fillable.setFillColor(Color.CYAN);
                    assertTrue(fillable.isFilled());
                }
            );
            renderWithBounds(image, gobj);
        }
    },

    FILLED_AND_STROKED {
        @Override
        public void render(BufferedImage image, GraphicsObject gobj) {
            var fillAndStroke = (Fillable & Strokable) gobj;
            assertChangedAtEachStep(gobj,
                () -> fillAndStroke.setStrokeColor(Color.BLUE),
                () -> fillAndStroke.setStrokeWidth(3),
                () -> fillAndStroke.setFillColor(Color.YELLOW)
            );
            assertTrue(fillAndStroke.isStroked());
            assertTrue(fillAndStroke.isFilled());
            renderWithBounds(image, gobj);
        }
    },
    
    HIT_TEST {
        @Override
        public void render(BufferedImage image, GraphicsObject gobj) {
            for (int y = 0; y < image.getHeight(); y++) {
                for(int x = 0; x < image.getWidth(); x++) {
                    image.setRGB(x, y, 0xFF000000
                        | (gobj.isInBounds(new Point(x, y)) ? 0xFF00ABCD : 0)
                        | (gobj.testHit(x, y) ? 0xFFFF1234 : 0));
                }                
            }
        }
    };

    private static void renderWithBounds(BufferedImage image, GraphicsObject gobj) {
        var g = image.createGraphics();
        gobj.draw(g);
        visualizeBounds(g, gobj);
    }

    private static void visualizeBounds(Graphics2D g, GraphicsObject gobj) {
        var bounds = gobj.getBounds();
        var cropMarks = new Path2D.Double(GeneralPath.WIND_EVEN_ODD);
        for(int side = 0; side < 2; side++) {
            for (double y : List.of(bounds.getMinY(), bounds.getMaxY() - 1)) {
                double x = bounds.getMinX() + bounds.getWidth() * side;
                cropMarks.moveTo(x + (side * 2 - 1) * 16, y);
                cropMarks.lineTo(x + (side * 2 - 1) * 4, y);
            }
            for (double x : List.of(bounds.getMinX(), bounds.getMaxX() - 1)) {
                double y = bounds.getMinY() + bounds.getHeight() * side;
                cropMarks.moveTo(x, y + (side * 2 - 1) * 16);
                cropMarks.lineTo(x, y + (side * 2 - 1) * 4);
            }
        }
        g.setStroke(new BasicStroke(1f));
        g.setPaint(new Color(0, 0, 0, 64));
        g.draw(cropMarks);
    }
}
