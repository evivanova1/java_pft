package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test

  public void testContactModification() {
    if (!app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("Petr", "Ivanovich", "+79265410230", "84956236520", "p.ivanov@gmail.com", "test1"));
    }
    app.getContactHelper().selectContact();
    app.getContactHelper().editContact();
    app.getContactHelper().fillAddNewContactForm(new ContactData("Petr", "Ivanovich", "+79265410230", "84956236520", "p.ivanov@gmail.com", null), false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().goHome();
  }
}
