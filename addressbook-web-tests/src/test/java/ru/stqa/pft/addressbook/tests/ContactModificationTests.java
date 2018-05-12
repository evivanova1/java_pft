package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.AddContactData;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {
    app.getContactHelper().selectContact();
    app.getContactHelper().editContact();
    app.getContactHelper().fillAddNewContactForm(new AddContactData("Petr", "Ivanov", "+79265410230", "84956236520", "p.ivanov@gmail.com"));
    app.getContactHelper().submitContactModification();
    app.getContactHelper().goHome();
  }
}
