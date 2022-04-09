package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
  @Test
  public void contactDeletion() {
    app.getNavigationHelper().returnToHomePage();
    if (!app.getContactsHelper().isThereAContact()) {
      app.getContactsHelper().createContact(new ContactData("Petr", "Petrov", "petya", "Ленина 8", "petrov@mail.ru", "+79521458745", "[none]"));
    }
    app.getNavigationHelper().returnToHomePage();
    int before = app.getContactsHelper().getContactCount();
    app.getContactsHelper().selectObject();
    app.getContactsHelper().deleteSelectObject();
    app.getNavigationHelper().returnToHomePage();
    int after = app.getContactsHelper().getContactCount();
    Assert.assertEquals(after, before - 1);
  }

}
