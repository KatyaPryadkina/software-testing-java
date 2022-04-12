package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.firstname());
    type(By.name("lastname"), contactData.lastname());
    type(By.name("nickname"), contactData.nickname());
    type(By.name("address"), contactData.address());
    type(By.name("email"), contactData.email());
    type(By.name("mobile"), contactData.mobilNumber());

   /* if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.group());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }*/
  }


  public void goToAddNewContact() {
    click(By.linkText("add new"));
  }

  public void submitContactModification() {
    click(By.name("update"));
  }


  public void selectObjectById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
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


  public void create(ContactData contact) {
    goToAddNewContact();
    fillContactForm(contact);
    submitNewContact();
    contactCache = null;


  }

  public void modify(ContactData contact) {
    selectObjectById(contact.getId());
    editObject();
    fillContactForm(contact);
    submitContactModification();
    contactCache = null;
  }


  public void delete(ContactData contact) {
    selectObjectById(contact.getId());
    deleteSelectObject();
    acceptDelete();
    contactCache = null;
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();

  }

 /* public List<ContactData> list() {     //списки
    List<ContactData> contacts = new ArrayList<ContactData>();
    List<WebElement> elements = wd.findElements(By.tagName("tr"));
    for (int index = 1; index < elements.size(); index++) {
      int id = Integer.parseInt(elements.get(index).findElement(By.tagName("input")).getAttribute("value"));
      List<WebElement> elements1 = wd.findElements(By.tagName("td"));
      String firstname = elements1.get(2).getText();
      String lastname = elements1.get(1).getText();
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }*/

  private Contacts contactCache = null;

  public Contacts all() {                                               //множество
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.tagName("tr"));
    for (int index = 1; index < elements.size(); index++) {
      int id = Integer.parseInt(elements.get(index).findElement(By.tagName("input")).getAttribute("value"));
      List<WebElement> elements1 = wd.findElements(By.tagName("td"));
      String firstname = elements1.get(2).getText();
      String lastname = elements1.get(1).getText();
      String[] phones = elements1.get(5).getText().split("\n");           //split = резать строки
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withHomeNumber(phones[0]).withMobilNumber(phones[1]).withWorkNumber(phones[2]));
    }
    return new Contacts(contactCache);
  }


  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname).withMobilNumber(mobile).withHomeNumber(home).withWorkNumber(work);
  }

  private void initContactModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();
  }

  //примеры локаторов
  //WebElement checkbox = wd.findElement(By.xpath(String.format("//input[@value='%s']/../..td[8]/a",id))).click();
  //WebElement checkbox = wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a",id))).click();
  //WebElement checkbox = wd.findElement(By.cssSelector(String.format("a[href=edit.php?id=%s']".id))).click();
}
