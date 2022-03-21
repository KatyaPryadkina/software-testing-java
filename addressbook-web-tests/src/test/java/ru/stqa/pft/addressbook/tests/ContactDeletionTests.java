package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase {
  @Test
  public void contactDelition() {
    app.getNavigationHelper().returnToHomePage();
    app.getContactsHelper().selectObject();
    app.getContactsHelper().deleteSelectObject();
  }


}
