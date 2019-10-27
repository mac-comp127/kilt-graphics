package comp127graphics.ui;

import comp127graphics.GraphicsObject;
import comp127graphics.Point;

import javax.swing.JComponent;
import java.awt.Graphics2D;
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

    @Override
    protected void draw(Graphics2D gc) {
        // drawn by top-level container, so nothing to do here
    }

    @Override
    public void setPosition(double x, double y) {
        component.setLocation((int) position.getX(), (int) position.getY());
    }

    @Override
    public boolean testHit(double x, double y) {
        return false;
    }

    @Override
    public Rectangle2D getBounds() {
        return component.getBounds();
    }
}
