package io.pixelavi.ui.titltebar;

import io.pixelavi.ui.Theme;
import io.pixelavi.ui.misc.SingletonCondition;
import io.pixelavi.ui.titltebar.impl.TitlebarButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created: 20.12.2021 01:51
 * Author: Twitter @niffyeth
 **/

public abstract class TitlebarCore extends JComponent implements MouseListener, MouseMotionListener {

    private final int DEFAULT_COMPONENT_SIZE = 26;

    private Point initialClick;
    private TitlebarButton[] components = new TitlebarButton[0];

    public TitlebarCore() {
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    private void insert(int position, TitlebarButton component) {
        TitlebarButton[] components = new TitlebarButton[this.components.length + 1];
        components[position] = component;
        System.arraycopy(this.components, 0, components, 0, position);
        if (components.length - (position + 1) >= 0) {
            System.arraycopy(this.components, position + 1 - 1, components, position + 1, components.length - (position + 1));
        }
        this.components = components;
    }

    public void addCustomUnicodeComponent(String text, Runnable runnable) {
        addCustomUnicodeComponent(components.length, text, runnable);
    }

    public void addCustomUnicodeComponent(int position, String text, Runnable runnable) {
        TitlebarButton component = new TitlebarButton(text, runnable);
        insert(position, component);
    }

    public void addCustomResourceComponent(String regular, String hovered, Runnable runnable) throws IOException {
        addCustomResourceComponent(components.length, regular, hovered, runnable);
    }

    public void addCustomResourceComponent(int position, String regular, String hovered, Runnable runnable) throws IOException {
        TitlebarButton component = new TitlebarButton(regular, hovered, runnable);
        insert(position, component);
    }

    public void removeComponent(int index) {
        TitlebarButton[] components = new TitlebarButton[this.components.length - 1];
        if (index >= 0) System.arraycopy(this.components, 0, components, 0, index);
        if (components.length - (index + 1) >= 0) {
            System.arraycopy(this.components, index + 1, components, index + 1, components.length - (index + 1));
        }
        this.components = components;
    }

    public void removeComponent(TitlebarButton component) {
        for (int i = 0; i < components.length; i++) {
            if (components[i].equals(component)) {
                TitlebarButton[] components = new TitlebarButton[this.components.length - 1];
                for (int j = 0; j < i; j++) {
                    components[i] = this.components[j];
                }
                for (int j = i + 1; j < components.length; j++) {
                    components[i] = this.components[j];
                }
                this.components = components;
                break;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        FontMetrics metrics = g.getFontMetrics();
        Dimension dimension = getSize();
        int gap = 2, offsetX = dimension.width - gap, ascent = metrics.getAscent();
        for (int i = 0; i < components.length; i++) {
            TitlebarButton component = components[i];
            if (component.isHovered()) g.setColor(Color.WHITE);
            else g.setColor(Theme.FOREGROUND.getColor());
            int x = offsetX - (32 * (1 + i)), y = 7;
            RoundRectangle2D bounds = new RoundRectangle2D.Float(x, y, DEFAULT_COMPONENT_SIZE, DEFAULT_COMPONENT_SIZE, 13, 13);
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            graphics2D.draw(bounds);
            if (component.getText() != null) {
                int width = metrics.stringWidth(component.getText());
                int posX = x + ((DEFAULT_COMPONENT_SIZE >> 1) - (width >> 1));
                int posY = y + ((DEFAULT_COMPONENT_SIZE >> 1) + (ascent >> 1));
                graphics2D.drawString(component.getText(), posX, posY);
            } else {
                BufferedImage image = component.isHovered() ? component.getHovered() : component.getRegular();
                if (image == null) return;
                int posX = x + ((DEFAULT_COMPONENT_SIZE >> 1) - (image.getWidth() >> 1));
                int posY = y + ((DEFAULT_COMPONENT_SIZE >> 1) - (image.getHeight() >> 1));
                graphics2D.drawImage(image, posX, posY, null);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Dimension dimension = getSize();
        this.initialClick = e.getPoint();
        int gap = 2, offsetX = dimension.width - gap;
        for (int i = 0; i < components.length; i++) {
            Rectangle bounds = new Rectangle(offsetX - (32 * (1 + i)), 7, DEFAULT_COMPONENT_SIZE, DEFAULT_COMPONENT_SIZE);
            if (bounds.contains(e.getPoint())) {
                this.initialClick = null;
                this.components[i].onClick();
            }
            components[i].setHover(false);
        }
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
        Window window = SwingUtilities.windowForComponent(this);
        if (!(window instanceof JFrame)) return;
        JFrame source = (JFrame) window;
        if (this.initialClick == null || source.getState() == Frame.ICONIFIED) return;
        final int thisX = source.getLocation().x;
        final int thisY = source.getLocation().y;
        final int xMoved = e.getX() - this.initialClick.x;
        final int yMoved = e.getY() - this.initialClick.y;
        final int X = thisX + xMoved;
        final int Y = thisY + yMoved;
        source.setLocation(X, Y);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Dimension dimension = getSize();
        int gap = 2, offsetX = dimension.width - gap;
        SingletonCondition condition = new SingletonCondition();
        for (int i = 0; i < components.length; i++) {
            Rectangle bounds = new Rectangle(offsetX - (32 * (1 + i)), 7, DEFAULT_COMPONENT_SIZE, DEFAULT_COMPONENT_SIZE);
            components[i].setHover(condition.configure(bounds.contains(e.getPoint())));
        }
        repaint();
    }
}
