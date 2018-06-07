package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactTests extends TestBase {
  @DataProvider
  public Iterator<Object[]> validContacts() {
    List<Object[]> list = new ArrayList<Object[]>();
    File photo = new File("src/test/resources/tree.png");
    list.add(new Object[]{new ContactData().withFirstname("Ivan").withLastname("Ivanov").withMiddlename("Ivanovich")
            .withMobilePhone("+79265410230").withHomePhone("84956236520")
            .withEmail("p.ivanov@gmail.com").withGroup("test1").withPhoto(photo)});
    list.add(new Object[]{new ContactData().withFirstname("Ivan1").withLastname("Ivanov1").withMiddlename("Ivanovich1")
            .withMobilePhone("+79265410231").withHomePhone("84956236521")
            .withEmail("p.ivanov1@gmail.com").withGroup("test2").withPhoto(photo)});
    list.add(new Object[]{new ContactData().withFirstname("Ivan2").withLastname("Ivanov2").withMiddlename("Ivanovich2")
            .withMobilePhone("+79265410232").withHomePhone("84956236522")
            .withEmail("p.ivanov2@gmail.com").withGroup("test3").withPhoto(photo)});
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testAddContact(ContactData contact) {
    Contacts before = app.contact().all();
    app.contact().create(contact);
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
    assertThat(after, equalTo
            (before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

/*
  @Test
  public void testCurrentDir() {
    File currentDir = new File(".");
    System.out.println(currentDir.getAbsolutePath());
    File photo = new File("src/test/resources/tree.png");
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }*/
}