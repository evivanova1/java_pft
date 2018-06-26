package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
      }

  public void manageUser() {
    click(By.cssSelector("a[href='/mantisbt-2.15.0/manage_user_page.php']"));
  }
}
