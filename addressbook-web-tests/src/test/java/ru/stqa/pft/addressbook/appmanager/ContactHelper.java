package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.tests.ContactCheckTests;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;


public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData, boolean creation) {


        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("email"), contactData.getEmail());
        if(contactData.getHome()!=null)
        type(By.name("home"), contactData.getHome());
        if(contactData.getHome()!=null)
        type(By.name("mobile"), contactData.getMobile());
        if(contactData.getHome()!=null)
        type(By.name("work"), contactData.getWorkPhone());
       if (creation){
           if(contactData.getGroups().size()>0) {
               Assert.assertTrue(contactData.getGroups().size()==1);
            select(By.name("new_group"),contactData.getGroups().iterator().next().getName());
           }

       } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
        attach(By.name("photo"),contactData.getPhoto());

    }

  private void attach(By locator, File photo) {
    if (photo != null) {
        wd.findElement(locator).sendKeys(photo.getAbsolutePath());
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
    click(By.cssSelector(String.format("#maintable tr a[href='edit.php?id=%s'] img",id)));
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
            String address = element.findElement(By.cssSelector("td:nth-child(4)")).getText().replaceAll("\n","");
            String emails = element.findElement(By.cssSelector("td:nth-child(5)")).getText();
            String allPhones = element.findElement(By.cssSelector("td:nth-child(6)")).getText();
            //String[] phones = element.findElement(By.cssSelector("td:nth-child(6)")).getText().split("\n");
            int id = Integer.parseInt(element.findElement(By.cssSelector("td input")).getAttribute("id"));
            ContactData contact = new ContactData()
                    .withId(id)
                    .withFirstname(firstname)
                    .withLastname(lastname)
                    .withAllEmails(emails)
                    .withAddress(address)
                    //.withHomePhone(phones[0])
                    //.withMobile(phones[1])
                    //.withWorkPhone(phones[2])
                    .withAllPhones(allPhones);
            contactCache.add(contact);

        }
    return new Contacts(contactCache);
    }

    public int count() {
        return wd.findElements(By.name("entry")).size();
    }


    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value").replaceAll("\n|\\s+$","");
        wd.navigate().back();
        return new ContactData()
                .withId(contact.getId())
                .withFirstname(firstname)
                .withLastname(lastname)
                .withMobile(mobile)
                .withHomePhone(home)
                .withWorkPhone(work)
                .withAddress(address)
                .withEmail1(email)
                .withEmail2(email2)
                .withEmail3(email3);

    }
    public String mergePhones(ContactData contact) {
        return Arrays.asList(contact.getHome(),contact.getMobile(),contact.getWorkPhone())
                .stream().filter(s->!s.equals(""))
                .map(ContactCheckTests::cleaned)
                .collect(Collectors.joining("\n"));
    }
    public String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmail(),contact.getEmail2(),contact.getEmail3())
                .stream().filter(s->!s.equals(""))
                .collect(Collectors.joining("\n"));
    }


    public void selectGroup(GroupData group) {
       // select(By.xpath(String.format("//option[text() = \"%s\"]",group.getName())),null);
        WebElement selectElem = wd.findElement(By.xpath("//select[@name='to_group']"));
        Select select = new Select(selectElem);
        select.selectByValue(String.valueOf(group.getId()));


    }
    public  void addToGroup(ContactData contact, GroupData group) {
        checkContact(contact);
       selectGroup(group);
        click(By.cssSelector("input[value='Add to']"));

    }

    public void checkContact(ContactData contact){

        click(By.cssSelector(String.format("input[type='checkbox'][id='%s']",contact.getId())));

    }

    public void filterByGroup(GroupData group) {
        WebElement selectElem = wd.findElement(By.xpath("//select[@name='group']"));
        Select select = new Select(selectElem);
        select.selectByValue(String.valueOf(group.getId()));

    }

    public void deleteContactFromGroup(ContactData contact, GroupData group) {
        filterByGroup(group);
        click(By.cssSelector(String.format("input[id='%s']",contact.getId())));
        click(By.cssSelector("input[name='remove']"));

    }
}
