package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.appmanager.HelperBase;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;


public class ContactHelper extends HelperBase{

    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void fillContactForm(ContactData contactData) {

        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("email"), contactData.getEmail());

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
}
