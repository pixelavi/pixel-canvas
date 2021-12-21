package io.pixelavi.ui.color;

import java.awt.*;

/**
 * Created: 20.12.2021 21:43
 * Author: Twitter @niffyeth
 **/

public class ColorSelectionHint {

    private final int OUTER = 22, INNER = 20, PADDING = 6, INSET = 15;

    private Color color;
    private int x = INSET, y = INSET;

    public void validateY(int y, int height) {
        this.y = (y < INSET) ? INSET : Math.min(y, height - 1 - INSET);
    }

    public void validateX(int x, int width) {
        this.x = (x < INSET) ? INSET : Math.min(x, width - 1 - INSET);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics2D g) {
        int outerCenterX = x - (OUTER >> 1);
        int outerCenterY = y - (OUTER >> 1);

        g.setColor(Color.BLACK);
        g.fillOval(outerCenterX, outerCenterY, OUTER, OUTER);

        int innerCenterX = x - (INNER >> 1);
        int innerCenterY = y - (INNER >> 1);

        g.setColor(Color.WHITE);
        g.fillOval(innerCenterX, innerCenterY, INNER, INNER);

        g.setColor(color);
        g.fillOval(innerCenterX + (PADDING >> 1), innerCenterY + (PADDING >> 1), INNER - PADDING, INNER - PADDING);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
