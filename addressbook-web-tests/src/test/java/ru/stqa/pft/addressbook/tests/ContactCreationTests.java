package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{



  @Test
  public void testContactCreation() throws Exception {

    app.goToAddNewContact();
    app.fillContactForm(new ContactData("Petr", "Petrov", "petya", "Ленина 8", "petrov@mail.ru", "+79521458745"));
    app.submitNewContact();
    app.returnToHomePage();
      }


}
