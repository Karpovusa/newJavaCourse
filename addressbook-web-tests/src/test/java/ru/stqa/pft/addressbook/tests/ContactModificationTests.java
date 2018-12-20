package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;


public class ContactModificationTests extends TestBase {
  @Test
  public void testContactModification() {
    //check if no contact exists
    if (!app.getContactHelper().isThereAContact())
      app.getContactHelper().createContact(new ContactData("Gena1", "Krokodil1", "gena_s_avtogenom@fairy.ft", "someGroup"));

    //list of contacts before modification
    List<ContactData> before = app.getContactHelper().getContactList();

    //modification
    app.getContactHelper().initContactModification();
    ContactData modifiedContact = new ContactData(before.get(before.size() - 1).getId(), "Gennadiy", "El Croco", "modifiedcontact@viva.me", null);

    app.getContactHelper().fillContactForm(modifiedContact, false);
    app.getContactHelper().submitContactModification();
    app.getContactHelper().returnToHomepage();

    //list of contacts bafter modification
    List<ContactData> after = app.getContactHelper().getContactList();

    before.remove(before.size() - 1);
    before.add(modifiedContact);
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);


  }

}
