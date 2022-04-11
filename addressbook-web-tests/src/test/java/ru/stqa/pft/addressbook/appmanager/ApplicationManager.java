package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  public WebDriver wd;

  private NavigationHelper navigationHelper;
  private ContactsHelper contactsHelper;
  private SessionHelper sessionHelper;
  private GroupHelper groupHelper;
  private String browser;

  public ApplicationManager(String browser) {

    this.browser = browser;
  }



  public void init() {
    if (browser.equals(BrowserType.FIREFOX)) {
      wd = new FirefoxDriver();
    } else if (browser.equals(BrowserType.IE)) {
      wd = new InternetExplorerDriver();
    } else if (browser.equals(BrowserType.CHROME)) {
      wd = new ChromeDriver();
    }

    wd.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);// таймаут ожидания появления всп.окна на форме можно поставить в 0 либо закомментировать, иначе увеличить
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactsHelper = new ContactsHelper(wd);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }


  public GroupHelper group() {
    return groupHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public ContactsHelper contact() {
    return contactsHelper;
  }


}
