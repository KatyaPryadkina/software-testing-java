package ru.stqa.pft.sandbox;

//класс сравнения
public class Equality {
  public static void main(String[] args) {
    String s1 = "firefox";
    // String s2 = new String(s1);
//    String s2 = "fire" + "fox"; //компилятор понял что это один объект
    String s2 = "fire" + Math.sqrt(4.0); //компилятор на этапе компиляции ничего не поймет, вычисление будет происходить позже и создаться другой объект

    System.out.println(s1 == s2); //сравнение ссылок на объект
    System.out.println(s1.equals(s2));//сравненые содержимого объектов
  }
}
