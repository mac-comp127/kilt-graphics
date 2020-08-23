package comp127graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(RenderingTestHandler.class)
@Test
public @interface RenderingTest {
}

class RenderingTestHandler implements AfterTestExecutionCallback {
    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        new ImageComparison(context, null, (g, gobj) -> {
            gobj.draw(g);
            visualizeBounds(g, gobj);
            return true;
        }).compare();
    }

    private void visualizeBounds(Graphics2D g, GraphicsObject gobj) {
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
