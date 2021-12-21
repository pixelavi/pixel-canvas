package io.pixelavi.ui.color;

import java.awt.*;

/**
 * Created: 20.12.2021 21:25
 * Author: Twitter @niffyeth
 **/

public interface IColorUpdate {
    void onColorUpdate(Color color);

    void onHueUpdate(float hue);
}
