package io.pixelavi.ui.color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created: 20.12.2021 21:45
 * Author: Twitter @niffyeth
 **/

public class ColorSelection extends JComponent implements MouseListener, MouseMotionListener, IColorUpdate {

    private final ColorSpectrum spectrum;

    public ColorSelection() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 200));
        add(spectrum = new ColorSpectrum());
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    private void onMouseEvent(Point point) {
        Dimension dimension = getSize();
        spectrum.getHint().validateX(point.x, dimension.width);
        spectrum.getHint().validateY(point.y, dimension.height);
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        onMouseEvent(e.getPoint());
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
        onMouseEvent(e.getPoint());
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void onColorUpdate(Color color) {
        spectrum.repaint();
    }

    @Override
    public void onHueUpdate(float hue) {
        this.spectrum.setHue(hue);
    }

    public void addObserver(IColorUpdate update) {
        spectrum.addObserver(update);
    }
}
