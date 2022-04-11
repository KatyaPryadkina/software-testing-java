package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void unsurePreconditions() {
    app.getNavigationHelper().goToGroupPage();
    if (!app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData(0, "test4", "test4", "test4"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception { //public - класс общедоступен

    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().deleteGroup(before);
    int index = before.size() - 1;
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Assert.assertEquals(before, after);
  }




}
