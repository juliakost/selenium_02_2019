package lesson11.task19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page {
    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("http://litecart.stqa.ru/en/");
    }

    public String checkItemCountInCard() {

        WebElement number = driver.findElement(By.cssSelector("div#cart span.quantity"));
        String itemCount = number.getText();
        Integer theNext = Integer.parseInt(itemCount) + 1;
        return theNext.toString();
    }

    @FindBy(css = "div.content div.name")
    public WebElement product;

    public void openProduct() {
        product.click();
    }
}