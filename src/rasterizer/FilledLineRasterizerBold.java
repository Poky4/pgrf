package rasterizer;/*
 *
 * @author PGRF1 FIM UHK
 * @version 2024.c04.t02
 */

import java.awt.*;

public class FilledLineRasterizerBold extends LineRasterizer {
    public FilledLineRasterizerBold(Raster raster) {
        super(raster);
    }

    @Override
    protected void drawLine(int x1, int y1, int x2, int y2) {
        float k; //angle

        //check for vertical line
        if(x1 != x2){
            k = (float) (y2 - y1) / (x2 - x1);
        }else{ //cant use x for in this case
            k = Integer.MAX_VALUE;
        }

        final float q = y1 - k * x1;

        //cases for all quadrants
        if(Math.abs(y2-y1) < Math.abs(x2 - x1)){
            if(x1 > x2){ //has to be lower to higher, so for cycle will run
                int swap = x1;
                x1 = x2;
                x2 = swap;
            }

            for (int x = x1; x < x2; x++){
                float y = ((k * x) + q);
                raster.setPixel(x, (int) y, this.color.getRGB());
                raster.setPixel(x, (int) y+1, this.color.getRGB());
                raster.setPixel(x, (int) y-1, this.color.getRGB());
                raster.setPixel(x+1, (int) y, this.color.getRGB());
                raster.setPixel(x-1, (int) y, this.color.getRGB());
            }
        }else{
            if(y1 > y2){
                int swap = y1;
                y1 = y2;
                y2 = swap;
            }

            for (int y = y1; y <= y2; y++){
                float x = Math.round((y - q) / k);
                raster.setPixel((int) x, y, this.color.getRGB());
                raster.setPixel((int) x, y+1, this.color.getRGB());
                raster.setPixel((int) x, y-1, this.color.getRGB());
                raster.setPixel((int) x+1, y, this.color.getRGB());
                raster.setPixel((int) x-1, y, this.color.getRGB());
            }
        }
    }
}
