package io.pixelavi.ui.titltebar.impl;

import io.pixelavi.ui.titltebar.IClickableTitlebarComponent;
import io.pixelavi.ui.titltebar.Titlebar;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Created: 20.12.2021 02:12
 * Author: Twitter @niffyeth
 **/

public class TitlebarButton implements IClickableTitlebarComponent {

    private final Runnable runnable;
    private BufferedImage regular, hovered;
    private String text;
    private boolean hover;

    public TitlebarButton(String text, Runnable runnable) {
        this.runnable = runnable;
        this.text = text;
    }

    public TitlebarButton(String regular, String hovered, Runnable runnable) throws IOException {
        this.runnable = runnable;
        try (InputStream stream = Titlebar.class.getClassLoader().getResourceAsStream(regular)) {
            if (stream == null) return;
            this.regular = ImageIO.read(stream);
        }
        try (InputStream stream = Titlebar.class.getClassLoader().getResourceAsStream(hovered)) {
            if (stream == null) return;
            this.hovered = ImageIO.read(stream);
        }
    }

    public BufferedImage getRegular() {
        return regular;
    }

    public BufferedImage getHovered() {
        return hovered;
    }

    public String getText() {
        return text;
    }

    @Override
    public void onClick() {
        runnable.run();
    }

    @Override
    public void setHover(boolean b) {
        this.hover = b;
    }

    @Override
    public boolean isHovered() {
        return hover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TitlebarButton that = (TitlebarButton) o;
        return Objects.equals(text, that.text) && Objects.equals(runnable, that.runnable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, runnable);
    }
}
