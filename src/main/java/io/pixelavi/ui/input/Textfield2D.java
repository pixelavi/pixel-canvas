package io.pixelavi.ui.input;

import io.pixelavi.ui.Theme;

import javax.swing.*;
import java.awt.*;

/**
 * Created: 21.12.2021 14:28
 * Author: Twitter @niffyeth
 **/

public class Textfield2D extends JTextField {

    public Textfield2D() {
        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 1), BorderFactory.createEmptyBorder(3, 3, 3, 3)));
        this.setBackground(Theme.LIGHT_COMPONENT.getColor());
        this.setForeground(Theme.FOREGROUND.getColor());
    }
}
