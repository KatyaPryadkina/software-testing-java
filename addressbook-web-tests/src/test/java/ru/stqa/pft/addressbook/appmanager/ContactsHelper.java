package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;
import java.util.Set;

public class ContactsHelper extends HelperBase {

    //сслыку на этот драйвер можно исп только в этом классе
    //private WebDriver wd;
    //конструктор к которому обращается ApplicationManager
    public ContactsHelper(WebDriver wd) {
        super(wd);

    }

    public void returnToContactPage() {
        click(By.linkText("home"));
    }

    public void submitNewContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.firstname());
        type(By.name("lastname"), contactData.lastname());
        //type(By.name("nickname"), contactData.nickname());
        type(By.name("address"), contactData.address());
        type(By.name("email"), contactData.email());
        type(By.name("mobile"), contactData.mobilNumber());
        //attach(By.name("photo"), contactData.getPhoto());

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

  /*public void initContactModificationById() {
    click(By.xpath("//img[@alt='Edit']")); }*/

    private void initContactModificationById(int id) {
    /*WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();*/
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }
    //примеры локаторов
    //WebElement checkbox = wd.findElement(By.xpath(String.format("//input[@value='%s']/../..td[8]/a",id))).click();
    //WebElement checkbox = wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a",id))).click();
    //WebElement checkbox = wd.findElement(By.cssSelector(String.format("a[href=edit.php?id=%s']".id))).click();

    public void deleteSelectObject() {
        click(By.xpath("//input[@value='Delete']"));
        acceptDelete();
    }



    public void filterForGroup(int id) {
        wd.findElement(By.cssSelector("select[name='group']>option[value='" + id + "']")).click();

    }

    private void removeFromGroup() {
        click(By.name("remove"));
    }


    private void returnToContactPageAll() {
        wd.findElement(By.xpath(String.format("//option[@value='[all]']"))).click();
    }


    public void create(ContactData contact) {
        goToAddNewContact();
        fillContactForm(contact);
        submitNewContact();
        returnToContactPage();
        contactCache = null;


    }


    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact);
        submitContactModification();
        contactCache = null;
    }

    public void addInGroup(String name) {

        click(By.name("to_group"));
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(name);
        click(By.name("add"));
        contactCache = null;
    }

    public void removeContactFromGroup() {
        removeFromGroup();

    }
    public void selectGroup(String name) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(name);
    }


    public void delete(ContactData contact) {
        selectObjectById(contact.getId());
        deleteSelectObject();
        returnToContactPage();
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

    public ContactData contactInGroup(Contacts contacts) {
        for (ContactData contact : contacts) {
            Set<GroupData> contactInGroup = contact.getGroups();
            if (contact.getGroups().size() > 0) {
                return contact;
            }
        }
        return null;
    }

    private Contacts contactCache = null;

    public Contacts all() {                                               //множество
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.tagName("tr"));
        for (int index = 1; index < elements.size(); index++) {
            int id = Integer.parseInt(elements.get(index).findElement(By.tagName("input")).getAttribute("value"));
            List<WebElement> elements1 = elements.get(index).findElements(By.tagName("td"));
            String firstname = elements1.get(2).getText();
            String lastname = elements1.get(1).getText();
            // String[] phones = elements1.get(5).getText().split("\n");           //split = резать строки,
            String allPhones = elements1.get(5).getText();
            String allEmails = elements1.get(4).getText();
            String address = elements1.get(3).getText();
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withAllPhones(allPhones).withAddress(address).withAllEmail(allEmails));
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
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");

        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
                .withMobilNumber(mobile).withHomeNumber(home).withWorkNumber(work)
                .withEmail1(email1).withEmail2(email2).withEmail3(email3)
                .withAddress(address);
    }


}
