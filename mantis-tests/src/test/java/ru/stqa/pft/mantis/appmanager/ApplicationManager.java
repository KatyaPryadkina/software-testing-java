package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private Properties properties;
    private WebDriver wd;


    private String browser;
    private RegistrarionHelper registrationHelper;
    private FtpHelper ftp;
    private MailHelper mailHelper;
    private JamesHelper jamesHelper;
    private PasswordHelper passwordHelper;
    private SoapHelper soapHelper;


    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }


    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
    }

    public void stop() {
        if (wd != null) {
            wd.quit();
        }
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }


    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrarionHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new RegistrarionHelper(this);
        }
        return registrationHelper;
    }

    public FtpHelper ftp() {                                       //помошник, если ftp не инициализирован , то AplicationManager его инициализирует
        if (ftp == null) {
            ftp = new FtpHelper(this);
        }
        return ftp;
    }

    public WebDriver getDriver() {
        if (wd == null) {
            if (browser.equals(BrowserType.CHROME)) {
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.FIREFOX)) {
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.IE)) {
                wd = new InternetExplorerDriver();
                wd.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);// таймаут ожидания появления всп.окна на форме можно поставить в 0 либо закомментировать, иначе увеличить
                wd.get(properties.getProperty("web.baseUrl"));
            }
        }
        return wd;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public JamesHelper james() {                                        //ленивая инициализация
        if (jamesHelper == null) {
            jamesHelper = new JamesHelper(this);
        }
        return jamesHelper;
    }
    public PasswordHelper passwordHelper() {                                        //ленивая инициализация
        if (passwordHelper == null) {
            passwordHelper = new PasswordHelper(this);
        }
        return passwordHelper;
    }

    public  SoapHelper soap() {
        if(soapHelper == null) {
            soapHelper = new SoapHelper(this);
            
        }
        return soapHelper;
    }
}
