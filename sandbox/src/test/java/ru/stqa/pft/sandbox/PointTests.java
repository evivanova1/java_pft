package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testArea() {
    Point p1 = new Point(1,3);
    Point p2 = new Point(8,5);
    Assert.assertEquals(p1.distance(p2), 7.280109889280518);
  }
}
