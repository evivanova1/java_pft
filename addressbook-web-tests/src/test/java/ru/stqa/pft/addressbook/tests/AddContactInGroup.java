package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

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
    ContactData contactData = new ContactData();
    Groups groups = app.db().groups();
    app.contact().addGroupForContact(contactData.inGroup(groups.iterator().next()));
  }
}
