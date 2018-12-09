package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase{

    @Test
    public void testContactCreation() {


       app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactForm(new ContactData("Gena", "Krokodil", "gena_s_avtogenom@fairy.ft"));
        app.getContactHelper().submitContactCreation();
        app.getContactHelper().returnToHomepage();
    }


}
