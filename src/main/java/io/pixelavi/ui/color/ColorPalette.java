package io.pixelavi.ui.color;

import io.pixelavi.observer.Observable;
import io.pixelavi.ui.Theme;
import io.pixelavi.ui.WrapLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created: 21.12.2021 00:44
 * Author: Twitter @niffyeth
 **/

public class ColorPalette extends JComponent implements ColorPaletteCallback, Observable<ColorCallback, Color> {

    private final List<ColorCallback> list = new ArrayList<>();

    public ColorPalette() {
        this.setBorder(new EmptyBorder(0, 14, 0, 14));
        this.setLayout(new WrapLayout(0, 4, 4));
        this.setPreferredSize(new Dimension(0, 62));
        this.onPaletteColorAdd(Color.BLACK);
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
        notifyObserver(color);
    }

    @Override
    public void onPaletteSelection(Color color) {
        notifyObserver(color);
    }

    @Override
    public void addObserver(ColorCallback update) {
        list.add(update);
    }

    @Override
    public void notifyObserver(Color color) {
        for (ColorCallback observer : list) {
            observer.onColorChange(color);
        }
    }
}
