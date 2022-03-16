package ru.stqa.pft.sandbox;

public class MyFirstProgramm {

  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Katya!");
   //квадрат
    Square s = new Square(5);//объявление переменной при указании его в кач-ве атрибута в функции
   // s.l = 5; убрали из-за того что параметр заполнен выше
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    //прямоугольник
    Renctagle k = new Renctagle(4,6);
    /*k.a = 4;
    k.b = 6;*/
   System.out.println("Площадь прямоугольника со сторонами "+  k.a + " и " + k.b + " = " + k.area());

    //расстояние между точками

    Point p1 = new Point();
    Point p2 = new Point();
    p1.x1 = 2.0;
    p2.x2 = 4.1;
    p1.y1 = 3.1;
    p2.y2 = 2.2;
    System.out.println("Расстояние между точками "+ "(" + p1.x1 + "," + p1.y1 + ")" + ":" + "(" + p2.x2 + "," + p2.y2 + ")" + " = " +  distance(p1,p2));

     /*Point p1 = new Point().p1(2.0,3.1);
     Point p2 = new Point().p2(4.1,2.2);

     System.out.println("Расстояние между точками"+ p1 + ":" + p2 + " = " +  distance()) ;*/
  }

  public static  double distance(Point p1, Point p2){
         return Math.sqrt((p2.x2 - p1.x1) * (p2.x2 - p1.x1) + (p2.y2 - p1.y1)*(p2.y2 - p1.y1));
  }


  public static void hello (String somebody) {

    System.out.println("Hello, " + somebody + "!!!!");
  }
}