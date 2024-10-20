package rasterizer;


import model.Line;
import model.Point;
import model.Polygon;

public class PolygonRasterizer {

    private LineRasterizer lineRasterizer;

    public PolygonRasterizer(LineRasterizer lineRasterizer) {
        this.lineRasterizer = lineRasterizer;
    }

    public void rasterize(Polygon polygon) {


        if(polygon.getSize() < 2)
            return;

        if(polygon.getSize() == 2){
            lineRasterizer.rasterize(new Line(polygon.getPoint(0), polygon.getPoint(1)));
        }

        // cyklus, ktery projde body
        // {
        // nactu indexA = i
        // nactu indexB = i+1
        // pokud indexB == size()
        // indexB bude prvnim bodem
        // nactu objekt Point A
        // nactu objekt Point B
        // vykreslim je pres LineRasterizer
        // }

        for(int i = 0; i < polygon.getSize(); i++) {
            int indexA = i;
            int indexB = i+1;

            if(indexB == polygon.getSize())
                indexB = 0;

            Point A = polygon.getPoint(indexA);
            Point B = polygon.getPoint(indexB);

            lineRasterizer.rasterize(new Line(A, B));
        }
    }

    public void setLineRasterizer(LineRasterizer lineRasterizer) {
        this.lineRasterizer = lineRasterizer;
    }
}
