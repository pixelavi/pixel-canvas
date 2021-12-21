package io.pixelavi.ui.drawable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created: 21.12.2021 00:12
 * Author: Twitter @niffyeth
 **/

public abstract class JComponent2D extends JComponent implements MouseMotionListener, MouseListener {

    private final Map<Shape, Component2D> actions = new HashMap<>();

    public JComponent2D() {
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (Component2D component2D : new ArrayList<>(actions.values())) {
            if (!component2D.isStatus()) continue;
            component2D.draw(graphics2D);
        }
    }

    public void register(Shape shape, Component2D component2D) {
        this.actions.put(shape, component2D);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        new ArrayList<>(actions.values()).stream().filter(Component2D::isStatus).findFirst().ifPresent(component2D -> component2D.getConsumer().accept(e));
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Shape[] shapes = actions.keySet().toArray(new Shape[0]);
        for (Shape shape : shapes) {
            Component2D component2D = actions.get(shape);
            boolean status = shape.contains(e.getPoint());
            if (status != component2D.isStatus()) {
                component2D.toggleStatus();
                repaint();
            }
        }
    }
}
