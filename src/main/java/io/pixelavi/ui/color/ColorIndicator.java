package io.pixelavi.ui.color;

import javax.swing.*;
import java.awt.*;

/**
 * Created: 21.12.2021 13:30
 * Author: Twitter @niffyeth
 **/

public class ColorIndicator extends JComponent implements ColorCallback {

    private Color color;

    public ColorIndicator() {
        this.setPreferredSize(new Dimension(50, 0));
        this.onColorChange(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Dimension dimension = getSize();
        int baseY = (dimension.height >> 1) - (30 >> 1);
        g.setColor(Color.BLACK);
        g.fillOval(15, baseY, 30, 30);
        g.setColor(Color.WHITE);
        g.fillOval(16, baseY + 1, 28, 28);
        g.setColor(color);
        g.fillOval(17, baseY + 2, 26, 26);
        super.paintComponent(g);
    }

    @Override
    public void onColorChange(Color color) {
        this.color = color;
        this.repaint();
    }
}
