package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactModificationTests extends TestBase {
    @Test
    public void testContactModification() {
        if (!app.getContactHelper().isThereAContact())
            app.getContactHelper().createContact(new ContactData("Gena1", "Krokodil1", "gena_s_avtogenom@fairy.ft", "someGroup"));
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Gennadiy", "El Croco", "gena_el_croco@viva.me", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomepage();
    }

}
