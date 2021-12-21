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

    public PalettePreviewInteraction(Consumer<MouseEvent> consumer) {
        super(consumer);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(28, 11, 4, 18);
        g.fillRect(21, 18, 18, 4);
        g.setColor(Color.WHITE);
        g.fillRect(29, 12, 2, 16);
        g.fillRect(22, 19, 16, 2);
    }
}
