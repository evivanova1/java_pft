package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ContactsInGroups extends ForwardingSet<ContactDataInGroups> {
  private Set<ContactDataInGroups> delegate;

  public ContactsInGroups(ContactsInGroups contactsInGroups) {
    this.delegate = new HashSet<ContactDataInGroups>(contactsInGroups.delegate);
  }

  public ContactsInGroups() {
    this.delegate = new HashSet<ContactDataInGroups>();
  }

  public ContactsInGroups(Collection<ContactDataInGroups> contactsInGroups) {
    this.delegate = new HashSet<ContactDataInGroups>(contactsInGroups);
  }

  @Override
  protected Set<ContactDataInGroups> delegate() {
    return delegate;
  }

  public ContactsInGroups withAdded(ContactDataInGroups contactsInGroup) {
    ContactsInGroups contactsInGroups = new ContactsInGroups(this);
    contactsInGroups.add(contactsInGroup);
    return contactsInGroups;
  }

  public ContactsInGroups without(ContactDataInGroups contactsInGroup) {
    ContactsInGroups contactsInGroups = new ContactsInGroups(this);
    contactsInGroups.remove(contactsInGroup);
    return contactsInGroups;
  }
}