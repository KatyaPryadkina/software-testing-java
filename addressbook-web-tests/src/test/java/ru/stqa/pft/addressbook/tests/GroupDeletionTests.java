package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void unsurePreconditions() {

    if (app.db().groups().size() == 0) {
      app.goTo().GroupPage();
      app.group().create(new GroupData().withName("test4").withHeader("test4").withFooter("test4"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception { //public - класс общедоступен

    Groups before = app.db().groups();// заменяется в зависимости от получения списков через бд или через интерфейс (app.group().all())
    GroupData deletetedGroup = before.iterator().next();
    app.goTo().GroupPage();
    app.group().delete(deletetedGroup);
    Groups after = app.db().groups();
    assertThat(app.group().count(), equalTo(before.size() - 1)); // проверка хэша списка групп с интерфейса, остается для доп. контроля, но при получении списков из бд не очень нужно
    assertThat(after, equalTo(before.without(deletetedGroup))); //проверка

  }


}
