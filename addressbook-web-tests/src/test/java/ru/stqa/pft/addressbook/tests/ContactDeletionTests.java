package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void unsurePreconditions(){
    app.goTo().homePage();
    if (app.contact().list().size()==0) {
      app.contact().create(new ContactData().withLastname("Petr").withFirstname("Petrov").withNickname("petya").withAddress("Ленина 8").withEmail("petrov@mail.ru").withMobilNumber("+79521458745").withGroup("[none]"));
    }
  }
  @Test//(enabled = false)
  public void contactDeletion() {

    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    app.contact().deleteContact(index);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Assert.assertEquals(before, after);

  }



}
