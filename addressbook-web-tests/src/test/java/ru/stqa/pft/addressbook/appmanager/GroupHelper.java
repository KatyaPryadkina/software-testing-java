package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper extends HelperBase{

  public GroupHelper(WebDriver wd) {
    super(wd);
  }
//super - обращение к конструктору базового класса
  public void returnToGroupPage() {
    click(By.linkText("groups"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.name());
    type(By.name("group_header"), groupData.header());
    type(By.name("group_footer"), groupData.footer());
  }

  //protected - для методов не только в классах наследников, а еще в классах в том же самом пакете
  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelectedGroups() {  //protected - обращение к методу из того же самого класса и из классов наследников
    click(By.name("delete"));
  }

  public void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
      }

  public void initGroupModification() {
    click(By.name("edit"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public void createGroup(GroupData group) {
    initGroupCreation();
    fillGroupForm(group);
    submitGroupCreation();
    returnToGroupPage();
  }

  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getCountGroup() {
    return wd.findElements(By.name("selected[]")).size();
  }
}
