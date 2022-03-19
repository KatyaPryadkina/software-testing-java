package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactsHelper extends HelperBase {
  //сслыку на этот драйвер можно исп только в этом классе
  //private WebDriver wd;
  //конструктор к которому обращается ApplicationManager
  public ContactsHelper(WebDriver wd) {
    super(wd);

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
}
