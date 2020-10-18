package edu.macalester.graphics.ui;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

import edu.macalester.graphics.GraphicsObject;

abstract class EmbeddedSwingComponent extends GraphicsObject {

    private final JComponent component;

    EmbeddedSwingComponent(JComponent component) {
        this.component = component;
    }

    @Override
    public JComponent getEmbeddedComponent() {
        return component;
    }

    protected void changed() {
        Dimension preferredSize = component.getPreferredSize();
        component.setSize(
            (int) Math.round(Math.max(preferredSize.getWidth(), component.getMinimumSize().getWidth())),
            (int) Math.round(Math.max(preferredSize.getHeight(), component.getMinimumSize().getHeight())));
        super.changed();
    }

    @Override
    protected void drawInLocalCoordinates(Graphics2D gc) {
        // Swing components are drawn by top-level container, so nothing to do here
    }

    @Override
    public boolean testHitInLocalCoordinates(double x, double y) {
        return getBounds().contains(x, y);
    }

    @Override
    public Rectangle2D getBounds() {
        Rectangle bounds = component.getBounds();
        return new Rectangle2D.Double(
            0,  // Actual JComponent is in canvas coordinates, and doesn't get positioned until next draw
            0,
            bounds.getWidth(),
            bounds.getHeight());
    }
}
