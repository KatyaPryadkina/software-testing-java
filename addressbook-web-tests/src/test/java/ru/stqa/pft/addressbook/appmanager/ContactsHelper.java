package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.testng.Assert.assertTrue;

public class ContactsHelper extends HelperBase {
  //сслыку на этот драйвер можно исп только в этом классе
  //private WebDriver wd;
  //конструктор к которому обращается ApplicationManager
  public ContactsHelper(WebDriver wd) {
    super(wd);
    boolean acceptNextAlert = true;

  }

  public void submitNewContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"),contactData.firstname());
    type(By.name("lastname"),contactData.lastname());
    type(By.name("nickname"),contactData.nickname() );
    type(By.name("address"),contactData.address() );
    type(By.name("email"),contactData.email() );
    type(By.name("mobile"),contactData.mobilNumber() );

  }

  public void goToAddNewContact() {
    click(By.linkText("add new"));
    }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectObject() {
    click(By.id("1"));
    HelperBase.acceptNextAlert = true;
  }

  public void editObject() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void deleteSelectObject() {
    click(By.xpath("//input[@value='Delete']"));
    assertTrue(closeAlertAndGetItsText().matches("^Delete 1 addresses[\\s\\S]$"));
  }


}
