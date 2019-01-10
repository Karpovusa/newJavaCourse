package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.junit.MatcherAssert.*;

public class ContactCheckTests extends TestBase {

    @Test
    public void testCorrectContact(){
        app.goTo().HomePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromEditForm=app.contact().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(),equalTo(app.contact().mergePhones(
                contactInfoFromEditForm)));
        assertThat(contact.getAllEmails(),equalTo(app.contact().mergeEmails(
                contactInfoFromEditForm)));
        assertThat(contact.getAddress(),equalTo(contactInfoFromEditForm.getAddress()));
    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s|[-()]","");
    }

}
