package rasterizer;/*
 *
 * @author PGRF1 FIM UHK
 * @version 2024.c04.t02
 */

public interface Raster {

    // setPixel, getPixel, clear, setClearColor, getWidth, getHeight

    void clear();

    void setClearColor(int color);

    int getWidth();

    int getHeight();

    int getPixel(int x, int y);

    void setPixel(int x, int y, int color);

}
