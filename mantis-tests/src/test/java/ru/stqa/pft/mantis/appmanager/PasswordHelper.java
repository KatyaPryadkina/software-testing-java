package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class PasswordHelper extends HelperBase{
    public PasswordHelper(ApplicationManager app) {
        super(app);
    }
    public void loginAsAdmin() {
        wd.get(app.getProperty("web.baseURL") + "login_page.php");
        type(By.name("username"),"administrator");
        type(By.name("password"), "root");
        click(By.xpath("//input[@value='Login']"));
    }
    public void goToManage(){
        click(By.xpath("//a[contains(text(),'Manage')]"));
    }
    public void goToManageUser(){
        click(By.xpath("//a[contains(text(),'Manage Users')]"));
    }
    public void selectUser(){
        click(By.xpath("//tr[2]/td/a"));
    }
    public void resetPassword(){
        click(By.cssSelector("input[value='Reset Password']"));
    }
    public void  goToUserPage(){
        goToManage();
        goToManageUser();
        selectUser();
    }

    public String getUserName(){
        String user = wd.findElement(By.name("username")).getAttribute("value");
        return user;
    }
}