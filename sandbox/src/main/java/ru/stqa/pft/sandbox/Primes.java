package ru.stqa.pft.sandbox;

public class Primes {
  //int -32 битные числа
  public static boolean isPrime(int n) {
    for (int i = 2; i < n; i++) {
      if (n % i == 0){
        return false;
      }
    }
    return true;
  }

  public static boolean isPrimeFast(int n) {
    int m = (int) Math.sqrt(n);
    for (int i = 2; i < m; i++) {
      if (n % i == 0){
        return false;
      }
    }
    return true;
  }


  public static boolean isPrimeWhile(int n) {
    int i = 2 ;
    while (i < n && n % i != 0) {
            i++;
    }
    return i == n ;
  }
  //long -64 битные числа
  public static boolean isPrime(long n) {
    for (long i = 2; i < n; i++) {
      if (n % i == 0){
        return false;
      }
    }
    return true;
  }
}
