package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Alexey");

    Square s = new Square(5);
    System.out.println("Площадь квардрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4, 6);
    System.out.println("Площадь квардрата со сторонaми " + r.a + " и " + r.b + " = " + r.area());

    Point p1 = new Point(1,3);
    Point p2 = new Point(8,5);
    System.out.println("Расстояние между двумя точками " + "(" + p1.x + "," + p1.y + ")" + " и " +  "("  + p2.x + "," + p2.y + ")" + " = " + distance(p1, p2));
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

  public static double distance (Point p1, Point p2) {
    double distanceX = p1.x - p2.x;
    double distanceY = p1.y - p2.y;
    return Math.sqrt((distanceX * distanceX) + (distanceY * distanceY));

  }
}