package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.stqa.pft.addressbook.tests.ContactHelper;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    FirefoxDriver wd;

    private SessionHelper sessionHelper;
    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private ContactHelper contactHelper;

    public void init() {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("http://192.168.0.2/addressbook/group.php");
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        contactHelper=new ContactHelper(wd);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        wd.quit();
    }

    public GroupHelper getGroupHelper() {
        return groupHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }

    public void gotoGroupPage() {
        navigationHelper.gotoGroupPage();
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public ContactHelper getContactHelper() {
        return contactHelper;
    }


}
