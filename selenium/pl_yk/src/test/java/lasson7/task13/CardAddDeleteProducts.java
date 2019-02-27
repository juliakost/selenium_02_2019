package lasson7.task13;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CardAddDeleteProducts {
    public WebDriver driver;
    public WebDriverWait wait;

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void cardAddOrDelete(){

        driver.get("http://litecart.stqa.ru/en/");

        for (int i = 1; i <= 3; i++) {
            addItem();
            driver.findElement(By.id("logotype-wrapper")).click();
        }

        emptyCart();
    }

    private void addItem() {

        WebElement number = driver.findElement(By.cssSelector("div#cart span.quantity"));
        By locator = By.cssSelector("div#cart span.quantity");
        String itemCount = number.getText();
        Integer theNext = Integer.parseInt(itemCount) + 1;
        itemCount = theNext.toString();

        driver.findElement(By.cssSelector("div.content div.name")).click();

        if (driver.findElements(By.cssSelector("td.options option")).size() > 0) {
            Select menu = new Select(driver.findElement(By.cssSelector("select[name='options[Size]'")));
            menu.selectByIndex(2);
        }

        driver.findElement(By.name("add_cart_product")).click();
        wait.until(ExpectedConditions.textToBe(locator, itemCount));

        number = driver.findElement(locator);
        Assert.assertTrue(number.getText().equals(itemCount));
    }

    private void emptyCart() {
        int count = driver.findElements(By.cssSelector("li.shortcut")).size();
        for (int i = count; i > 1; i--) {
            driver.findElement(By.cssSelector("li.shortcut")).click();
            driver.findElement(By.name("remove_cart_item")).click();

            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(
                    By.cssSelector("table.dataTable td.item"), i));

            driver.findElement(By.name("remove_cart_item")).click();

            wait.until(ExpectedConditions.stalenessOf(
                    driver.findElement(By.cssSelector("table.dataTable"))));

        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}