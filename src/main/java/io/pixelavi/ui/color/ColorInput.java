package io.pixelavi.ui.color;

import io.pixelavi.ColorConverter;
import io.pixelavi.observer.Observable;
import io.pixelavi.ui.Theme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created: 21.12.2021 13:32
 * Author: Twitter @niffyeth
 **/

public class ColorInput extends JComponent implements ColorInputCallback, IColorUpdate, Observable<IColorUpdate, Color> {

    private final List<IColorUpdate> list = new ArrayList<>();
    private final JTextField[] rgb = new JTextField[3];
    private final JTextField hex;

    public ColorInput() {
        this.setBorder(new EmptyBorder(5, 13, 5, 12));
        this.setLayout(new GridLayout(0, 4, 4, 0));
        this.setPreferredSize(new Dimension(0, 30));
        this.rgb[0] = new ColorValueField(this, ColorValueType.RGB);
        this.rgb[1] = new ColorValueField(this, ColorValueType.RGB);
        this.rgb[2] = new ColorValueField(this, ColorValueType.RGB);
        this.hex = new ColorValueField(this, ColorValueType.HEX);
        this.onColorUpdate(Color.WHITE);
        this.add(rgb[0]);
        this.add(rgb[1]);
        this.add(rgb[2]);
        this.add(hex);
    }

    @Override
    public void onColorUpdate(Color color) {
        int argb = color.getRGB();
        hex.setText(String.format("#%06X", (0xFFFFFF & argb)));
        int[] rgb = ColorConverter.convertToRGB(color);
        this.rgb[0].setText(String.valueOf(rgb[0]));
        this.rgb[1].setText(String.valueOf(rgb[1]));
        this.rgb[2].setText(String.valueOf(rgb[2]));
        notifyObserver(Theme.COMPONENT.getColor());
    }

    @Override
    public void onHueUpdate(float hue) {

    }

    @Override
    public void addObserver(IColorUpdate update) {
        list.add(update);
    }

    @Override
    public void notifyObserver(Color color) {
        for (IColorUpdate observer : list) {
            observer.onColorUpdate(color);
        }
    }

    @Override
    public void forward(ColorValueType type) {
        if (type == ColorValueType.HEX) {
            notifyObserver(Color.decode(hex.getText()));
        } else {
            if (!type.validate(rgb[0].getText()) || !type.validate(rgb[1].getText()) || !type.validate(rgb[2].getText())) {
                return;
            }
            int r = Integer.parseInt(rgb[0].getText());
            int g = Integer.parseInt(rgb[1].getText());
            int b = Integer.parseInt(rgb[2].getText());
            Color color = new Color(r, g, b);
            notifyObserver(color);
        }
    }
}
