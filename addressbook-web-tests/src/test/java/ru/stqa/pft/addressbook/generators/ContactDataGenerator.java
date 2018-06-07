package ru.stqa.pft.addressbook.generators;

import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);
    File file = new File(args[1]);

    List<ContactData> contacts = generateContacts(count);
    save(contacts, file);
  }

  private static void save(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s\n", contact.getLastname(), contact.getFirstname(),
              contact.getMiddlename(), contact.getAddress(), contact.getEmail(), contact.getMobilePhone(), contact.getHomePhone()));
    }
    writer.close();
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withLastname(String.format("Ivanov %s", i))
              .withFirstname(String.format("Ivan %s", i)).withMiddlename(String.format("Ivanovich %s", i))
              .withAddress(String.format("Москва, ул.Балтийская, 25, кв.18 %s", i)).withEmail(String.format("i.ivanov@gmail.com %s", i))
              .withMobilePhone(String.format("+79253654102 %s", i)).withHomePhone(String.format("84956235402 %s", i)));
    }
    return contacts;
  }
}
