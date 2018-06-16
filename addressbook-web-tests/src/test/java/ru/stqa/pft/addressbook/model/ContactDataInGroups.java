package ru.stqa.pft.addressbook.model;

import javax.persistence.Column;
import javax.persistence.Table;

@Table(name = "address_in_groups")
public class ContactDataInGroups {
  @Column(name = "id")
  private int id;
  @Column(name = "group_id")
  private int groupId;


  public ContactDataInGroups withContactId(int id) {
    this.id = id;
    return this;
  }

  public ContactDataInGroups withGroupId(int groupId) {
    this.groupId = groupId;
    return this;
  }


  public int getId() {
    return id;
  }

  public int getGroupId() {
    return groupId;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactDataInGroups that = (ContactDataInGroups) o;

    if (id != that.id) return false;
    return groupId == that.groupId;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + groupId;
    return result;
  }
}
