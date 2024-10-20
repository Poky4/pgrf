package model;


import java.util.ArrayList;

public class Polygon {

 private final ArrayList<Point> points;

 public Polygon() {
  this.points = new ArrayList<>();
 }

 public void addPoint(Point p) {
  this.points.add(p);
 }

 public Point getPoint(int index) {
  return this.points.get(index);
 }

 public int getSize() {
  return this.points.size();
 }


}
