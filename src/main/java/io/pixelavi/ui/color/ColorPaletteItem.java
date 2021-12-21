package io.pixelavi.ui.color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

/**
 * Created: 21.12.2021 01:06
 * Author: Twitter @niffyeth
 **/

public class ColorPaletteItem extends JComponent implements MouseListener {
    private final ColorPaletteCallback callback;
    private final Color color;

    public ColorPaletteItem(ColorPaletteCallback callback, Color color) {
        this.setPreferredSize(new Dimension(25, 25));
        this.addMouseListener(this);
        this.callback = callback;
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension dimension = getSize();
        Shape outline = new RoundRectangle2D.Double(0, 0, dimension.width, dimension.height, 10, 10);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setColor(Color.BLACK);
        graphics2D.fill(outline);
        Shape shape = new RoundRectangle2D.Double(1, 1, dimension.width - 2, dimension.height - 2, 10, 10);
        graphics2D.setColor(color);
        graphics2D.fill(shape);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        callback.onPaletteSelection(color);
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
}
