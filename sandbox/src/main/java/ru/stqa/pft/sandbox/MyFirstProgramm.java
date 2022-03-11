package ru.stqa.pft.sandbox;

public class MyFirstProgramm {

  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Katya!");
    Square s = new Square(5);//объявление переменной при указании его в кач-ве атрибута в функции
   // s.l = 5; убрали из-за того что параметр заполнен выше
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());
    Renctagle k = new Renctagle(4,6);
    //k.a = 4;
   // k.b = 6;
    System.out.println("Площадь прямоугольника со сторонами "+  k.a + " и " + k.b + " = " + k.area());
  }
  public static void hello (String somebody) {

    System.out.println("Hello, " + somebody + "!!!!");
  }


}