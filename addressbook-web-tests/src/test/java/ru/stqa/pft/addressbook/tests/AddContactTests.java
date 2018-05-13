package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class AddContactTests extends TestBase {

  @Test
  public void testAddContact() {
    app.getContactHelper().gotoAddNewContact();
    app.getContactHelper().fillAddNewContactForm(new ContactData("Petr", "Ivanov", "+79265410230", "84956236520", "p.ivanov@gmail.com"));
    app.getContactHelper().enterAddNewContact();
    app.getContactHelper().returnToHomePage();
  }
}