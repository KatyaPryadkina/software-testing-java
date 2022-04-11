package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void unsurePreconditions(){
    app.goTo().GroupPage();
    if (app.group().list().size()==0) {
      app.group().create(new GroupData().withName("test4").withHeader("test4").withFooter("test4"));
    }
  }


  @Test
  public void testGroupModification() {

    List<GroupData> before = app.group().list();
    int index = before.size() - 1;
    GroupData group = new GroupData()
            .withId(before.get(index).getid()).withName("test4").withHeader("test2").withFooter("test3");
    app.group().modify(index, group);
    List<GroupData> after = app.group().list();
    Assert.assertEquals(after.size(), before.size());

    before.remove(index);
    before.add(group);
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getid(), g2.getid());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);

  }


}
