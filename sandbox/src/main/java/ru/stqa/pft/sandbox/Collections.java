package ru.stqa.pft.sandbox;

import java.util.Arrays;
import java.util.List;

public class Collections {
  public static void main(String[] args) {
    /*String[] langs = {"Java", "C#", "Python", "PHP"};
    for (int i = 0; i < langs.length; i++) {
      System.out.println("Я хочу выучить " + langs[i]);
    }*/
    String[] langs = {"Java", "C#", "Python", "PHP"};

   /*  List<String> languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("C#");
    languages.add("Python");
     for (String l : languages) {
      System.out.println("Я хочу выучить " + l);
    }*/
   /* List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");
    for (int i = 0; i < languages.size(); i++) {
      System.out.println("Я хочу выучить " + languages.get(i));
    }*/
    List<String> languages = Arrays.asList("Java", "C#", "Python", "PHP");
    for (String l : languages) {
      System.out.println("Я хочу выучить " + l);

    }
  }
}