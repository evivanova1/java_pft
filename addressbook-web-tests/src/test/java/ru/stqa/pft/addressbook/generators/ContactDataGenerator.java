package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contact count")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    save(contacts, new File(file));
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
