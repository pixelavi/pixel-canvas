package io.pixelavi;

import io.pixelavi.ui.canvas.Canvas2D;
import io.pixelavi.ui.titltebar.Titlebar;
import io.pixelavi.ui.titltebar.TitlebarFrame;

import java.awt.*;

/**
 * Created: 20.12.2021 01:27
 * Author: Twitter @niffyeth
 **/

public class Main {
    public static void main(String[] args) {
        TitlebarFrame frame = new TitlebarFrame("Pixel Canvas", "rocket-32px.png");
        Titlebar titlebar = frame.getTitlebar();

        titlebar.addCustomUnicodeComponent("✖", () -> System.exit(0));
        titlebar.addCustomUnicodeComponent("_", () -> frame.setState(Frame.ICONIFIED));

        Container container = frame.getContainer();
        container.add(new Canvas2D(), BorderLayout.CENTER);

        frame.setUndecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
