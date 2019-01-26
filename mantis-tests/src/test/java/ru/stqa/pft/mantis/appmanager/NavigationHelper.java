package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.mantis.model.MantisUser;


public class NavigationHelper extends HelperBase {


    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void groupPage() {

        if (isElementPresent(By.tagName("h1"))
                && wd.findElement(By.tagName("h1")).getText().equals("Groups")
                && isElementPresent(By.name("new")))
            return;
        click(By.linkText("groups"));
    }

    public void HomePage() {
        if (isElementPresent(By.id("maintable")))
            return;
        click(By.linkText("home"));
    }

    public void usersPage() {
        click(By.cssSelector(".bracket-link:first-of-type"));
    }
    public void userSettings(MantisUser user){
        click(By.cssSelector(String.format("a[href='manage_user_edit_page.php?user_id=%s']",user.getId())));
    }
}
