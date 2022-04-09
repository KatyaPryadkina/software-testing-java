package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{



  @Test
  public void testContactCreation() throws Exception {
    int before = app.getContactsHelper().getContactCount();
    app.getContactsHelper().goToAddNewContact();
    app.getContactsHelper().createContact(new ContactData("Petr", "Petrov", "petya", "Ленина 8", "petrov@mail.ru", "+79521458745", "[none]"));
    app.getNavigationHelper().returnToHomePage();
    int after = app.getContactsHelper().getContactCount();
    Assert.assertEquals(after, before + 1);

      }


}
