package view;/*
 *
 * @author KŠ - PGRF1 FIM UHK
 * @version 2024.c04.t02
 */

import rasterizer.RasterBufferedImage;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    private RasterBufferedImage rasterBufferedImage;
    private static final int WIDTH = 800, HEIGHT = 600;

    public Panel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        rasterBufferedImage = new RasterBufferedImage(WIDTH, HEIGHT);
        rasterBufferedImage.setClearColor(Color.BLACK.getRGB());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        rasterBufferedImage.repaint(g);
    }

    public void clear(int color) {
        rasterBufferedImage.setClearColor(color);
        rasterBufferedImage.clear();
    }

    public RasterBufferedImage getRasterBufferedImage() {
        return rasterBufferedImage;
    }
}
