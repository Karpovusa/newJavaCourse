package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;


public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    //check if no create exists
    //if (app.contact().all().size()==0)
    if(app.db().contacts().size()==0)
      app.contact().create(new ContactData().withFirstname("Gena3").withLastname("HiberPomog").withEmail1("gena@gmail.com").withGroup("someGroup").withPhoto(new File("src/test/resources/image.jpg")));

  }

  @Test
  public void testContactModification() {


    Contacts before = app.db().contacts();
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstname("Gena").withLastname("Krokodilov").withEmail1("gena@gmail.com").withPhoto(new File("src/test/resources/image.jpg"));
    app.contact().modify(contact);
    assertThat(app.contact().count(),equalTo(before.size()));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));


  }
}
