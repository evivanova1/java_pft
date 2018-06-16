package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Groups;

public class AddContactInGroup extends TestBase {

  @Test
  public void testAddContactInGroup() {
    ContactData contactData = new ContactData();
    Groups groups = app.db().groups();
    app.contact().selectContact();
    app.contact().addGroupForContact(contactData.inGroup(groups.iterator().next()));
  }
}
