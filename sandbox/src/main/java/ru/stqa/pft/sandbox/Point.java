package ru.stqa.pft.sandbox;

public class Point {
  public double x;
  public double y;


//конструктор
  public  Point (double x, double y) {
    this.x = x;
    this.y = y;


  }



  //метод вычисления раст м/д 2-мя точками
   public   double distanceFunction(Point p){
     return   Math.sqrt((this.x - p.x) * (this.x - p.x) + (this.y - p.y)*(this.y - p.y));

  }



}






