package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;


public class ContactDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions(){

    //check if no create exists
    if (app.contact().all().size()==0)
      app.contact().create(new ContactData().withFirstname("Gena2").withLastname("Krokodilov2").withEmail("gena2@gmail.com").withGroup("someGroup"));

  }

  @Test
  public void testContactDeletion() {

    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().delete(deletedContact);
    app.goTo().HomePage();
    Contacts after = app.contact().all();

    Assert.assertEquals(after.size(), before.size() - 1);
    assertThat(after, equalTo(before.without(deletedContact)));
  }



}
