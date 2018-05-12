package ru.stqa.pft.addressbook.model;

public class AddContactData {
  private final String firstname;
  private final String middlename;
  private final String mobile;
  private final String homePhone;
  private final String email;

  public AddContactData(String firstname, String middlename, String mobile, String homePhone, String email) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.mobile = mobile;
    this.homePhone = homePhone;
    this.email = email;
  }

  public String getFirstname() {
    return firstname;
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
}
