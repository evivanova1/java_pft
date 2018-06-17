package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteContactFromGroup extends TestBase {
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
    if (app.db().contactsInGroups().size() == 0) {
      Groups groups = app.db().groups();
      Contacts contacts = app.db().contacts();
      app.contact().selectContactById(contacts.iterator().next().getId());
      app.contact().addGroupForContact(contacts.iterator().next().inGroup(groups.iterator().next()));
    }
  }

  @Test
  public void testDeleteContactFromGroup() {
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    ContactsInGroups before = app.db().contactsInGroups();
    ContactData contactData = contacts.iterator().next();
    ContactDataInGroups contactsInGroup = new ContactDataInGroups().withContactId(contactData.getId()).withGroupId(groups.iterator().next().getId());
    app.contact().deleteContactFroumGroup(contactData.inGroup(groups.iterator().next()));
    ContactsInGroups after = app.db().contactsInGroups();
    assertThat(after, equalTo (before.without(contactsInGroup)));
  }
}
