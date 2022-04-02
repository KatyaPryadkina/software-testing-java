package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactDeletionTests extends TestBase {
  @Test
  public void contactDelition() {
    app.getNavigationHelper().returnToHomePage();
    if (!app.getContactsHelper().isThereAContact()) {
      app.getContactsHelper().createContact(new ContactData("Petr", "Petrov", "petya", "Ленина 8", "petrov@mail.ru", "+79521458745", "[none]"));
    }
    app.getNavigationHelper().returnToHomePage();
    app.getContactsHelper().selectObject();
    app.getContactsHelper().deleteSelectObject();
  }

}
