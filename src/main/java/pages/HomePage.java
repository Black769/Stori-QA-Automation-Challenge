package pages;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.apache.commons.io.FileUtils;
import org.testng.asserts.SoftAssert;


public class HomePage {
    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    private WebDriver webDriver;
    private SoftAssert softAssert = new SoftAssert();

    @FindBy(xpath = "//*[@id='autocomplete']")
    WebElement sugestion;
    @FindBy(css = "ul.ui-menu>li:nth-child(6)")
    WebElement optionCountry;
    @FindBy(xpath = "/html[1]/body[1]/div[1]/div[3]/fieldset/select")
    WebElement opt;
    @FindBy(css = "#ui-id-1 li")
    protected List<WebElement> countries;
    @FindBy(id = "openwindow")
    WebElement openButton;
    @FindBy(css = "#opentab")
    WebElement openTab;
    @FindBy(linkText = "VIEW ALL COURSES")
    WebElement buton;
    @FindBy(css = "#name")
    WebElement name;
    @FindBy(css = "#alertbtn")
    WebElement alertBtn;
    @FindBy(css = "#confirmbtn")
    WebElement confBtn;
    @FindBy(xpath = "//table[@name='courses']/tbody/tr/td[3]")
    List<WebElement> table;
    @FindBy(xpath = "//div/table[@id='product']/tbody/tr/td[2]")
    List<WebElement> flexTable;
    @FindBy(css = "#courses-iframe")
    WebElement iframe;
    @FindBy(css = "section.price-section div div div div:nth-child(2) ul li")
    List<WebElement> colum;

    public void load(String url){
        webDriver.get(url);
    }
    private void type(WebElement element,String mensage){
        element.sendKeys(mensage);
    }
    private void select(WebElement element, String option){
        Select options = new Select(element);
        options.selectByVisibleText(option);
    }
    private void selCountry(String country){
        for(int cont= 0; cont < countries.size();cont++){
            if(countries.get(cont).getText().equals(country)){
                countries.get(cont).click();
                break;
            }
        }
    }
    public void selCntry(String men,String country){
        Actions actions = new Actions(webDriver);
        actions.sendKeys(sugestion,men).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).perform();
        sugestion.click();
    }
    public void selecTest(){
        select(opt,"Option2");
        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        select(opt,"Option3");
    }
    public void switches(){
        String window1 = webDriver.getWindowHandle();
        openButton.click();
        webDriver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        for(String win : webDriver.getWindowHandles()){
            webDriver.switchTo().window(win);
        }

        webDriver.close();
        webDriver.switchTo().window(window1);
    }
    public void tap() throws IOException {
        String tap = webDriver.getWindowHandle();
        openTab.click();
        webDriver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        for(String win : webDriver.getWindowHandles()){
            webDriver.switchTo().window(win);
        }
        Actions actions = new Actions(webDriver);
        actions.scrollToElement(buton).perform();
        File scrShot = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrShot, new File("src/output/demoTest001.png"));
        webDriver.switchTo().window(tap);
    }
    public void alert(String names){
        name.sendKeys(names);
        alertBtn.click();
        String mensage = webDriver.switchTo().alert().getText();
        System.out.print(mensage+"\n");
        webDriver.switchTo().alert().accept();
        name.sendKeys(names);
        confBtn.click();
        String mensage2 = webDriver.switchTo().alert().getText();
        softAssert.assertEquals(mensage2,"Hello Stori Card, Are you sure you want to confirm?");
        webDriver.switchTo().alert().accept();
    }
    public void tables(String price){
        for(int i=0;i<table.size();i++){
            if(table.get(i).getText().equals(price)){
                int cont = i+2;
                System.out.print(webDriver.findElement(By.xpath("//table[@name='courses']/tbody/tr["+cont+"]/td[2]")).getText()+"$"+price+" \n");
            }
        }
    }
    public void tablesFlex(String position){
        for(int i=0;i<flexTable.size();i++){
            if(flexTable.get(i).getText().equals(position)){
                int cont = i+1;
                System.out.print(webDriver.findElement(By.xpath("//div/table[@id='product']/tbody/tr["+cont+"]/td[1]")).getText()+position+" \n");
            }
        }
    }
    public void iFrame(){
        webDriver.switchTo().frame(iframe);
        System.out.print(colum.get(1).getText());
    }
    public void asserts(){
         softAssert.assertAll();
    }



}


