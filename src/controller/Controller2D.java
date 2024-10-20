package controller;/*
 *
 * @author PGRF1 FIM UHK
 * @version 2024.cX
 */

import model.Line;
import model.Point;
import model.Polygon;
import rasterizer.*;
import view.Panel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Controller2D {
    private final Panel panel;
    private LineRasterizer lineRasterizer;
    private LineRasterizer lineRasterizerBold; //for bold lines

    private Polygon polygon;
    private PolygonRasterizer polygonRasterizer;

    private ArrayList<Line> lines = new ArrayList<>();
    private Point p1; //first point has to be saved so mouse drag can be shown

    //switching between line mode and polygon mode
    private int paintMode;

    //switch between shift mode
    private int shiftMode;

    public Controller2D(Panel panel) {
        this.panel = panel;

        initObjects(panel.getRasterBufferedImage());
        initListeners(panel);
    }

    public void initObjects(Raster raster) {
        //lineRasterizer = new LineRasterizerGraphics(raster);
        lineRasterizer = new FilledLineRasterizer(raster);
        lineRasterizer.setColor(Color.CYAN);

        lineRasterizerBold = new FilledLineRasterizerBold(raster);
        lineRasterizerBold.setColor(Color.PINK);
        // lineRasterizer.rasterize(new Line(20, 20, 500, 500));

        polygon = new Polygon();
        polygonRasterizer = new PolygonRasterizer(lineRasterizerBold);

        lines = new ArrayList<>();

        paintMode = 1;
        shiftMode = 1;
    }

    public void initListeners(Panel panel) {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
            if(paintMode == 1){ //lines
                p1 = new Point(e.getX(), e.getY());
            }else if(paintMode == 2){ //polygons

                panel.clear(Color.BLACK.getRGB()); //add new point, and repaint whole canvas
                polygon.addPoint(new Point(e.getX(), e.getY()));
                polygonRasterizer.rasterize(polygon);
                panel.repaint();
            }

            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(paintMode == 1){
                    Point p2 = new Point(e.getX(), e.getY());
                    if(shiftMode == 2){ //when shift mode is on
                        //calculate closest horizontal, and vertical point, and check, which is closest
                        p2 = getClosestRounded(p1,p2);
                    }

                    lines.add(new Line(p1.getX(), p1.getY(), p2.getX(), p2.getY())); //add new line to ArrayList and draw the list
                    for(Line line : lines) {
                        lineRasterizerBold.rasterize(line);
                    }
                    panel.repaint();
                }
            }
        });
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(paintMode == 1){
                    panel.clear(Color.BLACK.getRGB());
                    for(Line line : lines) {
                        lineRasterizerBold.rasterize(line);
                    }

                    Point p2 = new Point(e.getX(), e.getY());
                    if(shiftMode == 2){ //when shift mode is on
                        //calculate closest horizontal, and vertical point, and check, which is closest
                        p2 = getClosestRounded(p1,p2);
                    }

                    lineRasterizer.rasterize(new Line(p1.getX(), p1.getY(), p2.getX(), p2.getY()));
                    panel.repaint();
                }
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                if(paintMode == 2){
                    panel.clear(Color.BLACK.getRGB());

                    polygonRasterizer.rasterize(polygon);
                    panel.repaint();

                    int size = polygon.getSize(); //size of polygon
                    if(size > 1){ //2 guide showing potential new lines
                        lineRasterizer.rasterize(new Line(polygon.getPoint(0).getX(), polygon.getPoint(0).getY(), e.getX(), e.getY()));
                        lineRasterizer.rasterize(new Line(polygon.getPoint(size - 1).getX(), polygon.getPoint(size - 1).getY(), e.getX(), e.getY()));
                    }
                    else if(size > 0){ //only show one line, if 2 dont exist yet
                        lineRasterizer.rasterize(new Line(polygon.getPoint(0).getX(), polygon.getPoint(0).getY(), e.getX(), e.getY()));
                    }
                    panel.repaint();
                }
            }
        });
        panel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_C) { //clear canvas
                    initObjects(panel.getRasterBufferedImage());
                    panel.clear(Color.BLACK.getRGB());
                    panel.repaint();
                }else if(e.getKeyCode() == KeyEvent.VK_L) { //switching between line and polygon modes
                    paintMode = 1; //line mode
                }else if(e.getKeyCode() == KeyEvent.VK_P) {
                    paintMode = 2; //polygon mode
                }else if(e.getKeyCode() == KeyEvent.VK_SHIFT) { // 1 is default, 2 is rounded to be horizontal/vertical/diagonal
                    if(shiftMode == 1){
                        shiftMode = 2;
                    }else{
                        shiftMode = 1;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }

    public Point getClosestRounded(Point p1, Point p2){
        Point horizontal = new Point(p2.getX(), p1.getY());
        Point vertical = new Point(p1.getX(), p2.getY());
        Point diagonal = getDiagonalPoint(p1, p2);

        if(p2.distance(horizontal) < p2.distance(vertical)) {
            if(p2.distance(horizontal) < p2.distance(diagonal)){
                p2 = horizontal;
            }else{
                p2 = diagonal;
            }
        }else{
            if(p2.distance(vertical) < p2.distance(diagonal)){
                p2 = vertical;;
            }else{
                p2 = diagonal;
            }
        }
        return p2;
    }

    /**
     *
     * @param p1 starting point from which we are offsetting
     * @param p2 mouse point
     * @return diagonal point
     *
     * function starts by finding quadrant of the mouse relative to starting point of the line,
     * then iterates up/down on each axis based on quadrant, and if the cureent iterated point is not closer to the current,
     * we return the point.
     */
    public Point getDiagonalPoint(Point p1, Point p2){
        Point last = p1;
        Point current = null;
        if(p1.getX() > p2.getX()){ //left quadrant
            if(p1.getY() > p2.getY()){ //upper quadrant
                while(true){
                    current = new Point(last.getX() - 1, last.getY() - 1);
                    if(p2.distance(current) > p2.distance(last)){
                        break;
                    }
                    last = current;
                }
            }else{ //upper
                while(true){
                    current = new Point(last.getX() - 1, last.getY() + 1);
                    if(p2.distance(current) > p2.distance(last)){
                        break;
                    }
                    last = current;
                }
            }
        }else{ //right quadrant
            if(p1.getY() > p2.getY()){ // upper
                while(true){
                    current = new Point(last.getX() + 1, last.getY() - 1);
                    if(p2.distance(current) > p2.distance(last)){
                        break;
                    }
                    last = current;
                }
            }else{ //lower
                while(true){
                    current = new Point(last.getX() + 1, last.getY() + 1);
                    if(p2.distance(current) > p2.distance(last)){
                        break;
                    }
                    last = current;
                }
            }
        }
        return current;
    }

}
