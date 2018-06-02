package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void enterAddNewContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillAddNewContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("home"), contactData.getHomePhone());
    type(By.name("mobile"), contactData.getMobile());
    type(By.name("email"), contactData.getEmail());
    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
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

  public void deleteContact() {
    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
  }

  private void editContactById(int id) {
    WebElement index = wd.findElement(By.cssSelector("input[value='" + id + "']"));
    WebElement lists = index.findElement(By.xpath("self::input/parent::td/parent::tr"));
    List<WebElement> values = lists.findElements(By.tagName("td"));
    values.get(7).findElement(By.tagName("a")).click();
  }

  private void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void submitContactModification() {
    click(By.xpath("//div[@id='content']/form[1]/input[1]"));
  }

  public void create(ContactData contact) {
    gotoAddNewContact();
    fillAddNewContactForm(contact, true);
    enterAddNewContact();
    contactCashe = null;
    returnToHomePage();
  }

  public void modify(ContactData contact) {
    editContactById(contact.getId());
    fillAddNewContactForm(contact, false);
    submitContactModification();
    contactCashe = null;
    goHome();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteContact();
    contactCashe = null;
    alert();
    goHome();
  }

  public boolean isThereAContact() {
    return isElementPresent(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));
  }

  public int getContactCount() {
  return wd.findElements(By.xpath(String.format("//input[@name='selected[]']"))).size();
  }

  private Contacts contactCashe = null;

  public Contacts all() {
    if (contactCashe != null) {
      return new Contacts(contactCashe);
    }
    contactCashe = new Contacts();
    List<WebElement> elements = wd.findElements(By.name("entry"));
    for (WebElement element : elements) {
      List<WebElement> value = element.findElements(By.tagName("td"));
      String firstname = value.get(2).getText();
      String lastname = value.get(1).getText();
      int id = Integer.parseInt(value.get(0).findElement(By.tagName("input")).getAttribute("id"));
      contactCashe.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contactCashe;
  }
}
