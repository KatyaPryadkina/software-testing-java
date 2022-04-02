package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

//extends TestBase -наследование , чтобы можно было обращаться к объектам другого класса
public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().goToGroupPage();
    int before = app.getGroupHelper().getCountGroup();
    app.getGroupHelper().createGroup(new GroupData("test4", "test4", "test4"));
    int after = app.getGroupHelper().getCountGroup();
    Assert.assertEquals(after, before + 1);

  }


}
