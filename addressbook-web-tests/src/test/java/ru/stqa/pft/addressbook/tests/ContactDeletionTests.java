package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void unsurePreconditions(){

    if (app.db().contacts().size() == 0) {
      app.goTo().homePage();
      app.contact().create(new ContactData().withLastname("Petrov").withFirstname("Petr").withNickname("petya").withAddress("Ленина 8").withEmail1("petrov@mail.ru").withMobilNumber("+79521458745").withGroup("[none]"));
    }
  }
  @Test//(enabled = false)
  public void contactDeletion() {

    //app.goTo().homePage();
    Contacts before = app.db().contacts();//предусловия "получить список контактов"
    ContactData deletedContact = before.iterator().next();
    app.goTo().homePage();
    app.contact().delete(deletedContact);
    Assert.assertEquals(app.contact().count(), before.size() - 1);
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(before.without(deletedContact)));

  }



}
