package lesson4.task7;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

import static junit.framework.TestCase.assertTrue;

public class AdminAppsMenu {
    public WebDriver driver;
    public WebDriverWait wait;

    public boolean isElementPresent(By locator) {

        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public boolean areElementsPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }


    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void adminAppsCheck() throws NoSuchElementException {
        driver.get("http://localhost/litecard/admin");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        WebElement table = driver.findElement(By.cssSelector("ul.list-vertical"));
        List<WebElement> menus = table.findElements(By.tagName("li"));

        for (int i = 1; i <= menus.size(); i++) {
            System.out.println("iteration = " + i);
            driver.findElement(By.cssSelector("ul#box-apps-menu>li:nth-child(" + i + ")")).click();
            assertTrue(isElementPresent(By.cssSelector("td#content>h1")));

            if (areElementsPresent(By.cssSelector("li.selected li"))) {

                WebElement docs = driver.findElement(By.cssSelector("ul.docs"));
                List<WebElement> submenus = docs.findElements(By.tagName("li"));

                for (int j = 1; j < submenus.size() + 1; j++) {
                    driver.findElement(By.cssSelector("ul.docs>li:nth-child(" + j + ")")).click();
                }
            }
        }
    }
    
    @After
    public void stop() {
        driver.quit();
    }
}