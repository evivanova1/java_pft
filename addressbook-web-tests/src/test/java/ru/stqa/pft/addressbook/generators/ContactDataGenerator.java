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
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", contact.getFirstname(), contact.getLastname(), contact.getMiddlename(), contact.getMobilePhone(), contact.getHomePhone(), contact.getWorkPhone(),
              contact.getEmail(), contact.getEmail2(), contact.getEmail3(),
              contact.getAddress(), contact.getEmail()));
    }
    writer.close();
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      contacts.add(new ContactData().withFirstname(String.format("Ivan %s", i)).withLastname(String.format("Ivanov %s", i)).withMiddlename(String.format("Ivanovich %s", i))
              .withMobilePhone(String.format("+79253654102 %s", i)).withHomePhone(String.format("84956235402 %s", i)).withWorkPhone(String.format("84996235403 %s", i))
              .withEmail(String.format("i.ivanov@gmail.com %s", i)).withEmail2(String.format("i.ivanov1@gmail.com %s", i)).withEmail3(String.format("i.ivanov2@gmail.com %s", i))
              .withAddress(String.format("Москва, ул.Балтийская, 25, кв.18 %s", i)));
    }
    return contacts;
  }
}
