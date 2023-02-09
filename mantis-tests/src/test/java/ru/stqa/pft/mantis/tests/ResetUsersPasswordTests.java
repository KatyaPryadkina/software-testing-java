package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class ResetUsersPasswordTests extends TestBase {

    @BeforeMethod                                             //убрали т.к будет запуск не встроенного почт.сервиса
    public void startMailServer() {
        app.mail().start();
    }


    @Test
    public void testResetPassword() throws IOException, MessagingException {
        String password = "passwordTest";

       // app.james().createUser(user, password);                                                     //добавили создание пользователя на почтовом сервере james

        app.passwordHelper().loginAsAdmin();
        app.passwordHelper().goToUserPage();
        String user = app.passwordHelper().getUserName();
        String email = String.format("user%s@localhost.localdomain", user);
        app.passwordHelper().resetPassword();


        List<MailMessage> mailMessages = app.mail().waitForMail(1, 20000);
        //List<MailMessage> mailMessages = app.james().waitForMail(user, password, 120000);   //изменили способ получения почты на james
        String confirmationLink = findConfirmationLink(mailMessages, email);

        app.registration().finish(confirmationLink, password);
        HttpSession session = app.newSession();
        Assert.assertTrue(session.login(user, password));
        Assert.assertTrue(session.isLoggedInAs(user));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }


    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
