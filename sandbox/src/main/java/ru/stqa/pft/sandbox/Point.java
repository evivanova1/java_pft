package ru.stqa.pft.sandbox;

public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point p) {
    double PointX = this.x - p.x;
    double PointY = this.y - p.y;
    return Math.sqrt((PointX * PointX) + (PointY * PointY));

  }
}
