package io.pixelavi.ui.color;

import io.pixelavi.ColorConverter;
import io.pixelavi.observer.Observable;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created: 20.12.2021 21:37
 * Author: Twitter @niffyeth
 **/

public class ColorSpectrum extends JComponent implements Observable<IColorUpdate, Color> {

    private final List<IColorUpdate> list = new ArrayList<>();

    private final ColorSelectionHint hint = new ColorSelectionHint();
    private final Color BASE_COLOR = Color.WHITE;
    private final int PADDING = 15;

    private float hue = Float.MIN_VALUE;
    private BufferedImage cache;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension dimension = getSize();
        g.setColor(Color.BLACK);
        g.drawRect(PADDING - 1, PADDING - 1, dimension.width - (PADDING << 1) + 1, dimension.height - (PADDING << 1) + 1);
        this.cache = new BufferedImage(dimension.width - (PADDING << 1), dimension.height - (PADDING << 1), BufferedImage.TYPE_INT_RGB);
        float brightnessIncrementer = 1f / (cache.getHeight() - 1);
        float saturationIncrementer = 1f / (cache.getWidth() - 1);
        float brightness = 1f;
        float saturation = 0f;
        for (int y = 0; y < cache.getHeight(); y++) {
            for (int x = 0; x < cache.getWidth(); x++) {
                Color color = Color.getHSBColor(hue == Float.MIN_VALUE ? ColorConverter.getHue(BASE_COLOR) : hue, saturation, brightness);
                this.cache.setRGB(x, y, color.getRGB());
                if ((saturation += saturationIncrementer) > 1f) {
                    saturation = 1f;
                }
            }
            if ((brightness -= brightnessIncrementer) < 0) {
                brightness = 0;
            }
            saturation = 0f;
        }
        g.drawImage(cache, PADDING, PADDING, null);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color color = getColor();
        notifyObserver(color);
        hint.setColor(color);
        hint.draw(graphics2D);
    }

    public ColorSelectionHint getHint() {
        return hint;
    }

    public void setHue(float hue) {
        this.hue = hue;
        this.repaint();
    }

    public Color getColor() {
        if (this.cache == null) return BASE_COLOR;
        return new Color(this.cache.getRGB(hint.getX() - PADDING, hint.getY() - PADDING));
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
}
