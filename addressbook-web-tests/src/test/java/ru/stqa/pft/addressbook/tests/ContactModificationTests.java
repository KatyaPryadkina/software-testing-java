package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.*;

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
    app.getContactsHelper().fillContactForm(contact);
    app.getContactsHelper().submitContactModification();
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactsHelper().getContactList();
    Assert.assertEquals(after.size(), before.size());


    before.remove(before.size() - 1);
    before.add(contact);
    Comparator<? super ContactData> byFirstName = Comparator.comparing(ContactData::getFirstname);
    Comparator<? super ContactData> byLastName = Comparator.comparing(ContactData::getLastname);

    before.sort(byFirstName);before.sort(byLastName);
    after.sort(byFirstName);   after.sort(byLastName);
    Assert.assertEquals(before, after);
  }


}
