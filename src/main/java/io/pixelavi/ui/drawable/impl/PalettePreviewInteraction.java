package io.pixelavi.ui.drawable.impl;

import io.pixelavi.ui.drawable.Component2D;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.function.Consumer;

/**
 * Created: 21.12.2021 00:30
 * Author: Twitter @niffyeth
 **/

public class PalettePreviewInteraction extends Component2D {

    private int baseY;

    public PalettePreviewInteraction(int baseY, Consumer<MouseEvent> consumer) {
        super(consumer);
        this.baseY = baseY;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(28, 6 + baseY, 4, 18);
        g.fillRect(21, 13 + baseY, 18, 4);
        g.setColor(Color.WHITE);
        g.fillRect(29, 7 + baseY, 2, 16);
        g.fillRect(22, 14 + baseY, 16, 2);
    }
}
