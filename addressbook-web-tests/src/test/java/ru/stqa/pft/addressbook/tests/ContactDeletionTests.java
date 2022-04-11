package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void unsurePreconditions(){
    app.getNavigationHelper().returnToHomePage();
    if (!app.getContactsHelper().isThereAContact()) {
      app.getContactsHelper().createContact(new ContactData("Petr", "Petrov", "petya", "Ленина 8", "petrov@mail.ru", "+79521458745", "[none]"));
    }
  }
  @Test//(enabled = false)
  public void contactDeletion() {

    app.getNavigationHelper().returnToHomePage();
    List<ContactData> before = app.getContactsHelper().getContactList();
    int index = before.size() - 1;
    app.getContactsHelper().deleteContact(index);
    app.getNavigationHelper().returnToHomePage();
    List<ContactData> after = app.getContactsHelper().getContactList();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Assert.assertEquals(before, after);

  }



}
