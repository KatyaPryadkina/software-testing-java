package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{



  @Test
  public void testContactCreation() throws Exception {

    app.getContactsHelper().goToAddNewContact();
    app.getContactsHelper().fillContactForm(new ContactData("Petr", "Petrov", "petya", "Ленина 8", "petrov@mail.ru", "+79521458745", "test4"), true);
    app.getContactsHelper().submitNewContact();
    app.getNavigationHelper().returnToHomePage();
      }


}
