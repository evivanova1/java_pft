package ru.stqa.pft.sandbox;

public class Point {

  public double x;
  public double y;

  public Point (double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance (Point p) {
    double distanceX = this.x - p.x;
    double distanceY = this.y - p.y;
    return Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));

  }

}
