package io.pixelavi;

import io.pixelavi.ui.canvas.Canvas2D;
import io.pixelavi.ui.color.*;
import io.pixelavi.ui.sidebar.Sidebar;
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
        Canvas2D canvas2D = new Canvas2D();
        container.add(canvas2D, BorderLayout.CENTER);

        Sidebar sidebar = new Sidebar();
        sidebar.setLayout(new BorderLayout());

        ColorPalette palette = new ColorPalette(canvas2D);
        ColorContainer color = new ColorContainer();
        ColorSelection selection = new ColorSelection();
        ColorPalettePreview preview = new ColorPalettePreview(palette);
        HueSlider slider = new HueSlider();
        selection.addObserver(preview);
        slider.addObserver(selection);
        color.add(selection, BorderLayout.NORTH);
        color.add(slider, BorderLayout.CENTER);
        color.add(preview, BorderLayout.WEST);
        color.add(palette, BorderLayout.SOUTH);
        sidebar.add(color, BorderLayout.NORTH);
        container.add(sidebar, BorderLayout.EAST);

        frame.setUndecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
