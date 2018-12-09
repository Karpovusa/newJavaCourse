package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        app.getContactHelper().createContact(new ContactData("Gena1", "Krokodil1", "gena_s_avtogenom@fairy.ft", "someGroup"));

    }


}
