package test;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

public class HP {
    private WebDriver webDriver;

@BeforeMethod
    public void setup(){
        ChromeOptions chromeOptions = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver","src/main/resources/Driver/chromedriver.exe");
        webDriver = new ChromeDriver(chromeOptions);
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }
@Test
    public void HP(){
        HomePage homePage = new HomePage(webDriver);
        webDriver.get("https://amazon.com");
        homePage.buscar("phone case");
        homePage.filtrar();
        homePage.filterPrice();
        homePage.print();
}
@AfterMethod
    public void close(){
        webDriver.close();
        webDriver.quit();
}
}
