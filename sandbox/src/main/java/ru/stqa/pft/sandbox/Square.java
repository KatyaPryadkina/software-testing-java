package ru.stqa.pft.sandbox;

public class Square {
  public double l;
//обявление конструктора Имя= Имени Класса с параметрами, без возвращаемого зн-я
  public Square(double l) {
   this.l = l;  //this - слово для обращения к переменной в конструкторе
  }
  public  double area() {
    return  this.l * this.l;
  }
}
