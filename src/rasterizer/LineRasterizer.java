package rasterizer;/*
 *
 * @author PGRF1 FIM UHK
 * @version 2024.c04.t02
 */

import model.Line;

import java.awt.*;

public abstract class LineRasterizer {
    Raster raster;
    Color color;

    public LineRasterizer(Raster raster) {
        this.raster = raster;
    }

    public void setColor(int color) {
        this.color = new Color(color);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void rasterize(Line line) {
        drawLine(line.getX1(), line.getY1(), line.getX2(), line.getY2());
    }

    protected void drawLine(int x1, int y1, int x2, int y2) {}
}
