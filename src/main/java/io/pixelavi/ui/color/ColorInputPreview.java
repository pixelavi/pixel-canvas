package io.pixelavi.ui.color;

import io.pixelavi.ui.drawable.JComponent2D;
import io.pixelavi.ui.drawable.impl.PalettePreviewInteraction;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created: 20.12.2021 23:37
 * Author: Twitter @niffyeth
 **/

public class ColorInputPreview extends JComponent2D implements IColorUpdate {

    private Color color;

    public ColorInputPreview(ColorPaletteCallback callback) {
        this.onColorUpdate(Color.WHITE);
        this.setPreferredSize(new Dimension(50, 0));
        this.register(new Ellipse2D.Double(17, 2, 26, 26), new PalettePreviewInteraction(0, e -> callback.onPaletteColorAdd(color)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.BLACK);
        g.fillOval(15, 0, 30, 30);
        g.setColor(Color.WHITE);
        g.fillOval(16, 1, 28, 28);
        g.setColor(color);
        g.fillOval(17, 2, 26, 26);
        super.paintComponent(g);
    }

    @Override
    public void onColorUpdate(Color color) {
        if (color == null) return;
        this.color = color;
        repaint();
    }

    @Override
    public void onHueUpdate(float hue) {
        repaint();
    }
}
