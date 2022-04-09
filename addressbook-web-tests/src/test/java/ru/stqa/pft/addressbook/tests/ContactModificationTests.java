package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.HashSet;
import java.util.List;

public class ContactModificationTests extends TestBase {
  @Test
  public void contactModification() {
    app.getNavigationHelper().returnToHomePage();
    if (!app.getContactsHelper().isThereAContact()) {
      app.getContactsHelper().createContact(new ContactData("Petr", "Petrov", "petya", "Ленина 8", "petrov@mail.ru", "+79521458745", "[none]"));
    }
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> before = app.getContactsHelper().getContactList();
    app.getContactsHelper().selectObject(before.size() - 1);
    app.getContactsHelper().editObject();
    //станд окно
   ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Petr", "Petrov", "petya", "Ленина 8", "petrov@mail.ru", "+79521458745", "[none]");
    app.getContactsHelper().fillContactForm(  new ContactData ("Petr", "Petrov", "petya", "Ленина 8", "petrov@mail.ru", "+79521458745", "[none]"),false);
    app.getContactsHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactsHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());


    before.remove(before.size() - 1);
    before.add(contact);
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }


}
