package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

//extends TestBase -наследование , чтобы можно было обращаться к объектам другого класса
public class GroupCreationTests extends TestBase {


  @Test
  public void testGroupCreation() throws Exception {
    goToGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test4", "test2", "test3"));
    submitGroupCreation();
    returnToGroupPage();

  }


}
