package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.AddContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void enterAddNewContact() {
    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void fillAddNewContactForm(AddContactData addContactData) {
    type(By.name("firstname"), addContactData.getFirstname());
    type(By.name("middlename"), addContactData.getMiddlename());
    type(By.name("home"), addContactData.getHomePhone());
    type(By.name("mobile"), addContactData.getMobile());
    type(By.name("email"), addContactData.getEmail());
  }

  public void alert() { wd.switchTo().alert().accept();}

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

  public void editContact() {
    click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
  }

  public void selectContact() { click(By.xpath("//div/div[4]/form[2]/table/tbody/tr[2]/td[1]/input"));  }

  public void submitContactModification() { click(By.xpath("//div[@id='content']/form[1]/input[1]")); }
}
