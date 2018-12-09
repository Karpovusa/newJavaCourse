package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class HelperBase {
    WebDriver wd;

    public HelperBase(WebDriver wd) {
        this.wd = wd;
    }

    public void click(By locator) {
        wd.findElement(locator).click();
    }

    public void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    protected boolean isElementPresent(By locator) {
        try{
            wd.findElement(locator);
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    public void acceptDialogueWindow() {
        wd.switchTo().alert().accept();
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
    public void select(By locator, String text){
        Select select= new Select(wd.findElement(locator));
        if(elementExistsInDropdown(select,text)) select.selectByVisibleText(text);
    }

    public boolean elementExistsInDropdown(Select select, String text){
        List<WebElement> DrpDwnList=select.getOptions();
       for(WebElement element:DrpDwnList){
            if (element.getText().contains(text))
                return true;
            }
           return false;

    }
}
