package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.*;

public class ContactModificationTests extends TestBase {
  @BeforeMethod
  public void unsurePreconditions(){
    app.goTo().homePage();
    if (app.contact().list().size()==0) {
      app.contact().create(new ContactData("Petr", "Petrov", "petya", "Ленина 8", "petrov@mail.ru", "+79521458745", "[none]"));
    }
  }

  @Test//(enabled = false)
  public void contactModification() {

    app.goTo().homePage();
    List<ContactData> before = app.contact().list();
    int index = before.size() - 1;
    ContactData contact = new ContactData(before.get(before.size() - 1).getId(),"Petr", "Petrov", "petya", "Ленина 8", "petrov@mail.ru", "+79521458745", "[none]");
    app.contact().modify(index, contact);
    app.goTo().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size());


    before.remove(index);
    before.add(contact);
    Comparator<? super ContactData> byFirstName = Comparator.comparing(ContactData::getFirstname);
    Comparator<? super ContactData> byLastName = Comparator.comparing(ContactData::getLastname);

    before.sort(byFirstName);before.sort(byLastName);
    after.sort(byFirstName);   after.sort(byLastName);
    Assert.assertEquals(before, after);
  }




}
