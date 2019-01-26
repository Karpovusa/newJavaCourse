package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.TestBase;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.MantisUser;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.testng.AssertJUnit.assertTrue;


public class ChangePasswordTests extends TestBase {

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();

  }

  @Test
  public void testPasswordChange() throws IOException {

    MantisUser user = app.db().users().get(0);
    Date sysdate = new Date();
    app.auth().login("administrator","root");
    app.goTO().usersPage();
    app.goTO().userSettings(user);
    app.goTO().click(By.cssSelector("input[value='Reset Password']"));
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
    MailMessage mailMessage = mailMessages.stream().filter(m -> m.to.equals(user.getEmail())).filter(m -> m.date.after(sysdate)).findFirst().get();
    String confirmationLink = findConfirmationLink(mailMessage);
    String newPassword="qwerty";
    app.registration().finish(confirmationLink, newPassword);
    assertTrue(app.newSession().login(user.getUsername(),newPassword));



    }
  private String findConfirmationLink(MailMessage mailMessage) {
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }


  }