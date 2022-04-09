package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactCreationTests extends TestBase{



  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.getContactsHelper().getContactList();
    app.getContactsHelper().goToAddNewContact();
    app.getContactsHelper().createContact(new ContactData("Petr", "Petrov", "petya", "Ленина 8", "petrov@mail.ru", "+79521458745", "[none]"));
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactsHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

      }


}
