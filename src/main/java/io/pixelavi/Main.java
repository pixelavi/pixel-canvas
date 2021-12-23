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
        TitlebarFrame frame = new TitlebarFrame(null, "rocket-27px.png");
        Titlebar titlebar = frame.getTitlebar();

        titlebar.addCustomUnicodeComponent("✖", () -> System.exit(0));
        titlebar.addCustomUnicodeComponent("_", () -> frame.setState(Frame.ICONIFIED));

        Container container = frame.getContainer();
        Canvas2D canvas2D = new Canvas2D();
        container.add(canvas2D, BorderLayout.CENTER);

        Sidebar sidebar = new Sidebar();
        sidebar.setLayout(new BorderLayout());

        ColorContainer main = new ColorContainer();
        ColorInput info = new ColorInput();

        ColorPalette palette = new ColorPalette();
        palette.addObserver(canvas2D);

        ColorPalettePreview preview = new ColorPalettePreview(palette);
        ColorSelection selection = new ColorSelection();
        selection.addObserver(preview);
        selection.addObserver(info);

        HueSlider slider = new HueSlider();
        slider.addObserver(selection);

        main.add(selection, BorderLayout.NORTH);
        main.add(slider, BorderLayout.CENTER);
        main.add(preview, BorderLayout.WEST);

        ColorContainer south = new ColorContainer();
        ColorIndicator indicator = new ColorIndicator();
        palette.addObserver(indicator);

        ColorContainer input = new ColorContainer();
        ColorInputPreview manual = new ColorInputPreview(palette);
        info.addObserver(manual);
        input.add(info, BorderLayout.CENTER);
        input.add(manual, BorderLayout.WEST);
        south.add(input, BorderLayout.NORTH);

        south.add(indicator, BorderLayout.WEST);
        south.add(palette, BorderLayout.CENTER);

        main.add(south, BorderLayout.SOUTH);
        sidebar.add(main, BorderLayout.NORTH);
        container.add(sidebar, BorderLayout.EAST);

        frame.setUndecorated(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
