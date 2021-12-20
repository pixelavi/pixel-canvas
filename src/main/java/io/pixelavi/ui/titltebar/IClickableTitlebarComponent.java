package io.pixelavi.ui.titltebar;

/**
 * Created: 20.12.2021 01:47
 * Author: Twitter @niffyeth
 **/

public interface IClickableTitlebarComponent {
    void onClick();

    void setHover(boolean b);

    boolean isHovered();
}
