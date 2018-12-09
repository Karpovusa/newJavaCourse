package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion(){

    if(!app.getContactHelper().isThereAContact())app.getContactHelper().createContact(new ContactData("Gena1", "Krokodil1", "gena_s_avtogenom@fairy.ft", "someGroup"));
    app.getContactHelper().selectContacts();
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().submitContactsDeletion();
    app.getNavigationHelper().gotoHomePage();
  }

}
