package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void unsurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test4").withHeader("test4").withFooter("test4"));

    }
//    if (app.group().all().size() == 0)  {            // получение списка групп с интерфейса
//      app.goTo().groupPage();
//      app.group().create(new GroupData().withName("test1"));
//    }
  }


  @Test
  public void testGroupModification() {

    Groups before = app.db().groups();// заменяется в зависимости от получения списков через бд или через интерфейс (app.group().all())
    GroupData modifiedGroup = before.iterator().next();

    GroupData group = new GroupData()
            .withId(modifiedGroup.getid()).withName("test4").withHeader("test2").withFooter("test3");
    app.goTo().GroupPage();
    app.group().modify(group);
    assertThat(app.group().count(), equalTo(before.size()));  // проверка хэша списка групп с интерфейса, остается для доп. контроля, но при получении списков из бд не очень нужно
    Groups after = app.db().groups();

    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

    verifyGroupListInUI();
    

  }




}
