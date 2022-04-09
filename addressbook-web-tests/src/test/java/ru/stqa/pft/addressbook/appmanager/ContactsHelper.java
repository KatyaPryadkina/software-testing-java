package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

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

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.firstname());
    type(By.name("lastname"), contactData.lastname());
    type(By.name("nickname"), contactData.nickname());
    type(By.name("address"), contactData.address());
    type(By.name("email"), contactData.email());
    type(By.name("mobile"), contactData.mobilNumber());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.group());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }


  public void goToAddNewContact() {
    click(By.linkText("add new"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void selectObject(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
    //click(By.name("selected[]"));
    //HelperBase.acceptNextAlert = true;
  }

  public void editObject() {
    click(By.xpath("//img[@alt='Edit']"));
  }

  public void deleteSelectObject() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void acceptDelete() {
    wd.switchTo().alert().accept();
  }


  public void createContact(ContactData contact) {
    goToAddNewContact();
    fillContactForm(contact, true);
    submitNewContact();


  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();

  }

  public List<ContactData> getContactList() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd. findElements(By.tagName("tr"));
    for (WebElement element : elements) {
      List<WebElement> elements1 = wd. findElements(By.tagName("td"));
      int id = Integer.parseInt(elements1.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String firstname = elements1.get(2).getText();
      String lastname = elements1.get(1).getText();
      ContactData contact = new ContactData(id, firstname, lastname, null, null, null, null, null);
      contacts.add(contact);
    }
    return contacts;
  }
}
