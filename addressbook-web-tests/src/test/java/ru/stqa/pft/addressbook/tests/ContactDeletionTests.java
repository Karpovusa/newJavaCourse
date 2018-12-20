package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;


public class ContactDeletionTests extends TestBase {

  @Test
  public void testContactDeletion() {


    //check if no contact exists
    if (!app.getContactHelper().isThereAContact())
      app.getContactHelper().createContact(new ContactData("Gena1", "Krokodil1", "gena_s_avtogenom@fairy.ft", "someGroup"));

    //list of contacts before deletion
    List<ContactData> before = app.getContactHelper().getContactList();
    //deletion
    app.getContactHelper().selectContacts(before.get(before.size() - 1).getId());
    app.getContactHelper().deleteSelectedContacts();
    app.getContactHelper().submitContactsDeletion();
    app.getNavigationHelper().gotoHomePage();
    //list of contacts after deletion
    List<ContactData> after = app.getContactHelper().getContactList();

    // assert by size
    Assert.assertEquals(after.size(), before.size() - 1);
    // assert by content
    before.remove(before.size() - 1);
    Assert.assertEquals(before, after);
  }

}
