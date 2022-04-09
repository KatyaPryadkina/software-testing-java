package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
  @Test
  public void contactModification() {
    app.getNavigationHelper().returnToHomePage();
    if (!app.getContactsHelper().isThereAContact()) {
      app.getContactsHelper().createContact(new ContactData("Petr", "Petrov", "petya", "Ленина 8", "petrov@mail.ru", "+79521458745", "[none]"));
    }
    app.getNavigationHelper().returnToHomePage();
    int before = app.getContactsHelper().getContactCount();
    app.getContactsHelper().selectObject(before - 1);
    app.getContactsHelper().editObject();
    //станд окно
    app.getContactsHelper().fillContactForm(new ContactData("Petr", "Petrov", "petya", "Ленина 8", "petrov@mail.ru", "+79521458745", "[none]"), false);
    app.getContactsHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    int after = app.getContactsHelper().getContactCount();
    Assert.assertEquals(after, before);
  }


}
