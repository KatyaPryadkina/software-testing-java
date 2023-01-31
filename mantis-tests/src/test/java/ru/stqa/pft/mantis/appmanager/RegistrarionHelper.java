package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistrarionHelper {
    private final ApplicationManager app;
    private WebDriver wd;

    public RegistrarionHelper(ApplicationManager app) {
        this.app = app;
        wd = app.getDriver();
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseURL") + "signup_page.php");
    }
}
