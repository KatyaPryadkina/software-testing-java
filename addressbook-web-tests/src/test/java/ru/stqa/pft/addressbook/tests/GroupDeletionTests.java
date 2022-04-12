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
    app.goTo().GroupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test4").withHeader("test4").withFooter("test4"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception { //public - класс общедоступен

    Groups before = app.group().all();
    GroupData deletetedGroup = before.iterator().next();
    app.group().delete(deletetedGroup);
    assertThat(app.group().count(), equalTo(before.size() - 1));
    Groups after = app.group().all();

    assertThat(after, equalTo(before.without(deletetedGroup))); //проверка

  }


}
