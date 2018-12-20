package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
      Contacts before = app.contact().all();
      ContactData contact = new ContactData().withFirstname("Gena1").withLastname("Krokodilov1").withEmail("gena1@gmail.com").withGroup("someGroup");
      app.contact().create(contact);
      app.goTo().HomePage();
      Contacts after = app.contact().all();

      Assert.assertEquals(after.size(), before.size() + 1);
      assertThat(after, equalTo(before.withAdded(contact.withId(after.stream().mapToInt(g->g.getId()).max().getAsInt()))));


    }


}
