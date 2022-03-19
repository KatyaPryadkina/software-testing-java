package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper {
  private WebDriver wd;

  public SessionHelper(WebDriver wd) {

    this.wd = wd;
  }


  public void login(String username, String password) {
    wd.get("http://127.0.0.1/addressbook/group.php");
    wd.findElement(By.name("user")).click();
    wd.findElement(By.name("user")).clear();
    wd.findElement(By.name("user")).sendKeys(username);
    wd.findElement(By.name("pass")).clear();
    wd.findElement(By.name("pass")).sendKeys(password);
    wd.findElement(By.xpath("//input[@value='Login']")).click();
  }
}
