package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {


  @Test(enabled = false)
  public void testContactCreation() throws Exception {
    List<ContactData> before = app.getContactsHelper().getContactList();
    app.getContactsHelper().goToAddNewContact();
    ContactData contact = new ContactData("Petr", "Petrov", "petya", "Ленина 8", "petrov@mail.ru", "+79521458745", "[none]");
    app.getContactsHelper().createContact(contact);
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactsHelper().getContactList();
    Assert.assertEquals(after.size(), before.size() + 1);


    before.add(contact);
    //Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    Comparator<? super ContactData> byFirstName = Comparator.comparing(ContactData::getFirstname);
    Comparator<? super ContactData> byLastName = Comparator.comparing(ContactData::getLastname);

    before.sort(byFirstName);before.sort(byLastName);
    after.sort(byFirstName);   after.sort(byLastName);
    Assert.assertEquals(before, after);


  }


}
