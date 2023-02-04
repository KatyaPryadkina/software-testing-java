package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {
    Logger logger = LoggerFactory.getLogger(TestBase.class); //логирование
    //делигирование
    protected static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();

    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p) {
        logger.info("Start test" + m.getName() + "with parameters" + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info("Stop test" + m.getName());
    }

    public ApplicationManager getApp() {
        return app;
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {                        // VM options : -DverifyUI true - включаем проверку в "Edit Configurations"
            Groups dbGroups = app.db().groups();
            Groups uiGroups = app.group().all();
            assertThat(uiGroups, equalTo(dbGroups.stream()
                    .map((g) -> new GroupData().withId(g.getid()).withName(g.getName())) // упростим полученные с бд данные и оставим для сравнения только те данные, которые есть на ui
                    .collect(Collectors.toSet())));// соберем эти данные в коллекцию

        }


    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {         // VM options : -DverifyUI true - включаем проверку в "Edit Configurations"
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().all();
            assertThat(uiContacts, equalTo(dbContacts.stream()
                    .map((g) -> new ContactData().withLastname(g.getLastname()).withFirstname(g.getFirstname()).withNickname(g.getNickname()).withAddress(g.getAddress()).
                            withEmail1(g.getEmail1()).withMobilNumber(g.getMobilNumber())) // упростим полученные с бд данные и оставим для сравнения только те данные, которые есть на ui
                    .collect(Collectors.toSet())));// соберем эти данные в коллекцию
        }
    }
}
