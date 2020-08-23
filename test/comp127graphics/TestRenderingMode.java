package comp127graphics;

import static comp127graphics.GraphicsObjectTestSuite.assertChangedAtEachStep;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.util.List;

public enum TestRenderingMode implements ImageComparison.Renderer {
    PLAIN {
        @Override
        public void render(Graphics2D g, GraphicsObject gobj) {
            renderWithBounds(g, gobj);
        }
    },

    STROKED {
        @Override
        public void render(Graphics2D g, GraphicsObject gobj) {
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

            renderWithBounds(g, gobj);
        }
    },

    FILLED {
        @Override
        public void render(Graphics2D g, GraphicsObject gobj) {
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
            renderWithBounds(g, gobj);
        }
    },

    FILLED_AND_STROKED {
        @Override
        public void render(Graphics2D g, GraphicsObject gobj) {
            var fillAndStroke = (Fillable & Strokable) gobj;
            assertChangedAtEachStep(gobj,
                () -> fillAndStroke.setStrokeColor(Color.BLUE),
                () -> fillAndStroke.setStrokeWidth(3),
                () -> fillAndStroke.setFillColor(Color.YELLOW)
            );
            assertTrue(fillAndStroke.isStroked());
            assertTrue(fillAndStroke.isFilled());
            renderWithBounds(g, gobj);
        }
    },
    
    HIT_TEST {
        @Override
        public void render(Graphics2D g, GraphicsObject gobj) {
            var bounds = g.getClipBounds();
            System.out.println(bounds);
        }
    };

    private static void renderWithBounds(Graphics2D g, GraphicsObject gobj) {
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
