package io.pixelavi.ui.color;

import io.pixelavi.observer.Observable;
import io.pixelavi.ui.Theme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created: 20.12.2021 21:28
 * Author: Twitter @niffyeth
 **/

public class HueSlider extends JComponent implements Observable<IColorUpdate>, MouseMotionListener, MouseListener {

    private final ColorSelectionHint hint = new ColorSelectionHint();
    private final List<IColorUpdate> list = new ArrayList<>();
    private final Color BASE_COLOR = Color.RED;

    private final int PADDING = 15;
    private BufferedImage cache;

    public HueSlider() {
        this.setPreferredSize(new Dimension(0, 40));
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.hint.validateY(20, 40);
        this.hint.setColor(BASE_COLOR);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension dimension = getSize();
        g.setColor(Theme.COMPONENT.getColor());
        g.fillRect(0, 0, dimension.width, dimension.height);
        g.setColor(Color.BLACK);
        g.drawRect(PADDING - 1, PADDING - 1, dimension.width - (PADDING << 1) + 1, dimension.height - (PADDING << 1) + 1);
        this.cache = new BufferedImage(dimension.width - (PADDING << 1), dimension.height - (PADDING << 1), BufferedImage.TYPE_INT_RGB);
        float hueIncrementer = 1f / (cache.getWidth() - 1);
        float hue = 0f;
        float brightness = 1f;
        float saturation = 1f;
        for (int x = 0; x < cache.getWidth(); x++) {
            Color color = Color.getHSBColor(hue, saturation, brightness);
            for (int y = 0; y < cache.getHeight(); y++) {
                this.cache.setRGB(x, y, color.getRGB());
            }
            if ((hue += hueIncrementer) > 1f) {
                hue = 1f;
            }
        }
        g.drawImage(cache, PADDING, PADDING, null);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        hint.draw(graphics2D);
    }

    public Color getColor(int x, int y) {
        if (this.cache == null) return BASE_COLOR;
        return new Color(this.cache.getRGB(x - PADDING, y - PADDING));
    }

    protected void onMouseEvent(MouseEvent e) {
        Point point = e.getPoint();
        Dimension dimension = getSize();
        hint.validateX(point.x, dimension.width);
        Color color = getColor(hint.getX(), hint.getY());
        hint.setColor(color);
        repaint();
        notifyObserver();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        onMouseEvent(e);
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
        onMouseEvent(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void addObserver(IColorUpdate update) {
        list.add(update);
    }

    @Override
    public void notifyObserver() {
        Color color = hint.getColor();
        float hue = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null)[0];
        for (IColorUpdate observer : list) {
            observer.onHueUpdate(hue);
            observer.onColorUpdate(color);
        }
    }
}
