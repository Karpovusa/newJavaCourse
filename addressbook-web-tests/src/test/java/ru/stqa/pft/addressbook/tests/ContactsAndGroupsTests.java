package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class ContactsAndGroupsTests extends TestBase {


    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0)
            app.contact().create(new ContactData().withFirstname("Gena3").withLastname("HiberPomog")
                    .withEmail1("gena@gmail.com").withPhoto(new File("src/test/resources/image.jpg")));
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("Test1").withHeader("Test2").withFooter("Test3"));
            app.goTo().HomePage();
        }

    }

    @Test
    public void testAddContactToGroup() {

        GroupData group = app.db().groups().iterator().next();
        ContactData contact = app.db().contacts().iterator().next();
        app.contact().addToGroup(contact, group);
        app.goTo().HomePage();
        ContactData contactFromDB = app.db().contact(contact.getId());
        assertTrue(contactFromDB.getGroups().contains(group));

    }

    @Test
    public void testDeleteContactFromGroup() {

        ContactData contact = findContactWithGroup();
        GroupData group = contact.getGroups().iterator().next();
        app.contact().deleteContactFromGroup(contact,group);
        app.goTo().HomePage();
        ContactData contactFromDB = app.db().contact(contact.getId());
        assertFalse(contactFromDB.getGroups().contains(group));


    }

    public ContactData findContactWithGroup() {
        ContactData contactWithGroup = null;

        for (ContactData contact : app.db().contacts()) {
            if (contact.getGroups().size() > 0) {
                contactWithGroup = contact;
                break;
            }
        }
        if (contactWithGroup == null) {
            contactWithGroup = app.db().contacts().iterator().next();
            GroupData group = app.db().groups().iterator().next();
            app.contact().addToGroup(contactWithGroup, group);
            contactWithGroup.inGroup(group);
        }
        return contactWithGroup;
    }


}


