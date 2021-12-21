package io.pixelavi.ui.color;

import io.pixelavi.ui.Theme;
import io.pixelavi.ui.drawable.JComponent2D;
import io.pixelavi.ui.drawable.impl.PalettePreviewInteraction;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Created: 20.12.2021 23:37
 * Author: Twitter @niffyeth
 **/

public class ColorPalettePreview extends JComponent2D implements IColorUpdate {

    private Color color;

    public ColorPalettePreview(ColorPaletteCallback callback) {
        this.onColorUpdate(Color.WHITE);
        this.setPreferredSize(new Dimension(50, 0));
        this.register(new Ellipse2D.Double(17, 7, 26, 26), new PalettePreviewInteraction(e -> callback.onPaletteColorAdd(color)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Dimension dimension = getSize();
        g.setColor(Theme.COMPONENT.getColor());
        g.fillRect(0, 0, dimension.width, dimension.height);
        g.setColor(Color.BLACK);
        g.fillOval(15, 5, 30, 30);
        g.setColor(Color.WHITE);
        g.fillOval(16, 6, 28, 28);
        g.setColor(color);
        g.fillOval(17, 7, 26, 26);
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

    }
}
