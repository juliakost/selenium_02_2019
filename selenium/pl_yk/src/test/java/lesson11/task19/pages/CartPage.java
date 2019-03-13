package lesson11.task19.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends Page{
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("http://localhost/litecart/en/checkout");
    }

    public void emptyCart() {
        int count = driver.findElements(By.cssSelector("li.shortcut")).size();
        for (int i = count; i > 1; i--) {
            driver.findElement(By.cssSelector("li.shortcut")).click();
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(
                    By.cssSelector("table.dataTable td.item"), i));
        }

        driver.findElement(By.name("remove_cart_item")).click();
        wait.until(ExpectedConditions.stalenessOf(
                driver.findElement(By.cssSelector("table.dataTable"))));
    }
}