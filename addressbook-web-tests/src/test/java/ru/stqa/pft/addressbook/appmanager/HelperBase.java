package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;

public class HelperBase {

  protected static boolean acceptNextAlert;
  protected WebDriver wd;

  public HelperBase(WebDriver wd) {
    this.wd=wd;
  }

  protected void click(By locator) {
    wd.findElement(locator).click();
  }

  protected void type(By locator, String text) {
    click(locator);

        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);


  }

  public void acceptDelete() {
    wd.switchTo().alert().accept();
  }


  //не используемый пока нигде метод, ссылка на параметр ему не нужна, он польз.тем что объявлен в классе //конструкция перехвата исключений try-попытка, catch-перехватить(тип исключения)

  public  boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
     }
  }
  // для проставления галочки в списке и закрытия окна при удалении
  public String closeAlertAndGetItsText() {
        try {
      Alert alert = wd.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }

  protected boolean isElementPresent(By locator) {//поиск элемента на странице, если элемент не найдет срабатывает исключение
    try {
      wd.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;

    }
  }
}
