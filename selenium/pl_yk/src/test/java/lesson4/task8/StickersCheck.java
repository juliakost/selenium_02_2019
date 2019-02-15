package lesson4.task8;

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

public class StickersCheck {
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
        driver.get("http://localhost/litecard");
        List<WebElement> sections = driver.findElements(By.cssSelector("ul.listing-wrapper.products"));

        for (int i = 0; i < sections.size(); i++) {
            WebElement section = sections.get(i);

            if (areElementsPresent(By.tagName("li"))) {
                List<WebElement> ducks = section.findElements(By.cssSelector("a.link"));

                for (int j = 0; j < ducks.size(); j++) {
                    WebElement duck = ducks.get(j);
                    List<WebElement> stickers = duck.findElements(By.cssSelector("div.image-wrapper>div[class^=sticker]"));
                    assertTrue(stickers.size() == 1);
                }
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
    }
}