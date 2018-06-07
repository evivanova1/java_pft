package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddContactTests extends TestBase {

  @Test
  public void testAddContact() {
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/tree.png");
    ContactData contact = new ContactData().withFirstname("Ivan").withLastname("Ivanov").withMiddlename("Ivanovich")
            .withMobilePhone("+79265410230").withHomePhone("84956236520")
            .withEmail("p.ivanov@gmail.com").withGroup("test1").withPhoto(photo);
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