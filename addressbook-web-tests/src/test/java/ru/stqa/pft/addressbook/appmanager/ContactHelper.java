package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {


        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("email"), contactData.getEmail());
        if (creation&&contactData.getGroup()!=null) {
            select(By.name("new_group"),contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }

    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void returnToHomepage() {
        click(By.linkText("home page"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

  private void selectContactsById(int id) {
    wd.findElement(By.cssSelector("#maintable tr input[id='"+id+"']")).click();
  }
  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCache=null;
    returnToHomepage();
  }

  private void initContactModificationById(int id) {
    click(By.cssSelector("#maintable tr a[href='edit.php?id="+id+"'] img"));
  }

  public void delete(ContactData contact) {
    selectContactsById(contact.getId());
    deleteSelectedContacts();
    contactCache=null;
    submitContactsDeletion();
  }

  public void create(ContactData contactData) {
    initContactCreation();
    fillContactForm(contactData, true);
    submitContactCreation();
    contactCache=null;
    returnToHomepage();
  }

  public void deleteSelectedContacts() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void submitContactsDeletion() {
        acceptDialogueWindow();
    }


  private Contacts contactCache=null;

    public Contacts all() {
      if(contactCache!=null) return new Contacts(contactCache);

        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.cssSelector("tr[name=entry]"));
        for (WebElement element : elements) {
            String lastname = element.findElement(By.cssSelector("td:nth-child(2)")).getText();
            String firstname = element.findElement(By.cssSelector("td:nth-child(3)")).getText();
            String email = element.findElement(By.cssSelector("td:nth-child(5)")).getText();
            int id = Integer.parseInt(element.findElement(By.cssSelector("td input")).getAttribute("id"));
            ContactData contact = new ContactData().withId(id).withFirstname(firstname).withLastname(lastname).withEmail(email);
            contactCache.add(contact);

        }
    return new Contacts(contactCache);
    }



}
