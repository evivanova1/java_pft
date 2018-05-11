package ru.stqa.pft.addressbook;

public class AddContactData {
  private final String firstname;
  private final String middlename;
  private final String mobile;
  private final String homePhone;

  public AddContactData(String firstname, String middlename, String mobile, String homePhone) {
    this.firstname = firstname;
    this.middlename = middlename;
    this.mobile = mobile;
    this.homePhone = homePhone;
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
}
