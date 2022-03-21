package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {
  @Test
  public void contactModification() {
    app.getNavigationHelper().returnToHomePage();
    app.getContactsHelper().selectObject();
    app.getContactsHelper().editObject();
    //станд окно
    app.getContactsHelper().fillContactForm(new ContactData("Petr", "Petrov", "petya", "Ленина 8", "petrov@mail.ru", "+79521458745"));
    app.getContactsHelper().submitContactModification();
  }


}
