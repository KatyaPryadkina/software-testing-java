package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {
  @Test
  public void testArea() {
    Point p1 = new Point(2, 3.1);
    Point p2 = new Point(4.1, 2.2);
    Assert.assertEquals(p1.distanceFunction(p2), 2.284731931759172);

  }
}
