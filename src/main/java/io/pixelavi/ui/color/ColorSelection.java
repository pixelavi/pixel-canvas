package io.pixelavi.ui.color;

import io.pixelavi.observer.Observable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created: 20.12.2021 21:45
 * Author: Twitter @niffyeth
 **/

public class ColorSelection extends JComponent implements Observable<IColorUpdate>, MouseListener, MouseMotionListener, IColorUpdate {

    private final ColorSelectionHint hint = new ColorSelectionHint();
    private final List<IColorUpdate> list = new ArrayList<>();
    private final ColorSpectrum spectrum;

    public ColorSelection() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 200));
        add(spectrum = new ColorSpectrum());
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        spectrum.drawSpectrum(g);
        hint.draw(g);
        notifyObserver();
    }

    private void onMouseEvent(MouseEvent e) {
        Point point = e.getPoint();
        Dimension dimension = getSize();
        hint.validateX(point.x, dimension.width);
        hint.validateY(point.y, dimension.height);
        hint.setColor(spectrum.getColor(hint.getX(), hint.getY()));
        repaint();
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
    public void onColorUpdate(Color color) {
        this.hint.setColor(spectrum.getColor(hint.getX(), hint.getY()));
    }

    @Override
    public void onHueUpdate(float hue) {
        this.spectrum.setHue(hue);
    }

    @Override
    public void addObserver(IColorUpdate update) {
        list.add(update);
    }

    @Override
    public void notifyObserver() {
        Color color = hint.getColor();
        for (IColorUpdate observer : list) {
            observer.onColorUpdate(color);
        }
    }
}
