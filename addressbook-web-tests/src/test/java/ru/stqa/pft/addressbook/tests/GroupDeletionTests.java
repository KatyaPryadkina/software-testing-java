package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;


public class GroupDeletionTests extends TestBase {



  @Test
  public void testGroupDeletion() throws Exception { //public - класс общедоступен
    app.getNavigationHelper().goToGroupPage();
    app.getGroupHelper().selectGroup();
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroupPage();
  }


}
