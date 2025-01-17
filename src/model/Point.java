package model;/*
 *
 * @author PGRF1 FIM UHK
 * @version 2024.c04.t02
 */

public class Point {

    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(double x, double y) {
        this.x = (int) Math.round(x);
        this.y = (int) Math.round(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int distance(Point p) {
        return Math.abs(x - p.x) + Math.abs(y - p.y);
    }
}
