package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.mantis.model.UserData;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserHelper extends HelperBase {
  public UserHelper(ApplicationManager app) {
    super(app);
  }

  public Set<UserData> all() {
    Set<UserData> users = new HashSet<UserData>();
    List<WebElement> elements = wd.findElements(By.tagName("tbody"));
    for (WebElement element : elements) {
      List<WebElement> value = element.findElements(By.tagName("td"));
      //int id = value.get(0).findElement(By.cssSelector("a[href='manage_user_edit_page.php?user_id=%s']"));
      String username = value.get(0).getText();
      users.add(new UserData().withId(id).withUsername(username));
    }
    return users;
  }
}
