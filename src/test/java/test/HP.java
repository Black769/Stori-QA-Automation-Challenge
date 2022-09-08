package test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import pages.HomePage;

public class HP {
    private WebDriver webDriver;
    private HomePage homePage;

@Parameters("browser")
@BeforeMethod
    public void setup(@Optional("Chrome") String browser) throws Exception {
    switch (browser) {
        case "Chrome":
            System.setProperty("webdriver.chrome.driver", "src/main/resources/Driver/chromedriver.exe");
            webDriver = new ChromeDriver();
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            homePage = new HomePage(webDriver);
            break;
        case "Firefox":
            System.setProperty("webdriver.gecko.driver", "src/main/resources/Driver/geckodriver.exe");
            webDriver = new FirefoxDriver();
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            homePage = new HomePage(webDriver);
            break;
        default:
           throw new Exception("unsupport browser");

    }
    }
@Test
    public void HP() throws IOException {
        homePage.load("https://rahulshettyacademy.com/AutomationPractice/");
        homePage.selCntry("me","Mexico");
        homePage.selecTest();
        homePage.switches();
        homePage.tap();
        homePage.alert("Stori Card");
        homePage.tables("25");
        homePage.tablesFlex("Engineer");
        homePage.iFrame();
        homePage.asserts();
}
@AfterMethod
    public void close(){
        webDriver.close();
        webDriver.quit();
}
}
