package io.pixelavi.ui.canvas;

import io.pixelavi.ui.Theme;
import io.pixelavi.ui.color.ColorCallback;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created: 20.12.2021 01:26
 * Author: Twitter @niffyeth
 **/

public class Canvas2D extends JComponent implements MouseListener, MouseMotionListener, ColorCallback {

    private static final Color LINE_COLOR = new Color(255, 255, 255, 50);
    private static final Color OFFSET_COLOR = new Color(204, 204, 204);
    private static final Color TRANSPARENT = new Color(0, 0, 0, 0);
    private static final Color BASE_COLOR = Color.WHITE;
    private static final int CANVAS_MULTIPLIER = 36;
    private static final int CANVAS_MAX_SIZE = 26;
    private static final int PADDING = 14;

    private final int[][] matrix = new int[CANVAS_MAX_SIZE][CANVAS_MAX_SIZE];
    private final Dimension target;

    private Color paint = Color.BLACK;
    private DrawMode mode;

    public Canvas2D() {
        int max = CANVAS_MULTIPLIER * CANVAS_MAX_SIZE;
        this.target = new Dimension(max + PADDING, max + (PADDING << 1));
        addMouseMotionListener(this);
        addMouseListener(this);
        setPreferredSize(target);
        setMaximumSize(target);
        setMinimumSize(target);
    }

    private void modify(Point p) {
        int vectorX = (int) Math.floor((p.getX() - PADDING) / CANVAS_MULTIPLIER);
        int vectorY = (int) Math.floor((p.getY() - PADDING) / CANVAS_MULTIPLIER);
        if (vectorX < 0 || vectorY < 0 || vectorX >= CANVAS_MAX_SIZE || vectorY >= CANVAS_MAX_SIZE) return;
        Color color = mode == DrawMode.PAINT ? paint : TRANSPARENT;
        matrix[vectorX][vectorY] = color.getRGB();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension dimension = getSize();
        g.setColor(Theme.COMPONENT.getColor());
        g.fillRect(0, 0, dimension.width, dimension.height);
        for (int x = 0; x < CANVAS_MAX_SIZE; x++) {
            for (int y = 0; y < CANVAS_MAX_SIZE; y++) {
                Color color = new Color(matrix[x][y], true);
                int baseX = x * CANVAS_MULTIPLIER;
                int baseY = y * CANVAS_MULTIPLIER;
                int trans = CANVAS_MULTIPLIER >> 2;
                if (color.getRGB() == 0) {
                    for (int i = baseX; i < baseX + CANVAS_MULTIPLIER; i += trans) {
                        for (int j = baseY; j < baseY + CANVAS_MULTIPLIER; j += trans) {
                            boolean modX = (i % 2) == 0;
                            boolean modY = (j % 2) == 0;
                            g.setColor(modX ? modY ? BASE_COLOR : OFFSET_COLOR : !modY ? BASE_COLOR : OFFSET_COLOR);
                            g.fillRect(i + PADDING, j + PADDING, trans, trans);
                        }
                    }
                } else {
                    g.setColor(color);
                    g.fillRect(baseX + PADDING, baseY + PADDING, CANVAS_MULTIPLIER, CANVAS_MULTIPLIER);
                }
                if (CANVAS_MULTIPLIER < 10) continue;

                g.setColor(LINE_COLOR);
                g.drawLine((x + 1) * CANVAS_MULTIPLIER - 1 + PADDING, y * CANVAS_MULTIPLIER + PADDING, (x + 1) * CANVAS_MULTIPLIER - 1 + PADDING, (y + 1) * CANVAS_MULTIPLIER - 1 + PADDING);
                g.drawLine(x * CANVAS_MULTIPLIER + PADDING, (y + 1) * CANVAS_MULTIPLIER - 1 + PADDING, (x + 1) * CANVAS_MULTIPLIER + PADDING, (y + 1) * CANVAS_MULTIPLIER - 1 + PADDING);

            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.mode = e.getButton() == 1 ? DrawMode.PAINT : DrawMode.ERASE;
        modify(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        modify(e.getPoint());
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void onColorChange(Color color) {
        this.paint = color;
    }
}
