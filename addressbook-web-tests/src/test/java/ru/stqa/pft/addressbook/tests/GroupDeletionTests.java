package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;


public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void unsurePreconditions() {
    app.goTo().GroupPage();
    if (app.group().list().size()==0) {
      app.group().create(new GroupData().withName("test4").withHeader("test4").withFooter("test4"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception { //public - класс общедоступен

    List<GroupData> before = app.group().list();
    app.group().delete(before);
    int index = before.size() - 1;
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), index);

    before.remove(index);
    Assert.assertEquals(before, after);
  }




}
