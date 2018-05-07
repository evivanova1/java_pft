package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PointTests2 {
  @Test
  public void testArea() {
    Point p1 = new Point(2,1);
    Point p2 = new Point(6,3);
    Assert.assertEquals(p1.distance(p2), 4.47213595499958);
  }
}
