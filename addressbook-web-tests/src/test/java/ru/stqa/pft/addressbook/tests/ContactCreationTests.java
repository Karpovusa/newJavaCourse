package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
      //list of contacts before creation
      List<ContactData> before = app.getContactHelper().getContactList();
      //creation
      ContactData contact = new ContactData("Gena1", "Krokodil1", "gena_s_avtogenom@fairy.ft", "someGroup");
      app.getContactHelper().createContact(contact);
      app.getNavigationHelper().gotoHomePage();
      //list of contacts after creation
      List<ContactData> after = app.getContactHelper().getContactList();

      //assert by size
      Assert.assertEquals(after.size(), before.size() + 1);

      //assert by content
      before.add(contact);
      Comparator<? super ContactData> byId = (c1, c2)-> Integer.compare(c1.getId(),c2.getId());
      before.sort(byId);
      after.sort(byId);
      Assert.assertEquals(before, after);


    }


}
