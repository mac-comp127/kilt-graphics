package comp127graphics.ui;

import comp127graphics.GraphicsObject;
import comp127graphics.Point;

import javax.swing.JComponent;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

abstract class EmbeddedSwingComponent extends GraphicsObject {

    private final JComponent component;
    private Point position = Point.ORIGIN;

    EmbeddedSwingComponent(JComponent component) {
        this.component = component;
    }

    @Override
    public JComponent getEmbeddedComponent() {
        return component;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    protected void changed() {
        Dimension preferredSize = component.getPreferredSize();
        component.setSize(
            (int) Math.round(Math.max(preferredSize.getWidth(), component.getMinimumSize().getWidth())),
            (int) Math.round(Math.max(preferredSize.getHeight(), component.getMinimumSize().getHeight())));
        super.changed();
    }

    @Override
    protected void draw(Graphics2D gc) {
        // Swing components are drawn by top-level container, so nothing to do here
    }

    @Override
    public void setPosition(double x, double y) {
        this.position = new Point(x, y);
        changed();
    }

    @Override
    public boolean testHit(double x, double y) {
        return getBounds().contains(x, y);
    }

    @Override
    public Rectangle2D getBounds() {
        Rectangle bounds = component.getBounds();
        return new Rectangle2D.Double(
            getPosition().getX(),  // Actual JComponent doesn't get positioned until next draw
            getPosition().getY(),
            bounds.getWidth(),
            bounds.getHeight());
    }
}
