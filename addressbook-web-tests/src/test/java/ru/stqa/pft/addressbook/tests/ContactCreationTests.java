package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase{



  @Test
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.getContactsHelper().getContactList();
    app.getContactsHelper().goToAddNewContact();
    ContactData contact = new ContactData("Petr", "Petrov", "petya", "Ленина 8", "petrov@mail.ru", "+79521458745", "[none]");
    app.getContactsHelper().createContact(contact);
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactsHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);

    int max = 0;
    for (ContactData c : after){
      if (c.getId() > max){
        max = c.getId();
      }
    }      //поиск макс.элемента по идентификатору
   
    contact.setId(max);
    before.add(contact);

    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));


  }


}
