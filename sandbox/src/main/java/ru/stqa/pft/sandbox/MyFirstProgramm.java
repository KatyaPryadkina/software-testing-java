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
    Renctagle k = new Renctagle(4, 6);
    /*k.a = 4;
    k.b = 6;*/
    System.out.println("Площадь прямоугольника со сторонами " + k.a + " и " + k.b + " = " + k.area());

    //расстояние между точками
    Point p1 = new Point(2, 3.1);
    Point p2 = new Point(4.1, 2.2);
    //при вызове функции
    //System.out.println("Расстояние между точками " + "(" + p1.x + "," + p1.y + ")" + ":" + "(" + p2.x + "," + p2.y + ")" + " = " + p1.distance(p1, p2));
    //при вызове  метода
    System.out.println("Расстояние между точками " + "(" + p1.x + "," + p1.y + ")" + ":" + "(" + p2.x + "," + p2.y + ")" + " = " + p1.distanceFunction(p2));


//ниже описано обращение к методу в рамках одного класса
    /*Point p1 = new Point(2.0 , 3.1);
     Point p2 = new Point(4.1,2.2);

    System.out.println("Расстояние между точками "+ "(" + p1.x + "," + p1.y + ")" + ":" + "(" + p2.x + "," + p2.y + ")" + " = " +  distance(p1,p2));

       }

  public static  double distance(Point p1, Point p2){
         return Math.sqrt((p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y)*(p2.y - p1.y));
  }*/
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!!!!");
  }
}

