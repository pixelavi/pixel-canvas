package io.pixelavi.ui.canvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created: 20.12.2021 01:26
 * Author: Twitter @niffyeth
 **/

public class Canvas2D extends JComponent implements MouseListener, MouseMotionListener {

    private static final Color LINE_COLOR = new Color(255, 255, 255, 50);
    private static final Color OFFSET_COLOR = new Color(204, 204, 204);
    private static final Color TRANSPARENT = new Color(0, 0, 0, 0);
    private static final Color PAINT_COLOR = Color.BLACK;
    private static final Color BASE_COLOR = Color.WHITE;
    private static final int CANVAS_MAX_SIZE = 26;

    private static int CANVAS_MULTIPLIER = 1;

    static {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int max = Math.min(screen.width, screen.height);
        Canvas2D.CANVAS_MULTIPLIER = (int) Math.floor((max / 1.5D) / CANVAS_MAX_SIZE);
    }

    private final int[][] matrix = new int[CANVAS_MAX_SIZE][CANVAS_MAX_SIZE];
    private final Dimension target;

    private DrawMode mode;

    public Canvas2D() {
        int max = CANVAS_MULTIPLIER * CANVAS_MAX_SIZE;
        this.target = new Dimension(max, max);
        addMouseMotionListener(this);
        addMouseListener(this);
        setPreferredSize(target);
        setMaximumSize(target);
        setMinimumSize(target);
    }

    private void modify(Point p) {
        int vectorX = (int) Math.floor(p.getX() / CANVAS_MULTIPLIER);
        int vectorY = (int) Math.floor(p.getY() / CANVAS_MULTIPLIER);
        if (vectorX < 0 || vectorY < 0 || vectorX >= CANVAS_MAX_SIZE || vectorY >= CANVAS_MAX_SIZE) return;
        Color color = mode == DrawMode.PAINT ? PAINT_COLOR : TRANSPARENT;
        matrix[vectorX][vectorY] = color.getRGB();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(BASE_COLOR);
        g.fillRect(0, 0, target.width, target.height);
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
                            g.fillRect(i, j, trans, trans);
                        }
                    }
                } else {
                    g.setColor(color);
                    g.fillRect(baseX, baseY, CANVAS_MULTIPLIER, CANVAS_MULTIPLIER);
                }

                if (CANVAS_MULTIPLIER < 10) continue;
                g.setColor(LINE_COLOR);
                g.drawLine((x + 1) * CANVAS_MULTIPLIER - 1, y * CANVAS_MULTIPLIER, (x + 1) * CANVAS_MULTIPLIER - 1, (y + 1) * CANVAS_MULTIPLIER);
                g.drawLine(x * CANVAS_MULTIPLIER, (y + 1) * CANVAS_MULTIPLIER - 1, (x + 1) * CANVAS_MULTIPLIER, (y + 1) * CANVAS_MULTIPLIER - 1);
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
}
