package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import javax.print.attribute.standard.MediaSize;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
  private final String browser;
  private final Properties properties;
  private WebDriver wd;
  private RegistrationHelper registrationHelper;
  private FTPHelper ftp;
  private MailHelper mailHelper;
  private  AuthHelper auth;
  private DBhelper dBhelper;
  private NavigationHelper navigationHelper;
  private SoapHelper soapHelper;


  public ApplicationManager(String browser) {
    this.browser = browser;
    properties = new Properties();
  }

  public void init() throws IOException {
    String target = System.getProperty("target", "local");
    properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));

  }

  public void stop() {
    if (wd != null)
      wd.quit();
  }

  public HttpSession newSession() {
    return new HttpSession(this);
  }


  public String getProperty(String key) {
    return properties.getProperty(key);
  }

  public RegistrationHelper registration() {
    if (registrationHelper == null)
      registrationHelper = new RegistrationHelper(this);
    return registrationHelper;
  }
  public FTPHelper ftp(){if(ftp==null)
    ftp= new FTPHelper(this);
  return ftp;

  }

  public WebDriver getDriver() {
    if (wd == null) {
      if (browser.equals(BrowserType.CHROME)) {//System.setProperty("webdriver.chrome.driver", "C:\\Users\\kseliumi\\IdeaProjects\\SKSES tests\\src\\main\\resources\\drivers\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        wd = new ChromeDriver();
      } else if (browser.equals(BrowserType.FIREFOX)) wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true));
      else if (browser.equals(BrowserType.IE)) wd = new InternetExplorerDriver();

      wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      wd.get(properties.getProperty("web.baseUrl"));

    }
    return wd;
  }
  public MailHelper mail(){
    if (mailHelper==null){
      mailHelper= new MailHelper(this);
    }
    return mailHelper;
  }
  public AuthHelper auth(){
    if (auth==null){
      auth= new AuthHelper(this);
    }
    return auth;
  }
  public DBhelper db(){
    if (dBhelper==null){
      dBhelper= new DBhelper(this);
    }
    return dBhelper;
  }

  public NavigationHelper goTO(){
    if (navigationHelper==null){
      navigationHelper= new NavigationHelper(this);
    }
    return navigationHelper;
  }
  public SoapHelper soap(){
    if (soapHelper==null){
      soapHelper= new SoapHelper(this);
    }
    return soapHelper;
  }

}
