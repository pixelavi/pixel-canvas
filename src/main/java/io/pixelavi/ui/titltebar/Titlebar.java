package io.pixelavi.ui.titltebar;

import io.pixelavi.ui.Theme;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created: 20.12.2021 01:41
 * Author: Twitter @niffyeth
 **/

public class Titlebar extends TitlebarCore {

    private final String title;
    private BufferedImage image;

    public Titlebar(String title) {
        this.title = title;
        setPreferredSize(new Dimension(0, 40));
    }

    public void setIcon(String name) throws IOException {
        try (InputStream stream = Titlebar.class.getClassLoader().getResourceAsStream(name)) {
            if (stream == null) return;
            this.image = ImageIO.read(stream);
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Dimension dimension = getSize();
        g.setColor(Theme.BACKGROUND.getColor());
        g.fillRect(0, 0, dimension.width, dimension.height);
        if (image != null) {
            int padding = (dimension.height - image.getHeight()) >> 1;
            g.drawImage(image, padding, (dimension.height >> 1) - (image.getHeight() >> 1), null);
        }
        Font font = g.getFont();
        if (title != null) {
            Graphics2D graphics2D = (Graphics2D) g;
            graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            graphics2D.setFont(Theme.BOLD);
            graphics2D.setColor(Color.WHITE);
            graphics2D.drawString(title, 42, 32);
        }
        g.setFont(font);
        super.paintComponent(g);
    }
}
