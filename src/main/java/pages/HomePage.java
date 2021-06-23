package pages;
import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;


public class HomePage {
    public HomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    private WebDriver webDriver;

    @FindBy(id = "twotabsearchtextbox\"")
    WebElement placeHolder;
    @FindBy(id = "nav-search-submit-button")
    WebElement busca;
    @FindBy(id="p_n_feature_ten_browse-bin/17731927011")
    WebElement productFilter; //
    @FindBy(id="s-result-sort-select_1")
    WebElement filterLowtoHi;
    @FindBy(id="a-autoid-0-announce")
    WebElement filterPrice;
    @FindBy(css=".s-main-slot>div .a-price")
    List<WebElement> resultados;



    public void buscar(String producto){
        placeHolder.sendKeys(producto);
        busca.click();
    }
    public void filtrar(){
        productFilter.click();
    }
    public void filterPrice(){
        filterPrice.click();
        filterLowtoHi.click();
    }
    public void print(){
        for(WebElement productList : resultados){
            System.out.print(productList.getText());
        }
    }
}
