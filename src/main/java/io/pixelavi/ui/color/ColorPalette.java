package io.pixelavi.ui.color;

import io.pixelavi.ui.Theme;
import io.pixelavi.ui.WrapLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created: 21.12.2021 00:44
 * Author: Twitter @niffyeth
 **/

public class ColorPalette extends JComponent implements ColorPaletteCallback {

    private final ColorCallback callback;

    public ColorPalette(ColorCallback callback) {
        this.setBorder(new EmptyBorder(0, 14, 0, 14));
        this.setLayout(new WrapLayout(0, 4, 4));
        this.setPreferredSize(new Dimension(0, 62));
        this.callback = callback;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension dimension = getSize();
        g.setColor(Theme.COMPONENT.getColor());
        g.fillRect(0, 0, dimension.width, dimension.height);
    }

    @Override
    public void onPaletteColorAdd(Color color) {
        Component[] components = getComponents();
        if (components.length >= 22) {
            remove(components[components.length - 1]);
        }
        add(new ColorPaletteItem(this, color), 0);
        revalidate();
        forward(color);
    }

    @Override
    public void onPaletteSelection(Color color) {
        forward(color);
    }

    private void forward(Color color) {
        callback.onColorChange(color);
    }
}
