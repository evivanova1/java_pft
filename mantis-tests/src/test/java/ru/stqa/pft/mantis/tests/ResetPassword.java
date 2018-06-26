package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class ResetPassword extends TestBase{

  //@BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testResetPassword() throws IOException {
    app.loginHelper().login("administrator", "root1");
    app.goTo().manageUser();


  }

  //@AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}
