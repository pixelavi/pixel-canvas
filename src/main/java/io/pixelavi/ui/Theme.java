package io.pixelavi.ui;

import java.awt.*;
import java.io.IOException;
import java.util.Objects;

/**
 * Created: 20.12.2021 01:33
 * Author: Twitter @niffyeth
 **/

public enum Theme {
    LIGHT_COMPONENT(Color.decode("#40444b")),
    BACKGROUND(Color.decode("#1A1A1A")),
    FOREGROUND(Color.decode("#dcddde")),
    COMPONENT(Color.decode("#2c2f33")),
    UI(Color.decode("#939393"));

    public static Font[] FONTS = new Font[3];
    public static Font BOLD;

    static {
        try {
            Theme.BOLD = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(Theme.class.getClassLoader().getResourceAsStream("PixeloidSansBold.ttf"))).deriveFont((float) 24);
            for (int i = 0; i < FONTS.length; i++) {
                FONTS[i] = Font.createFont(Font.TRUETYPE_FONT, Objects.requireNonNull(Theme.class.getClassLoader().getResourceAsStream("PixeloidSans.ttf"))).deriveFont((float) 18 + (i * 10));
            }
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    private final Color color;

    Theme(final Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }
}
