package ru.stqa.pft.addressbook.model;

public class ContactData {
  private final String firstname;
  private final String lastname;
  private final String middlename;
  private final String mobile;
  private final String homePhone;
  private final String email;
  private String group;

  public ContactData(String firstname, String lastname, String middlename, String mobile, String homePhone, String email, String group) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.middlename = middlename;
    this.mobile = mobile;
    this.homePhone = homePhone;
    this.email = email;
    this.group = group;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getMobile() {
    return mobile;
  }

  public String getHomePhone() {
    return homePhone;
  }

  public String getEmail() {
    return email;
  }

  public String getGroup() {
    return group;
  }
}
