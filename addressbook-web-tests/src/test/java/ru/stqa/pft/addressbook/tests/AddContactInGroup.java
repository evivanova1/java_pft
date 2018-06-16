package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactInGroup extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test2"));
    }
    if (app.db().contacts().size() == 0) {
      app.contact().create(new ContactData().withFirstname("Ivan").withLastname("Ivanov")
              .withMiddlename("Ivanovich").withMobilePhone("+79265410230").withHomePhone("84956236520").withEmail("p.ivanov@gmail.com"));
    }
  }

  @Test
  public void testAddContactInGroup() {
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    ContactData contactData = contacts.iterator().next();
    app.contact().selectContact();

    ContactsInGroups before = app.db().contactsInGroups();
    ContactDataInGroups contactsInGroup = before.iterator().next();

    app.contact().addGroupForContact(contactData.inGroup(groups.iterator().next()));

    ContactsInGroups after = app.db().contactsInGroups();
    assertThat(after, equalTo (before.withAdded(contactsInGroup)));
  }
}
