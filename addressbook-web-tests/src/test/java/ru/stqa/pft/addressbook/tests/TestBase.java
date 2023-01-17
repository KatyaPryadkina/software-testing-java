package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

  //делигирование
  protected static final  ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

  @BeforeSuite(alwaysRun = true)
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    app.stop();

  }

  public ApplicationManager getApp() {
    return app;
  }
  public void verifyGroupListInUI() {
    if (Boolean.getBoolean("verifyUI")){                        // VM options : -DverifyUI true - включаем проверку в "Edit Configurations"
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();
      assertThat(uiGroups, equalTo(dbGroups.stream()
              .map((g) -> new GroupData().withId(g.getid()).withName(g.getName())) // упростим полученные с бд данные и оставим для сравнения только те данные, которые есть на ui
              .collect(Collectors.toSet())));// соберем эти данные в коллекцию

    }


  }
}
