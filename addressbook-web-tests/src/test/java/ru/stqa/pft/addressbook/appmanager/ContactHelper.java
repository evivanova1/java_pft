package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void alert() {
    wd.switchTo().alert().accept();
  }

  public void gotoAddNewContact() {
    click(By.linkText("add new"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }

  public void goHome() {
    click(By.linkText("home"));
  }

  public void addToGroup() {
    click(By.name("add"));
  }

  private void removeFromGroup() {
    click(By.name("remove"));
  }

  public void returnToGroupPage(int id) {
    wd.findElement(By.cssSelector(String.format("a[href='./?group=%s']", id))).click();
  }

  public void enterAddNewContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  // public void selectContact() {
  // click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
  //}
  //public void selectContact(int index) {
  // wd.findElements(By.name("selected[]")).get(index).click();
  //}

  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  public void fillAddNewContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobilePhone());
    type(By.name("work"), contactData.getWorkPhone());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("address"), contactData.getAddress());
    //attach(By.name("photo"), contactData.getPhoto());
    if (creation) {
      if (contactData.getGroups().size() > 0) {
        Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void addGroupForContact(ContactData contactData) {
    if (contactData.getGroups().size() > 0) {
      new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
    }
    addToGroup();
    returnToGroupPage(contactData.getGroups().iterator().next().getId());
  }

  public void selectFilter(ContactData contactData) {
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
  }

  public void deleteContactFroumGroup(ContactData contactData) {
    selectFilter(contactData);
    selectContactById(contactData.getId());
    removeFromGroup();
    returnToGroupPage(contactData.getGroups().iterator().next().getId());
  }

  public ContactData infoFromEditForm(ContactData contact) {
    editContactById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work)
            .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
  }

  private void editContactById(int id) {
    WebElement value = wd.findElement(By.cssSelector("input[value='" + id + "']"));
    WebElement row = value.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

    //wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
    //wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]/td[8]/a", id))).click();
    //wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public void create(ContactData contact) {
    gotoAddNewContact();
    fillAddNewContactForm(contact, true);
    enterAddNewContact();
    contactCache = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    editContactById(contact.getId());
    fillAddNewContactForm(contact, false);
    submitContactModification();
    contactCache = null;
    goHome();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    contactCache = null;
    alert();
    goHome();
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[1]"));
  }

  public int count() {
    return wd.findElements(By.xpath(String.format("//input[@name='selected[]']"))).size();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
  }

  private Contacts contactCache = null;

  public Contacts all() {
    if (contactCache != null) {
      return new Contacts(contactCache);
    }
    contactCache = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> value = element.findElements(By.tagName("td"));
      int id = Integer.parseInt(value.get(0).findElement(By.tagName("input")).getAttribute("id"));
      String lastname = value.get(1).getText();
      String firstname = value.get(2).getText();
      String address = value.get(3).getText();
      String allEmails = value.get(4).getText();
      String allPhones = value.get(5).getText();
      contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
    }
    return new Contacts(contactCache);
  }
}
