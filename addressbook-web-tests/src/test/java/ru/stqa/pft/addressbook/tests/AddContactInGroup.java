package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class AddContactInGroup extends TestBase {

  @Test
  public void testAddContactInGroup() {
    app.contact().selectContact();
    app.contact().addGroupForContact();
    app.goTo().gotoHomePage();
  }
}
