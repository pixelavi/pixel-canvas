package io.pixelavi.ui.titltebar;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created: 20.12.2021 01:47
 * Author: Twitter @niffyeth
 **/

public class TitlebarFrame extends JFrame {

    private final Titlebar titlebar;
    private final Container container;

    public TitlebarFrame(String title, String resource) {
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.setPreferredSize(new Dimension(1286, 976));

        this.titlebar = new Titlebar(title);
        try {
            titlebar.setIcon(resource);
            setIconImage(titlebar.getImage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        container.add(titlebar, BorderLayout.NORTH);
        this.container = new JPanel(new BorderLayout());
        container.add(this.container, BorderLayout.CENTER);
    }

    public Titlebar getTitlebar() {
        return titlebar;
    }

    public Container getContainer() {
        return container;
    }
}
