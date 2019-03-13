package lesson11.task19.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends Page {
    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addItem(String itemCount) {

        driver.findElement(By.cssSelector("div.content div.name")).click();

        if (driver.findElements(By.cssSelector("td.options option")).size() > 0) {
            Select menu = new Select(driver.findElement(By.cssSelector("select[name='options[Size]'")));
            menu.selectByIndex(2);
        }

        driver.findElement(By.name("add_cart_product")).click();
        wait.until(ExpectedConditions.textToBe(By.cssSelector("div#cart span.quantity"), itemCount));
    }

    public void checkCartNumber(String itemCount) {

        WebElement number = driver.findElement(By.cssSelector("div#cart span.quantity"));
        Assert.assertTrue(number.getText().equals(itemCount));
    }

}