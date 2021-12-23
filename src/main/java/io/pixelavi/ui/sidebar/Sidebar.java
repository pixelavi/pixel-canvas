package io.pixelavi.ui.sidebar;

import io.pixelavi.ui.Theme;

import javax.swing.*;
import java.awt.*;

/**
 * Created: 20.12.2021 21:32
 * Author: Twitter @niffyeth
 **/

public class Sidebar extends JComponent {

    public Sidebar() {
        setPreferredSize(new Dimension(350, 0));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension dimension = getSize();
        g.setColor(Theme.COMPONENT.getColor());
        g.fillRect(0, 0, dimension.width, dimension.height);
    }
}
