package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RectangleTests {

  @Test
  public void testArea() {
    Renctagle k = new Renctagle(5, 4);
    Assert.assertEquals(k.area() , 20);  //сравнение, = -присваивание

  }
}

