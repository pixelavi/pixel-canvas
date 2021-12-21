package io.pixelavi.ui.color;

import java.awt.*;

/**
 * Created: 20.12.2021 21:24
 * Author: Twitter @niffyeth
 **/

public interface ColorPaletteCallback {
    void onPaletteColorAdd(Color color);

    void onPaletteSelection(Color color);
}
