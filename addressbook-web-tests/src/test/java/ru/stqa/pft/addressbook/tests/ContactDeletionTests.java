package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;


public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){

    //check if no create exists
    if (app.db().contacts().size()==0)
      app.contact().create(new ContactData().withFirstname("Gena2").withLastname("Krokodilov2").withEmail1("gena2@gmail.com").withGroup("someGroup").withPhoto(new File("src/test/resources/image.jpg")));;

  }

  @Test
  public void testContactDeletion() {

    Contacts before = app.db().contacts();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().HomePage();

    assertThat(app.contact().count(),equalTo(before.size()-1));
    Contacts after = app.db().contacts();
    assertThat(after, equalTo(before.without(deletedContact)));
  }



}
